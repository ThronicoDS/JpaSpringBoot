package io.github.RodrigoKing.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro", schema = "public")
@Data

//@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String codigo_livro;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao",nullable = false)
    private LocalDate dataPublic;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco",precision = 18,scale = 2 ,nullable = false)
    private BigDecimal preco;
    // private BigDecimal e melhor para trabalhar quando for usar valor monetario

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

}
