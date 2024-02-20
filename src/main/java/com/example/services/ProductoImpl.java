package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.dao.Productodao;
import com.example.entities.Producto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductoImpl implements ProductoService{

     //inyectar por constructor

    private final Productodao productodao;

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        
        return productodao.findAll(pageable);
    }

    @Override
    public List<Producto> findAll(Sort sort) {
        
        return productodao.findAll(sort);
    }

    @Override
    public List<Producto> findAll() {
        return productodao.findAll();
    }

    @Override
    public Optional findById(int id) {
        
        return productodao.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productodao.save(producto);
    }

    @Override
    public void delete(Producto producto) {
      
      productodao.delete(producto)  ;
    }

    @Override
    public List<Producto> findProductosByNameContaining(String name) {
       return productodao.findByNameContaining(name);
    }

    @Override
    public void deleteAll() {
        productodao.deleteAll();
    }

    @Override
    public boolean existsById(int idProducto) {
        return productodao.existsById(idProducto);
    }

    @Override
    public List<Producto> findProductosByPresentacionesId(int productoId) {
        
       return productodao.findProductosByPresentacionesId(productoId);
    }

    

    

    

    

}
