package br.com.zup.breno.mercadolivre;

import br.com.zup.breno.mercadolivre.categoria.Categoria;
import br.com.zup.breno.mercadolivre.categoria.CategoriaRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

public class NovaCategoriaRequestTest {

    @Test
    @DisplayName("deveria cadastrar categoria sem mae")
    void teste1() throws Exception {
        CategoriaRequest request = new CategoriaRequest("nome", null);

        EntityManager manager = Mockito.mock(EntityManager.class);
        request.toModel(manager);

        Mockito.verify(manager, Mockito.never())
                .find(Mockito.eq(Categoria.class), Mockito.anyLong());
    }

    @Test
    @DisplayName("deveria cadastrar categoria com mae")
    void teste2() throws Exception {
        CategoriaRequest request = new CategoriaRequest("nome", 1L);

        EntityManager manager = Mockito.mock(EntityManager.class);
        Categoria categoriaMae = new Categoria("teste");

        Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(categoriaMae);
        request.toModel(manager);

        Mockito.verify(manager).find(Categoria.class, 1l);
    }
}