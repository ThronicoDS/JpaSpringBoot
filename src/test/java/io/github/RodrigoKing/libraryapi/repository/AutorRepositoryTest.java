package io.github.RodrigoKing.libraryapi.repository;

import io.github.RodrigoKing.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Test
    public void saveTest(){
        Autor autor = new Autor();
        autor.setName("João Gomes Luiz Pereira");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1969,11,7));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo com sucesso: " + autorSalvo);
    }

    @Test
    public void updateTest(){
       var id =  UUID.fromString("ab159f52-acdf-4488-9cb6-1a87babdd3cc");

       Optional<Autor> possivelAutor = repository.findById(id);

       if(possivelAutor.isPresent()){
           Autor autoencontrado = possivelAutor.get();
           System.out.println("Dados do autor");
           System.out.println("Nome: " + possivelAutor.get().getName()+", " + "id: " + possivelAutor.get().getId() +", "+ "Nacionalidade: " + possivelAutor.get().getNacionalidade());

           autoencontrado.setDataNasc(LocalDate.of(2000,9,23));
           autoencontrado.setName("Luca Gimenez Rodriguez Drumond ");
           autoencontrado.setNacionalidade("Argentino");
           repository.save(autoencontrado);
       }

    }

    @Test
    public void listTest() {
        List<Autor> lista  = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());

    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("b89b6e04-d055-4b82-a69c-96c8ecdbed42");

        Optional<Autor> possivelDeleteAutor = repository.findById(id);

        if(possivelDeleteAutor.isPresent()){
            Autor deleteAutor = possivelDeleteAutor.get();
            repository.deleteById(deleteAutor.getId());
            System.out.println("Autor deletado com sucesso!!");
        }else{
            System.out.println("Autor ja foi deletado ou não existe em nossa base de dados!!");
        }
    }

    @Test
    public void pesquisa(){
        var id = UUID.fromString("");


    }

}
