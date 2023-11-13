package com.example.onlinebookstore.service;

import com.example.onlinebookstore.dto.book.BookDto;
import com.example.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll(Pageable pageable);

    BookDto get(Long id);

    void delete(Long id);

    BookDto update(Long id, CreateBookRequestDto bookRequestDto);

    List<BookDtoWithoutCategoryIds> findBooksByCategoryId(Long id, Pageable pageable);
}
