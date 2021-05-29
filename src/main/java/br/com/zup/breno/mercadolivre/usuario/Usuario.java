package br.com.zup.breno.mercadolivre.usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Email @Column(unique = true)
    private String login;
    @NotNull @Size(min = 6)
    private String senha;
    @NotNull @Future
    private LocalDateTime instanteDeCriacao;

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotNull String login, @NotNull SenhaLimpa senha) {
        Assert.isTrue(StringUtils.hasLength(login),"email não pode ser em branco");
        Assert.notNull(senha,"o objeto do tipo senha limpa nao pode ser nulo");

        this.login = login;
        this.senha = senha.hash();
        this.instanteDeCriacao = LocalDateTime.now().plusMinutes(5);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(instanteDeCriacao, usuario.instanteDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, senha, instanteDeCriacao);
    }

    public String getEmail() {
        return login;
    }
}
