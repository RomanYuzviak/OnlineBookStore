package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.dto.book.BookDto;
import com.example.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.book.CreateBookRequestDto;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.Category;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    void updateBook(CreateBookRequestDto dto, @MappingTarget Book book);

    @AfterMapping
    default void setCategoryIdSet(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIdSet(
                book.getCategories()
                        .stream()
                        .map(Category::getId)
                        .collect(Collectors.toSet()));
    }
}
