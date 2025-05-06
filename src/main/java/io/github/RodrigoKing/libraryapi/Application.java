package io.github.RodrigoKing.libraryapi;

import io.github.RodrigoKing.libraryapi.model.Autor;
import io.github.RodrigoKing.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

		exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setName("Lucas Pereira");
		autor.setNacionalidade("Argentina");
		autor.setDataNasc(LocalDate.of(1980,5,15));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor salvo com sucesso: " + autorSalvo);

	}

}
