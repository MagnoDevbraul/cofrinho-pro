package com.example.demo.repository;

import com.example.demo.dto.MoedaResumoDTO;
import com.example.demo.model.Moeda;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MoedaRepository extends JpaRepository<Moeda, Long> {

    List<Moeda> findByUsuario(Usuario usuario);

    // Corrigido para fazer a projeção exata sem confundir os aliases do Hibernate
    @Query("SELECT new com.example.demo.dto.MoedaResumoDTO(" +
            "m.id, " +
            "m.nome, " +
            "m.codigo, " +
            "m.saldo, " +
            "m.saldo, " + // Mantido m.saldo temporariamente como valorEmReal
            "m.metaEmReal) " +
            "FROM Moeda m " +
            "WHERE m.usuario.id = :usuarioId")
    List<MoedaResumoDTO> buscarResumoPorUsuario(@Param("usuarioId") Long usuarioId);
}