package br.com.zup.breno.mercadolivre.config.security;


import br.com.zup.breno.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Component
public class LoadUser implements UserDetailsService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Query query = entityManager.createQuery("select u from Usuario u where login = :login");
        query.setParameter("login", login);

        List<Usuario> usuarios = query.getResultList();

        Assert.state(usuarios.size()<=1, "[BUG] Existe mais de um usuário com o mesmo login.");
        Usuario usuario = usuarios.get(0);

        if (usuario != null) {
            return usuario;
        }

        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
