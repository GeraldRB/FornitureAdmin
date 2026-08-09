
package com.Forniture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="Usuarios")
public class Usuarios {
    
    private static final long serialVersionUID =1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usuarioID")
    private Integer usuarioID;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="correo")
    private String correo;
    
    @Column(name="contrasenna")
    private String contrasenna;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "rolesID")
    private Roles roles;
}
