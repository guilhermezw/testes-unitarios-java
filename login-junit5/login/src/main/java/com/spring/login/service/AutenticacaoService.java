package com.spring.login.service;

import com.spring.login.exception.custom.UsuarioNaoAutenticadoException;
import com.spring.login.model.UsuarioModel;
import com.spring.login.repository.UsuarioRepository;
import com.spring.login.security.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel getUsuarioAutenticado(){
        UUID id = SecurityUtils.getUsuarioId();
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoAutenticadoException("Usuário autenticado não encontrado"));
    }
}
