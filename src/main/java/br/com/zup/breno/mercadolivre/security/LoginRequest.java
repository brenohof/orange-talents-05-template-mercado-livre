package br.com.zup.breno.mercadolivre.security;

import br.com.zup.breno.mercadolivre.validator.UniqueValue;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank @Email
    private String login;
    @NotBlank @Size(min = 6)
    private String senha;

    public LoginRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
