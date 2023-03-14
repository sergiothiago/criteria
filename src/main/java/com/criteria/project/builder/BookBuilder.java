package com.criteria.project.builder;

import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookBuilder {

    public static BookDTO entityToDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .legacyId(book.getLegacyId())
                .authorId(Objects.nonNull(book.getAuthor()) ? book.getAuthor().getId() : null)
                .authorLegacyId(Objects.nonNull(book.getAuthor()) ? book.getAuthor().getLegacyId() : null)
                .build();
    }

    public Book dtoToEntity(BookDTO bookDTO) {

        return dtoToEntity(bookDTO, Book.builder().build());

    }

    public Book dtoToEntity(BookDTO bookDTO, Book book) {
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setLegacyId(bookDTO.getLegacyId());

        return book;
    }


}
