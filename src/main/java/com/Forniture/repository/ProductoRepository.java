
package com.Forniture.repository;

import com.Forniture.domain.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
  
    public List<Producto> findByEstadoTrue();
    
    List<Producto> findByCategoriaAndEstado(Integer categoria, Byte estado);
    
    List<Producto> findByCategoria(Integer categoria);
    
    Optional<Producto> findBySkuReferencia(String skuReferencia);
}
