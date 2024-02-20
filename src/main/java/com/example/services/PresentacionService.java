package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.entities.Presentacion;


public interface PresentacionService {

    public Page<Presentacion> findAll(Pageable pageable);
    public List<Presentacion> findAll(Sort sort);
    public List<Presentacion> findAll();
    public Optional<Presentacion> findById(int id);
    public Presentacion save(Presentacion presentacion);
    public void delete(Presentacion presentacion);
    public void deleteById(int id);
    public boolean existsById(int idPresentacion);
    
    public  List<Presentacion> findPresentacionesByProductosId(int productoId);
    

}
