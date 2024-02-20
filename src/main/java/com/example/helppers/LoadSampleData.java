package com.example.helppers;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.entities.Presentacion;
import com.example.entities.Producto;
import com.example.services.PresentacionService;
import com.example.services.ProductoService;

@Configuration
public class LoadSampleData {

    @Bean
    public CommandLineRunner saveSampleData(ProductoService productoService,
    PresentacionService presentacionService){

        return datos -> {

            Presentacion presentacion1 = Presentacion.builder()
                            .name("docena")
                            .build();

            Presentacion presentacion2 = Presentacion.builder()
                                        .name("unidad")
                                        .build();

            Producto producto1 = Producto.builder()
                                .name("rezma de papel")
                                .description("Des1")
                                .stock(10)
                                .price(3.75)
                                .build();

            Producto producto2 = Producto.builder()
                                .name("cartas")
                                .description("Desc 2")
                                .stock(1)
                                .price(10)
                                .build();

            Producto producto3 = Producto.builder()
                                .name("nada")
                                .description("Desc g")
                                .stock(1)
                                .price(10)
                                .build();

            Producto producto4 = Producto.builder()
                                .name("popee")
                                .description("Desc 2")
                                .stock(1)
                                .price(10)
                                .build();

            Producto producto5 = Producto.builder()
                                .name("juegos")
                                .description("Desc 2")
                                .stock(1)
                                .price(10)
                                .build();

            // productoService.save(producto5);
            // productoService.save(producto4);
            // productoService.save(producto3);
            // productoService.save(producto2);
            // productoService.save(producto1);
            


           presentacion1.setProductos(List.of(producto1,producto3));
           presentacion2.setProductos(List.of(producto5,producto4,producto2));

           producto2.setPresentaciones(List.of(presentacion1,presentacion2));
           producto1.setPresentaciones(List.of(presentacion2));


           presentacionService.save(presentacion2);
           presentacionService.save(presentacion1);
           
           productoService.save(producto5);
           productoService.save(producto4);
           productoService.save(producto3);
           productoService.save(producto2);
           productoService.save(producto1);
           
           
        };


}
}
