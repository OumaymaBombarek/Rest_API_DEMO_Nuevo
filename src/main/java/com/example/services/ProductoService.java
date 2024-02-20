package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.entities.Producto;

public interface ProductoService {

    public Page<Producto> findAll(Pageable pageable);
    public List<Producto> findAll(Sort sort);
    public List<Producto> findAll();
    public Optional<Producto> findById(int id);
    public Producto save(Producto producto);
    
    public void delete(Producto producto);
    public void deleteAll();

    public boolean existsById(int idProducto);

    List<Producto> findProductosByNameContaining(String name);


 List<Producto> findProductosByPresentacionesId(int productoId);
}
