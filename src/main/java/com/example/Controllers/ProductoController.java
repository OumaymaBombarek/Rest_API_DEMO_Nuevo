package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.entities.Producto;
import com.example.excepcion.ResourceNotFoundException;
import com.example.services.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/productos")

     public ResponseEntity<List<Producto>> findAll (@RequestParam(required = false) String name){

        List<Producto> productos = new ArrayList<>();

        if(name == null)
          
            productoService.findAll().forEach(productos::add);

        else
            productoService.findProductosByNameContaining(name).forEach(productos::add);

        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        }
            

        return new ResponseEntity<>(productos, HttpStatus.OK);
    
}

@GetMapping("/productos/{id}")
public ResponseEntity<Producto> getProductosById(@PathVariable("id") Integer id){

    Producto producto = productoService.findById(id)
    .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado un producto con id = " +id));

    return new ResponseEntity<>(producto,HttpStatus.OK);
}

 @PostMapping("/productos")
 public ResponseEntity<Producto>  crearProducto(@RequestBody Producto producto){

    // Preguntar se utilizar el constructor de lombok esta bien

    Producto productocreado = productoService.save(
        Producto.builder()
        .name(producto.getName())
        .description(producto.getDescription())
        .price(producto.getPrice())
        .stock(producto.getStock())
        .build()
    );

    return new ResponseEntity<>(productocreado,HttpStatus.CREATED);

}

@PutMapping("/productos/{id}")
public ResponseEntity<Producto> updateProducto(@PathVariable("id") Integer id, @RequestBody Producto producto) {
    Producto productoActualizado = productoService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found product with id = " + id));

    productoActualizado.setName(producto.getName());
    productoActualizado.setDescription(producto.getDescription());
    productoActualizado.setPrice(producto.getPrice());
    productoActualizado.setStock(producto.getStock());
    
    return new ResponseEntity<>(productoService.save(productoActualizado), HttpStatus.OK);
  }


  @DeleteMapping("/productos")
  public ResponseEntity<HttpStatus> deleteAllProducts() {
    productoService.deleteAll();
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/productos/{id}")
  public ResponseEntity<HttpStatus> deleteProducto(int id) {
    productoService.findById(id);
    
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }



  // NO hice el ultimo metodo



    



    


}
