package io.github.RodrigoKing.libraryapi.repository;


import io.github.RodrigoKing.libraryapi.model.Autor;
import io.github.RodrigoKing.libraryapi.model.GeneroLivro;
import io.github.RodrigoKing.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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

    static class LivroCadastro {
        String autorId; // UUID em string
        String titulo;
        String codigoLivro;
        BigDecimal preco;
        GeneroLivro genero;
        LocalDate dataPublicacao;

        public LivroCadastro(String autorId, String titulo, String codigoLivro,
                             BigDecimal preco, GeneroLivro genero, LocalDate dataPublicacao) {
            this.autorId = autorId;
            this.titulo = titulo;
            this.codigoLivro = codigoLivro;
            this.preco = preco;
            this.genero = genero;
            this.dataPublicacao = dataPublicacao;
        }
    }
    @Test
    void cadastrarLivrosComAutores() {
        List<LivroCadastro> cadastros = List.of(
                new LivroCadastro("44e7022b-2ba8-4ad8-a03d-f42516731e24", "Entre Luzes e Sombras", "2305635-987654", new BigDecimal("88.00"), GeneroLivro.ROMANCE, LocalDate.of(2011, 3, 18)),
                new LivroCadastro("4aae7dee-417d-40a4-bf5d-023f18010eaf", "Exploradores do Abismo", "2305635-246810", new BigDecimal("72.35"), GeneroLivro.AVENTURA, LocalDate.of(2013, 9, 5)),
                new LivroCadastro("207dbaf0-b90c-4a9d-a191-7f3a7537a677", "A Verdade Oculta", "2305635-112358", new BigDecimal("102.99"), GeneroLivro.SUSPENSE, LocalDate.of(2016, 2, 11)),
                new LivroCadastro("ab159f52-acdf-4488-9cb6-1a87babdd3cc", "No Rastro das Estações", "2305635-777777", new BigDecimal("61.00"), GeneroLivro.DRAMA, LocalDate.of(2009, 10, 23)),
                new LivroCadastro("92913e66-a2e9-4063-bcf2-e62838086c89", "O Medo Invisível", "2305635-999999", new BigDecimal("84.75"), GeneroLivro.TERROR, LocalDate.of(2018, 7, 2)),
                new LivroCadastro("dd9f9a44-17ac-42b3-9157-361e245afeff", "Corações em Exílio", "2305635-123123", new BigDecimal("115.90"), GeneroLivro.ROMANCE, LocalDate.of(2006, 6, 30))
        );

        for (LivroCadastro cadastro : cadastros) {
            try {
                UUID autorUuid = UUID.fromString(cadastro.autorId);
                Autor autor = autorRepository.findById(autorUuid)
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado: " + cadastro.autorId));

                Livro livro = new Livro();
                livro.setCodigo_livro(cadastro.codigoLivro);
                livro.setTitulo(cadastro.titulo);
                livro.setPreco(cadastro.preco);
                livro.setGenero(cadastro.genero);
                livro.setDataPublic(cadastro.dataPublicacao);
                livro.setAutor(autor);

                Livro salvo = livroRepository.save(livro);
                System.out.println("✅ Livro salvo: " + salvo.getTitulo() + " | Autor: " + autor.getName());

            } catch (Exception e) {
                System.out.println("❌ Erro ao cadastrar livro \"" + cadastro.titulo + "\": " + e.getMessage());
            }
        }
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
    @Test
    public void listTest() {
        List<Livro> lista  = livroRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Quantidade de livros cadastrados: " + livroRepository.count());
    }
}