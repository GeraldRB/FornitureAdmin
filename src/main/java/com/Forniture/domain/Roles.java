
package com.Forniture.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
@Table(name="Roles")
public class Roles {
    
    private static final long serialVersionUID =1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rolesID")
    private Integer rolesID;
    
    
    @Column(name="rolesNombre")
    private String rolesNombre;
    
    @Column(name="fechaCreacion")
    private LocalDateTime fechaCreacion;
    
    
}
