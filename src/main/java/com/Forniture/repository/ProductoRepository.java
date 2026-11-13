
package com.Forniture.repository;

import com.Forniture.domain.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
  
    List<Producto> findByEstado(Byte estado);
    
    List<Producto> findByCategoriaAndEstado(Integer categoria, Byte estado);
    
    List<Producto> findByCategoria(Integer categoria);
    
    Optional<Producto> findBySkuReferencia(String skuReferencia);

    @Query("SELECT p.skuReferencia FROM Producto p WHERE p.skuReferencia LIKE CONCAT(:prefijo, '%')")
    List<String> findSkusByPrefijo(@Param("prefijo") String prefijo);
}
