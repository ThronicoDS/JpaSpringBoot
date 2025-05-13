package io.github.RodrigoKing.libraryapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro", schema = "public")
@Data
@Getter
@Setter
@ToString
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

    public Livro() {

    }

    public Livro(String uuid, String caminhosDoVento, String s, BigDecimal bigDecimal, GeneroLivro generoLivro, LocalDate of) {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCodigo_livro() {
        return codigo_livro;
    }

    public void setCodigo_livro(String codigo_livro) {
        this.codigo_livro = codigo_livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataPublic() {
        return dataPublic;
    }

    public void setDataPublic(LocalDate dataPublic) {
        this.dataPublic = dataPublic;
    }

    public GeneroLivro getGenero() {
        return genero;
    }

    public void setGenero(GeneroLivro genero) {
        this.genero = genero;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

//    @Override
//    public String toString() {
//        return "Livro{" +
//                "id=" + id +
//                ", codigo_livro='" + codigo_livro + '\'' +
//                ", titulo='" + titulo + '\'' +
//                ", dataPublic=" + dataPublic +
//                ", genero=" + genero +
//                ", preco=" + preco +
//                ", autor=" + autor +
//                '}';
//    }
}
