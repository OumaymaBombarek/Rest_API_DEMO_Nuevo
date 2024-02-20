package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dao.Presentaciondao;
import com.example.dao.Productodao;
import com.example.entities.Presentacion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresentacionImpl implements PresentacionService {

    private final Presentaciondao presentaciondao;

    @Override
    public Page<Presentacion> findAll(Pageable pageable) {
        
        return presentaciondao.findAll(pageable);
    }

    @Override
    public List<Presentacion> findAll(Sort sort) {
       return presentaciondao.findAll(sort);
    }

    @Override
    public List<Presentacion> findAll() {
        return presentaciondao.findAll();
    }

    

    @Override
    public Presentacion save(Presentacion presentacion) {
        
        return presentaciondao.save(presentacion);
    }

    @Override
    public void delete(Presentacion presentacion) {
        presentaciondao.delete(presentacion);
    }

    @Override
    public Optional<Presentacion> findById(int id) {
        
        return presentaciondao.findById(id);
    }

    @Override
    public List<Presentacion> findPresentacionesByProductosId(int productoId) {
        
        return presentaciondao.findPresentacionesByProductosId(productoId);
    }

    @Override
    public boolean existsById(int idPresentacion) {
        
        return presentaciondao.existsById(idPresentacion);
    }

    

    @Override
    public void deleteById(int id) {
        presentaciondao.deleteById(id);
    }

    

}
