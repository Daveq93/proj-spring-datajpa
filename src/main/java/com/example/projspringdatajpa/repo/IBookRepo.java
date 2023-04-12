package com.example.projspringdatajpa.repo;

import com.example.projspringdatajpa.modelo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepo extends JpaRepository<Book,Long> {
}
