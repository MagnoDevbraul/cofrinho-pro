let graficoRosca;
let graficoBarras;
let todasTransacoes = [];
let ultimaMovimentacaoPorMoeda = {};

//  mapeamento da 9 cores fixa
const mapaCoresMoedas = {
    "BRL": "#27ae60",
    "USD": "#2980b9",
    "EUR": "#9b59b6",
    "KRW": "#e67e22",
    "JPY": "#f1c40f",
    "BTC": "#7f8c8d",
    "INR": "#34495e",
    "CNY": "#16a085",
    "ILS": "#e84393"
};

document.addEventListener("DOMContentLoaded", function () {
    carregarTotal();
    carregarMoedas();
    carregarHistorico();
    configurarMenuLateral();
});

function configurarMenuLateral() {
    const botoes = document.querySelectorAll(".sidebar-menu .menu-btn");
    botoes.forEach(btn => {
        btn.addEventListener("click", function() {
            botoes.forEach(b => b.classList.remove("active"));
            this.classList.add("active");
            const textoBotao = this.innerText.trim();
            if (textoBotao.includes("Saldo Total")) {
                document.getElementById("totalCarteira")?.scrollIntoView({ behavior: 'smooth' });
            } else if (textoBotao.includes("Ver Detalhes")) {
                document.querySelector(".panel-card h3")?.scrollIntoView({ behavior: 'smooth' });
            } else if (textoBotao.includes("Retirar Valor")) {
                abrirModal(1, "REAL");
            }
        });
    });
}

async function carregarTotal() {
    try {
        const response = await fetch("/moedas/total-geral", { credentials: "include" });
        const data = await response.json();
        const elTotal = document.getElementById("totalCarteira");
        if (elTotal) {
            elTotal.innerText = Number(data.totalEmReal).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        }
    } catch (error) {
        console.error("Erro ao carregar total geral:", error);
    }
}

async function carregarMoedas() {
    try {
        const response = await fetch("/moedas/resumo", { credentials: "include" });
        const moedas = await response.json();

        let labels = [];
        let valoresEmReal = [];
        let quantoFalta = [];
        let coresOrdenadas = [];

        const simbolos = {
            "BRL": "R$", "USD": "U$", "EUR": "€", "KRW": "₩",
            "JPY": "¥", "BTC": "₿", "INR": "₹", "CNY": "¥", "ILS": "₪"
        };

        moedas.forEach(m => {
            const codigoUpper = m.codigo.toUpperCase();
            const saldoNominal = Number(m.saldo);
            const valorReal = Number(m.valorEmReal);

            labels.push(m.nome);
            valoresEmReal.push(valorReal);
            coresOrdenadas.push(mapaCoresMoedas[codigoUpper] || "#7f8c8d");

            let metaDefinida = m.metaEmReal || m.valorDesejadoEmReal;
            if (!metaDefinida) {
                metaDefinida = (codigoUpper === "BTC") ? 150000 : 10000;
            }

            const falta = metaDefinida - valorReal;
            quantoFalta.push(falta > 0 ? falta : 0);

            // Vincula o clique tanto na classe amigável quanto no código iso
            let classeMoeda = codigoUpper.toLowerCase();
            if (codigoUpper === "INR") classeMoeda = "rupia";

            const card = document.querySelector(`.card-moeda.${classeMoeda}`) || document.querySelector(`.card-moeda.${codigoUpper.toLowerCase()}`);
            if (card) {
                const pSigla = card.querySelector(".sigla");
                if (pSigla) {
                    const simbolo = simbolos[codigoUpper] || "$";
                    pSigla.innerText = `${simbolo} ${saldoNominal.toFixed(2)}`;
                }
                card.onclick = () => abrirModal(m.id, m.nome);
            }
        });

        renderizarGraficosMetas(labels, valoresEmReal, quantoFalta, coresOrdenadas);

    } catch (error) {
        console.error("Erro ao carregar moedas e metas:", error);
    }
}

function renderizarGraficosMetas(labels, valoresReal, faltaParaMeta, coresMoedas) {
    const ctxBarras = document.getElementById('graficoBarrasTop');
    if (ctxBarras) {
        try {
            if (graficoBarras) graficoBarras.destroy();
            graficoBarras = new Chart(ctxBarras.getContext('2d'), {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        data: valoresReal,
                        backgroundColor: coresMoedas,
                        borderRadius: 4
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: { display: false },
                        tooltip: { callbacks: { label: context => ` Total: R$ ${Number(context.raw).toFixed(2)}` } }
                    },
                    scales: {
                        x: { grid: { display: false }, ticks: { color: '#a0aec0', font: { size: 11 } } },
                        y: {
                            type: 'logarithmic',
                            grid: { color: '#2d3545' },
                            ticks: {
                                color: '#a0aec0',
                                font: { size: 11 },
                                callback: value => (value === 10 || value === 100 || value === 1000 || value === 10000 || value === 100000) ? 'R$ ' + value.toLocaleString('pt-BR') : null
                            }
                        }
                    }
                }
            });
        } catch (err) {
            console.error("Erro no gráfico de barras:", err);
        }
    }

    const percentuaisConclusao = valoresReal.map((atual, index) => {
        const falta = faltaParaMeta[index] || 0;
        const metaTotal = atual + falta;
        if (metaTotal === 0) return 0;
        const porcentagem = (atual / metaTotal) * 100;
        return porcentagem > 100 ? 100 : porcentagem;
    });

    const ctxPizza = document.getElementById('graficoCarteira');
    if (ctxPizza) {
        try {
            if (graficoRosca) graficoRosca.destroy();
            graficoRosca = new Chart(ctxPizza.getContext('2d'), {
                type: 'pie',
                data: {
                    labels: labels.map(l => `Progresso de ${l}`),
                    datasets: [{
                        data: percentuaisConclusao,
                        backgroundColor: coresMoedas,
                        borderWidth: 0
                    }]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    plugins: {
                        legend: { display: false },
                        tooltip: { callbacks: { label: context => ` Concluído: ${Number(context.raw).toFixed(1)}%` } }
                    }
                }
            });
        } catch (err) {
            console.error("Erro no gráfico de pizza:", err);
        }
    }
}

async function carregarHistorico() {
    try {
        //  O SEGREDO ESTÁ AQUI: "credentials: 'include'" obriga o navegador a enviar o cookie da sessão
        const response = await fetch("/transacoes", { credentials: "include" });

        if (!response.ok) {
            console.error(`Endpoint /transacoes retornou status: ${response.status}`);
            return;
        }

        const transacoes = await response.json();
        const tbody = document.getElementById("historicoTransacoes");
        if (!tbody) return;

        tbody.innerHTML = "";

        if (!transacoes || transacoes.length === 0) {
            tbody.innerHTML = `<tr><td colspan="4" style="text-align:center; color:var(--text-muted);">Nenhuma transação encontrada.</td></tr>`;
            return;
        }

        // Garante que as transações mais recentes apareçam primeiro na tabela
        transacoes.reverse();

        ultimaMovimentacaoPorMoeda = {};

        transacoes.forEach(t => {
            if (
                t.moeda &&
                t.moeda.codigo &&
                !ultimaMovimentacaoPorMoeda[t.moeda.codigo]
            ) {
                ultimaMovimentacaoPorMoeda[t.moeda.codigo] = t.tipo;
            }
        });

        transacoes.forEach(t => {
            const tr = document.createElement("tr");
            const classeValor = t.tipo === "DEPOSITO" ? "val-positivo" : "val-negativo";
            const classeBadge = t.tipo === "DEPOSITO" ? "deposito" : "retirada";

            const dataFormatada = t.dataHora ? new Date(t.dataHora).toLocaleString('pt-BR') : '---';

            // Mapeamento dinâmico para o nome da moeda
            let nomeMoeda = "Moeda";
            if (t.moeda && typeof t.moeda === "object" && t.moeda.nome) {
                nomeMoeda = t.moeda.nome;
            } else if (t.nomeMoeda) {
                nomeMoeda = t.nomeMoeda;
            } else if (t.moedaNome) {
                nomeMoeda = t.moedaNome;
            }

            // Mapeamento dinâmico para o valor
            const valorExibido = t.valorAlterado !== undefined ? t.valorAlterado : (t.valor || 0);

            tr.innerHTML = `
                <td>${nomeMoeda}</td>
                <td class="${classeValor}">R$ ${Number(valorExibido).toFixed(2)}</td>
                <td><span class="badge ${classeBadge}">${t.tipo}</span></td>
                <td class="text-muted">${dataFormatada}</td>
            `;
            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error("Erro ao processar histórico no front-end:", error);
    }
}

function abrirModal(id, nomeMoeda) {
    const modal = document.getElementById("modalMovimentacao");
    const inputId = document.getElementById("modalMoedaId");
    const titulo = document.getElementById("modalTitulo");
    const inputValor = document.getElementById("modalValor");

    if (modal && inputId && titulo && inputValor) {
        inputId.value = id;
        titulo.innerText = `Movimentar - ${nomeMoeda}`;
        inputValor.value = "";
        modal.style.display = "flex";
    }
}

function fecharModal() {
    const modal = document.getElementById("modalMovimentacao");
    if (modal) modal.style.display = "none";
}

async function enviarMovimentacao(tipoOperacao) {
    const id = document.getElementById("modalMoedaId").value;
    const valorDigitado = document.getElementById("modalValor").value;

    if (!valorDigitado || Number(valorDigitado) <= 0) {
        alert("Por favor, informe um valor maior que zero.");
        return;
    }

    try {
        const response = await fetch(`/moedas/${id}/${tipoOperacao}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ valor: valorDigitado }),
            credentials: "include"
        });

        const data = await response.json();

        if (response.ok) {
            fecharModal();
            await carregarTotal();
            await carregarMoedas();
            await carregarHistorico();
        } else {
            alert("Erro na operação: " + (data.erro || "Falha desconhecida"));
        }
    } catch (error) {
        console.error(`Erro ao processar ${tipoOperacao}:`, error);
        alert("Não foi possível conectar ao servidor.");
    }
}

function logout() {
    fetch("/logout", { method: "POST", credentials: "include" }).then(() => {
        window.location.href = "/login.html";
    });
}