package com.example.projspringdatajpa.controller;

import com.example.projspringdatajpa.modelo.Book;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }



    @Test
    void guardarLibro() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//        String json= """
//                {
//                "title" : "libro creado",
//                "author" : "Yuval",
//                "pages" : 650,
//                "price" : 19.50,
//                "releaseDate" : "2019-12-12",
//                "online" : false
//                }
//                """;
//
//        HttpEntity<String> request = new HttpEntity<>(json,headers);
//      ResponseEntity<Book> response=  testRestTemplate.exchange("/guardar",HttpMethod.POST,request,Book.class);
//
//    Book result = response.getBody();
//        assertEquals(1L,result.getId());
//        assertEquals("libro creado",result.getTitle());
    }

    @Test
    void listarLibros() {
        ResponseEntity<Book[]> response=
        testRestTemplate.getForEntity("/listar",Book[].class);
     assertEquals(HttpStatus.OK,response.getStatusCode());
   assertEquals(200,response.getStatusCodeValue());

   List<Book> books= Arrays.asList(response.getBody());
        System.out.println(books.size());

    }

    @Test
    void buscarPorId() {
        ResponseEntity<Book> response = testRestTemplate.getForEntity("/buscar/1", Book.class);

        assertEquals(HttpStatus.UNAUTHORIZED,response.getStatusCode());

    }


}