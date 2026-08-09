
package com.Forniture.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


@Data 
@Entity
@Table(name = "Section")
public class Section implements Serializable{
    
    private static final long serialVersionUID =1L;
    
    //ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SectionID")
    private Integer SectionID;
    
    @Column(name = "nombreSection")
    private String nombreSection;
    
    @Column(name = "sectionByte")
    private Byte sectionByte;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "productoID")
    private Producto producto;
    
}
