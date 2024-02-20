package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entities.Presentacion;

@Repository

public interface Presentaciondao extends JpaRepository<Presentacion,Integer>{

 //List<Producto> findPresentacionesByProductos (int idPresentacion);
 @Query(value = "select p from Presentacion p left join fetch p.productos", 
countQuery = "select count(p) from Presentacion p left join p.productos")

 public Page<Presentacion> findAll(Pageable pageable);

//     // Metodo 2
     @Query(value = "select p from Presentacion p left join fetch p.productos")
     public List<Presentacion> findAll(Sort sort);

//     // Metodo 3
     @Query(value = "select p from Presentacion p left join fetch p.productos where p.id = :id")
     public Optional<Presentacion> findById(int id);

     List<Presentacion> findPresentacionesByProductosId(int productoId);

    

}
