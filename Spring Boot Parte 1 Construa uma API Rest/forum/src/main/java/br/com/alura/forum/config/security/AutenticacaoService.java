package br.com.alura.forum.config.security;

import br.com.alura.forum.model.UsuarioEntity;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findByEmail(s);
        if (usuarioEntityOptional.isPresent()) {
            return usuarioEntityOptional.get();
        } else {
            throw new UsernameNotFoundException("Dados inválidos!");
        }
    }

}
