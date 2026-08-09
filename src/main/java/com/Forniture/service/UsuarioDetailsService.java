package com.Forniture.service;

import com.Forniture.domain.Usuarios;
import com.Forniture.repository.UsuariosRepository;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService{

    private UsuariosRepository usuariosRepository;

    public UsuarioDetailsService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Usuarios usuario = usuariosRepository
                .findByCorreo(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException(
                        "Usuario no encontrado: " + username
                )
                );

        var authorities = List.of(
                new SimpleGrantedAuthority(
                        "ROLE_" + usuario.getRoles().getRolesNombre()
                )
        );

        return new User(
                usuario.getCorreo(),
                usuario.getContrasenna(),
                true,
                true,
                true,
                true,
                authorities
        );
    }

}
