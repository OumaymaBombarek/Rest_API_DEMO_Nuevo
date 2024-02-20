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
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Presentacion;
import com.example.entities.Producto;
import com.example.excepcion.ResourceNotFoundException;
import com.example.services.PresentacionService;
import com.example.services.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor


public class PresentacionController {

    private final PresentacionService presentacionService;
    private final ProductoService productoService;

    @GetMapping("/presentaciones")
    public ResponseEntity<List<Presentacion>> getAllPresentaciones(){

        List<Presentacion> presentaciones = new ArrayList<>();

        presentacionService.findAll().forEach(presentaciones::add);

        if (presentaciones.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

            return new ResponseEntity<>(presentaciones,HttpStatus.OK);
        
    }

    // Dar todos las presentaciones cuyo id de productos es
    
     @GetMapping("/productos/{productoId}/presentaciones")

    public ResponseEntity<List<Presentacion>> getAllPresentacionesByProductosId(@PathVariable(value = "productoId") Integer productoId){
        
        if(! productoService.existsById(productoId)){
            throw new ResourceNotFoundException("Not found presentation with id = " + productoId);
        }

        List<Presentacion> presentaciones = presentacionService.findPresentacionesByProductosId(productoId);
        return new ResponseEntity<>(presentaciones, HttpStatus.OK);
    }


    @GetMapping("/presentaciones/{id}")

    public ResponseEntity<Presentacion> getPresentacionesById(@PathVariable(value = "id") Integer id) {
      Presentacion presentacion = presentacionService.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + id));
  
      return new ResponseEntity<>(presentacion, HttpStatus.OK);
    }

    @GetMapping("/presentaciones/{presentacionId}/productos")

    public ResponseEntity<List<Producto>> getAllProductosBypresentacionId(@PathVariable(value = "presentacionId") Integer presentacionId) {
      if (!presentacionService.existsById(presentacionId)) {

        throw new ResourceNotFoundException("Not found presentation  with id = " + presentacionId);
      }
  
      List<Producto> productos = productoService.findProductosByPresentacionesId(presentacionId);
      return new ResponseEntity<>(productos, HttpStatus.OK);
    }

     @PostMapping("/productos/{productoId}/presentaciones")

     public ResponseEntity<Presentacion> addPresentacion(@PathVariable(value = "productoId") Integer productoId, @RequestBody Presentacion presetacionRequest) {

   Presentacion presentacion = productoService.findById(productoId).map(producto -> {

         int presentacionId = presetacionRequest.getId();

         if(presentacionId != 0L){
             Presentacion presentacion1 = presentacionService.findById(presentacionId)
             .orElseThrow(() -> new  ResourceNotFoundException("Not found presentation with id = " + presentacionId));
           producto.addPresentacion(presentacion1);
             productoService.save(producto);
             return presentacion1;
         }

             producto.addPresentacion(presetacionRequest);
           return presentacionService.save(presetacionRequest);
         }).orElseThrow(() -> new ResourceNotFoundException("Not found producto with id = " + productoId));

         return new ResponseEntity<>(presentacion, HttpStatus.CREATED);





     }

     @PutMapping("/presentaciones/{id}")

  public ResponseEntity<Presentacion> updatePresentacion(@PathVariable("id") int id, @RequestBody Presentacion presentacionRequest) {
    Presentacion presentacion= presentacionService.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("presentacionId " + id + "not found"));

    presentacion.setName(presentacionRequest.getName());

    return new ResponseEntity<>(presentacionService.save(presentacion), HttpStatus.OK);
  }


    @DeleteMapping("/productos/{productoId}/presentaciones/{presentacionId}")
    
   public ResponseEntity<HttpStatus> deletePresentacionFromProducto(@PathVariable(value = "productoId") Integer productoId, @PathVariable(value = "presentacionId") Integer presentacionId) {
     Producto producto = productoService.findById(productoId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Products with id = " + productoId));
    
     producto.removePresentacion(presentacionId);
     productoService.save(producto);
    
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }

@DeleteMapping("/presentaciones/{id}")
public ResponseEntity<HttpStatus> deletePresentacion(@PathVariable("id") int id) {
  presentacionService.deleteById(id);

  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}