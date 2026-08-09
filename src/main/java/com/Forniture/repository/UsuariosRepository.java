
package com.Forniture.repository;

import com.Forniture.domain.Usuarios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer>{
    
    Optional<Usuarios> findByCorreo(String correo);
}
