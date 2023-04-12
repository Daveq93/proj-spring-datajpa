package com.example.projspringdatajpa.modelo;

import io.swagger.annotations.ApiModel;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name="book")
@ApiModel("Clase Book para representar un objeto Libro")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "book_title")
    private String title;
    @Column(name = "book_autor")
    private String autor;
    @Column(name = "book_pages")
    private Integer pages;
    @Column(name = "book_price")
    private BigDecimal price;
    @Column(name = "book_release_date")
    private LocalDate releaseDate;
    @Column(name = "book_online")
    private Boolean online;


}
