package com.example.onlinebookstore.service.impl;

import com.example.onlinebookstore.dto.BookDto;
import com.example.onlinebookstore.dto.CreateBookRequestDto;
import com.example.onlinebookstore.exception.EntityNotFoundException;
import com.example.onlinebookstore.mapper.BookMapper;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.BookRepository;
import com.example.onlinebookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookRequestDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto get(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() ->
                        new EntityNotFoundException("There is not book in db by id %d"
                                .formatted(id))
                );
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookRequestDto) {
        Book existedBook = bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("There is not book in db by id %d"
                        .formatted(id)));
        bookMapper.updateBook(bookRequestDto, existedBook);
        return bookMapper.toDto(bookRepository.save(existedBook));
    }
}
