package io.github.RodrigoKing.libraryapi.repository;


import io.github.RodrigoKing.libraryapi.model.Autor;
import io.github.RodrigoKing.libraryapi.model.GeneroLivro;
import io.github.RodrigoKing.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void saveTest(){
        Livro livro = new Livro();
        livro.setCodigo_livro("9085435-764209");
        livro.setTitulo("Casa de papel");
        livro.setPreco(BigDecimal.valueOf(120.50));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setDataPublic(LocalDate.of(2000,11,28));

        //Vendo se existe esse autor no banco de dados
        Autor autor = autorRepository.
                findById(UUID.fromString("ab159f52-acdf-4488-9cb6-1a87babdd3cc"))
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        livro.setAutor(autor);
        Livro livroSalvo = livroRepository.save(livro);
        System.out.println("Dados do livro");
        System.out.println(
                "Id: " + livroSalvo.getId() + ", " + "Codigo livro: " + livroSalvo.getCodigo_livro() + ", " + "titulo: " + livroSalvo.getTitulo() + ", " + "preço: " + livroSalvo.getPreco() + ", " + "genero: " + livroSalvo.getGenero() + ", " + "Data de publicação: " + livroSalvo.getDataPublic() + ", " + "Id autor: " + livroSalvo.getAutor()
                );
        //✅ Verificar se o Codigo_livro foi gerado
//        assertNotNull(livroSalvo.getCodigo_livro());
//        assertEquals("9085435-345890",livroSalvo.getCodigo_livro());
    }

    @Test
    public void updateLivro(){
        var id = UUID.fromString("97eb5332-1f51-4587-bb38-a11ea8fe8a2a");

        Optional<Livro> livroAtualizado = livroRepository.findById(id);

        if(livroAtualizado.isPresent()){
            Livro livroEncontrado = livroAtualizado.get();

            livroEncontrado.setCodigo_livro("2085435-128390");
            livroRepository.save(livroEncontrado);
            System.out.println("Dados Atualizados com sucesso!!");
            System.out.println(livroEncontrado);
        }else{
            System.out.println("Aconteceu lago de errado na atualização dos dados:(");
        }
    }
}