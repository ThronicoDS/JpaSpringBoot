package io.github.RodrigoKing.libraryapi.repository;

import io.github.RodrigoKing.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
