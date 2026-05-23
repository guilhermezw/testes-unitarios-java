package com.spring.login.service;

import com.spring.login.dto.PerfilResponseDTO;
import com.spring.login.enums.Role;
import com.spring.login.model.UsuarioModel;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    private final AutenticacaoService autenticacaoService;

    public ViewService(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    public String exibirMensagem(){
       UsuarioModel usuario = autenticacaoService.getUsuarioAutenticado();
       Role role = usuario.getRole();
       return switch (role) {
           case ADMIN -> "Bem-vindo administrador: " + usuario.getNome() + "!";
           case CLIENTE ->  "Bem-vindo cliente: " + usuario.getNome() + "!";
           case GERENTE ->  "Bem-vindo gerente: " + usuario.getNome() + "!";
       };
    }


    public PerfilResponseDTO exibirMinhasInformações() {
        UsuarioModel usuario = autenticacaoService.getUsuarioAutenticado();
        return new PerfilResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }


}
