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
import com.example.entities.Producto;

@Repository

public interface Productodao extends JpaRepository <Producto,Integer>{

 //List<Producto> findProductosByPresentacionesId (int idPresentacion);

 @Query(value = "select p from Producto p left join fetch p.presentaciones", 
countQuery = "select count(p) from Producto p left join p.presentaciones")
 public Page<Producto> findAll(Pageable pageable);

//     // Metodo 2
     @Query(value = "select p from Producto p left join fetch p.presentaciones")
     public List<Producto> findAll(Sort sort);

//     // Metodo 3
     @Query(value = "select p from Producto p left join fetch p.presentaciones where p.id = :id")
     public Optional<Producto> findById(int id);

     List<Producto> findByNameContaining(String name);
     List<Producto> findProductosByPresentacionesId(int productoId);

     
    
     

}
