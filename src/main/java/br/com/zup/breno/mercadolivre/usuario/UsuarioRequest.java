package br.com.zup.breno.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank @Email
    private String login;
    @NotBlank @Size(min = 6) @isPasswordClean
    private String senha;

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, senha);
    }
}
