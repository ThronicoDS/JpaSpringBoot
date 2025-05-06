package io.github.RodrigoKing.libraryapi.repository;

import io.github.RodrigoKing.libraryapi.Application;
import io.github.RodrigoKing.libraryapi.model.Autor;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
