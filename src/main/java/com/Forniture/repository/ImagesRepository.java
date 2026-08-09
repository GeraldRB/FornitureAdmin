
package com.Forniture.repository;

import com.Forniture.domain.ProductoImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<ProductoImagen, Integer>{
    
    ProductoImagen findByProductoProductoIDAndPortada(Long productoID, Boolean portda);
    
}
