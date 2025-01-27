package com.example.demo.repositorios;

import com.example.demo.modelos.Ejemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EjemplarRepository extends JpaRepository<Ejemplar, Integer> {
    List<Ejemplar> findByEstado(String estado);
}