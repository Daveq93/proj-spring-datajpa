package com.example.projspringdatajpa.controller;

import com.example.projspringdatajpa.modelo.Book;
import com.example.projspringdatajpa.repo.IBookRepo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);
    @Autowired
    private IBookRepo bookRepo;

    @PostMapping("/guardar")
    public ResponseEntity<Book> guardarLibro(@RequestBody Book libro, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));//sirve para mostrar desde que sistema se esta haciendo la peticion
        return ResponseEntity.ok(this.bookRepo.save(libro));
    }


    @GetMapping("/listar")
    @ApiOperation("Metodo para listar todos  los libros registrados en la base de datos")
    public List<Book> listarLibros() {
        List<Book> libros =this.bookRepo.findAll();
        System.out.println("-----------------------------------------");
        libros.forEach(System.out::println);
        return libros;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Book> buscarPorId(@ApiParam("ID de busqueda") @PathVariable(name = "id") Long id) {
        Optional<Book> bookOp = this.bookRepo.findById(id);
        if (bookOp.isPresent())
            return ResponseEntity.ok(bookOp.get());
        else
            return null;
        //return bookOp.map(ResponseEntity::ok).orElse(() -> ResponseEntity.notFound().build());
    }


    /*
     * ACTUALIZAR
     * */

    @PutMapping("/actualizar")
    public ResponseEntity<Book> actualizarLibro(@RequestBody Book book) {
        if (book.getId() == null) {
            LOG.warn("Tratando de actualizar sin ingresar el ID");
            return ResponseEntity.badRequest().build();
        }
        if (!bookRepo.existsById(book.getId())) {
            LOG.warn("El ID ingresado no existe");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(this.bookRepo.save(book));
    }

    /*
     * DELETE
     * */

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Book> eliminarLibro(@PathVariable("id") Long id) {
        if (!bookRepo.existsById(id)) {
            LOG.warn("El ID ingresado no existe");
            return ResponseEntity.notFound().build();
        }
        this.bookRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Book> deleteAll(){
        LOG.debug("Eliminando todos los registros de libros");
        this.bookRepo.deleteAll();
        return ResponseEntity.noContent().build();
    }



}
