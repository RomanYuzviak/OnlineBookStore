package com.example.onlinebookstore.controller;

import com.example.onlinebookstore.dto.book.BookDto;
import com.example.onlinebookstore.dto.book.CreateBookRequestDto;
import com.example.onlinebookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book CRUD API", description = "Endpoints to manage books")
@RestController
@RequestMapping(value = "/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get all books")
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "get a book by id")
    public BookDto getById(@PathVariable Long id) {
        return bookService.get(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "create a new book")
    public BookDto save(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update a book by id")
    BookDto update(@PathVariable Long id, @RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.update(id, requestDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "delete a book by id")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
