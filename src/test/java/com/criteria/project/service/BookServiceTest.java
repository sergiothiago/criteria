package com.criteria.project.service;

import com.criteria.project.builder.BookBuilder;
import com.criteria.project.domain.Book;
import com.criteria.project.dto.BookDTO;
import com.criteria.project.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Mock
    private BookBuilder builder;

    private BookDTO bookDTO;

    private Book book;

    @BeforeEach
    public void setUp() {

        book = Book.builder()
                .id(1L)
                .build();

        bookDTO = BookDTO.builder()
                .id(1L)
                .build();

    }

//    @Test
//    void findAllWithCriteria_ExpectedNoException() {
//
//        when(repository.findAllWithCriteria(any(),any()))
//                .thenReturn( new Page<>(Collections.singletonList(book)));
//
//        assertDoesNotThrow(() -> service.findAllWithCriteria(bookDTO, null));
//    }



}
