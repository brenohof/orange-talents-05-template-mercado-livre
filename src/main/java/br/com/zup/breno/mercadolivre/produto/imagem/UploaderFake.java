package br.com.zup.breno.mercadolivre.produto.imagem;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Primary
public class UploaderFake implements Uploader {

    /**
     *
     * @param imagens
     * @return O link para as imagens que foram uploaded
     */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens.stream()
                .map(imagem ->
                        "http://bucket.io/"+
                                LocalDateTime.now().toString()+
                                imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
