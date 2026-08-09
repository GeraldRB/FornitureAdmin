package com.Forniture.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    //Id Producto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoID")
    private Integer productoID;

    //Categoria
    @Column(name = "categoria", nullable = false)
    @NotNull(message = "La categoria no puede estar vacío")
    private Integer categoria;

    //Nombre del Producto
    @Column(name = "productoNombre", nullable = false, length = 100)
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String productoNombre;

    //Precio de Venta decimal
    @Column(name = "precioVenta", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "El precio no puede estar vacio")
    private BigDecimal precioVenta;

    //Precio de Venta decimal
    @Column(name = "skuReferencia", nullable = false, length = 20, unique = true)
    @NotBlank(message = "El numero de serie no puede estar vacio")
    private String skuReferencia;

    //Precio de Venta decimal
    @Column(name = "descripcion", nullable = false, length = 500)
    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcion;

    //Medidas opcionales
    //Precio de Venta decimal
    @Column(name = "ancho", precision = 10, scale = 2)
    private BigDecimal ancho;

    //Precio de alto decimal
    @Column(name = "alto", precision = 10, scale = 2)
    private BigDecimal alto;

    //Precio de fondo decimal
    @Column(name = "fondo", precision = 10, scale = 2)
    private BigDecimal fondo;

    //Precio de largo decimal
    @Column(name = "largo", precision = 10, scale = 2)
    private BigDecimal largo;

    //Precio de capacidad decimal
    @Column(name = "capacidad")
    private Integer capacidad;

    //Precio de tipoCama decimal
    @Column(name = "tipoCama")
    private Integer tipoCama;

    //Precio de materialPrincipal 
    @Column(name = "materialPrincipal", nullable = false, length = 50)
    @NotBlank(message = "El material no puede estar vacio")
    private String materialPrincipal;

    //Precio de Venta decimal
    @Column(name = "detallesAcabado", length = 150)
    @NotBlank(message = "El material no puede estar vacio")
    private String detallesAcabado;

    //Fecha
    @Column(name = "fechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    //Estado
    @Column(name = "estado", nullable = false)
    private Byte estado = 1;
    
    @OneToMany(mappedBy = "producto")
    private List<ProductoImagen> images;
    
    
    public String getUrlPortada(){
        for(ProductoImagen img : images){
            if(img.getPortada()){
            return img.getUrlImagen();
            }
        }
        return "";
    }
    
    public String getRestImages(){
        for(ProductoImagen imge: images){
            if(!imge.getPortada()){
                return imge.getUrlImagen();
            }
        }
        return "";
    }
    
    public String getNonbreCategoria(){
        switch (categoria) {
            case 1: return "Mueble de Sala";
            case 2: return "Butaca";
            case 3: return "Dormitorio";
            case 4: return "Comedor";
            case 5: return "Armario";
            default: return "Sin categoria";
                
        }
    }
    
    public String getNombreEstado(){
        switch(estado){
            case 1: return "Activo";
            case 2: return "Inactivo";
            default: return "Sin estado";
        }
    }
    
    public List<ProductoImagen> getGaleria(){
        List<ProductoImagen> galeria = new ArrayList<>();
        
        for(ProductoImagen img : images){
            if(!img.getPortada()){
                galeria.add(img);
            }
        }
        return galeria;
    }
}
