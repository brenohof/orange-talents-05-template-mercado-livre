package br.com.zup.breno.mercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Email
    private String login;
    @NotNull @Size(min = 6)
    private String senha;
    @NotNull @Future
    private LocalDateTime instanteDeCriacao;

    public Usuario(@NotNull String login, @NotNull SenhaLimpa senha) {
        Assert.isTrue(StringUtils.hasLength(login),"email não pode ser em branco");
        Assert.notNull(senha,"o objeto do tipo senha limpa nao pode ser nulo");

        this.login = login;
        this.senha = senha.hash();
        this.instanteDeCriacao = LocalDateTime.now().plusMinutes(5);
    }

}
