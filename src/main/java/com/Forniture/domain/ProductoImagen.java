
package com.Forniture.domain;

import com.google.api.client.util.DateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "ProductoImagen")

public class ProductoImagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagenID")
    private Integer imagenID;

    @Column(name = "urlImagen", length = 1000)
    @NotNull(message = "Este campo no puede estar vacio.")
    private String urlImagen;

    @Column(name = "nombreArchivo")
    private String nombreArchivo;

    @Column(name = "portada")
    private Boolean portada;

    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    
    @ManyToOne(optional = false)
    @JoinColumn(name= "productoID", nullable = false)
    private Producto producto;
    
}
