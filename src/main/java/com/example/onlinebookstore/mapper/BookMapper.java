package com.example.onlinebookstore.mapper;

import com.example.onlinebookstore.config.MapperConfig;
import com.example.onlinebookstore.dto.book.BookDto;
import com.example.onlinebookstore.dto.book.BookDtoWithoutCategoryIds;
import com.example.onlinebookstore.dto.book.CreateBookRequestDto;
import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.model.Category;
import com.example.onlinebookstore.repository.CategoryRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto,
                 @Context CategoryRepository categoryRepository);

    void updateBook(@MappingTarget Book book, CreateBookRequestDto dto,
                    @Context CategoryRepository categoryRepository);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategories(@MappingTarget Book book,
                               CreateBookRequestDto bookDto,
                               @Context CategoryRepository categoryRepository) {
        book.setCategories(
                bookDto.categoryIdSet().stream()
                        .map(categoryRepository::findById)
                        .flatMap(Optional::stream)
                        .collect(Collectors.toSet()));
    }

    @AfterMapping
    default void setCategoryIdSet(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIdSet(
                book.getCategories()
                        .stream()
                        .map(Category::getId)
                        .collect(Collectors.toSet()));
    }
}
