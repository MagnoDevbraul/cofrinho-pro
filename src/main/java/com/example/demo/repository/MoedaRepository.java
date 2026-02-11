package com.example.demo.repository;

import com.example.demo.model.Moeda;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MoedaRepository extends JpaRepository<Moeda, Long> {
    // Esta linha é a que está faltando para o Controller funcionar!
    List<Moeda> findByUsuario(Usuario usuario);
}