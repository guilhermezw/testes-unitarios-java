package com.spring.login.service;

import com.spring.login.dto.CadastroRequestDTO;
import com.spring.login.dto.LoginRequestDTO;
import com.spring.login.model.UsuarioModel;
import com.spring.login.repository.UsuarioRepository;
import com.spring.login.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String login (LoginRequestDTO dto) {
        UsuarioModel usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new BadCredentialsException("Email ou senha inválidos");
        }
       return jwtService.generateToken(usuario);
    }

    public UsuarioModel cadastrarUsuario (CadastroRequestDTO dto) {
        if(usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já cadastrado em outra conta");
        }
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(dto.getRole());
        usuario.setAtivo(true);
        return usuarioRepository.save(usuario);
    }


}
