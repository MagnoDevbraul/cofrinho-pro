package com.example.demo.controller;

import com.example.demo.model.Meta;
import com.example.demo.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/metas")
public class MetaController {

    @Autowired
    private MetaService service;

    @GetMapping
    public List<Meta> listar() {
        List<Meta> metas = service.listarTodas();
        metas.forEach(meta -> meta.setPorcentagemConcluida(service.calcularPorcentagem(meta)));
        return metas;
    }
}