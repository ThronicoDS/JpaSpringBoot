package io.github.RodrigoKing.libraryapi.repository;

import io.github.RodrigoKing.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Test
    public void saveTest(){
        Autor autor = new Autor();
        autor.setName("João Ribeiro");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNasc(LocalDate.of(1969,10,27));

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
}
