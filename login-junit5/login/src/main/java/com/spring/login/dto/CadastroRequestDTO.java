package com.spring.login.dto;

import com.spring.login.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CadastroRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @Email(message = "E-mail inválido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{10,12}$",
            message = "A senha deve conter entre 10 e 12 caracteres, uma letra maiúscula, uma minúscula, um número e um caractere especial."
    )
    private String senha;

    private Role role;

    public CadastroRequestDTO(String nome, String email, String senha, Role role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public @NotBlank(message = "O nome é obrigatório.") String getNome() {
        return nome;
    }

    public void setNome(@NotBlank(message = "O nome é obrigatório.") String nome) {
        this.nome = nome;
    }

    public @Email(message = "E-mail inválido.") @NotBlank(message = "O e-mail é obrigatório.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "E-mail inválido.") @NotBlank(message = "O e-mail é obrigatório.") String email) {
        this.email = email;
    }

    public @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{10,12}$",
            message = "A senha deve conter entre 10 e 12 caracteres, uma letra maiúscula, uma minúscula, um número e um caractere especial."
    ) String getSenha() {
        return senha;
    }

    public void setSenha(@Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.#_-])[A-Za-z\\d@$!%*?&.#_-]{10,12}$",
            message = "A senha deve conter entre 10 e 12 caracteres, uma letra maiúscula, uma minúscula, um número e um caractere especial."
    ) String senha) {
        this.senha = senha;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}