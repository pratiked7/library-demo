package com.sessions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class LibraryTest {


    Library library;

    @BeforeEach
    public void setup(){
        //will run this everytime before any test
        library = new Library();
    }


    @Test
    public void the_default_number_of_books_in_library_should_be_1_initially(){

        int totalNumberOfBooks = library.getBooks().size();
        assertEquals(1, totalNumberOfBooks);
    }

    @Test
    public void adding_to_catalog_should_increase_the_size_of_books_and_newly_created_books_id_2(){

        Book newlyCreatedBook = library.addToCatalogue("Book 1", "Author", 500, 50.0);
        int totalBooks = library.getBooks().size();
        List<Book> bookList = library.getBooks();

        assertEquals(2, newlyCreatedBook.getId());
        assertThat(totalBooks, equalTo(2));
        assertThat(bookList, hasItem(newlyCreatedBook));
    }

    @Test
    public void findBookByName_called_with_bookname_available_in_library_should_return_book_object(){

        Book book = library.findBookByName("The God Of Small Things");
        assertNotNull(book);
    }

    @Test
    public void findBookByName_called_with_non_existent_bookname_should_return_null(){
        Book book = library.findBookByName("The God");
        assertNull(book);
    }

    @Test
    public void calculateBookRent_should_return_2_if_number_of_days_is_4(){

        RentedBook rentedBook = Mockito.mock(RentedBook.class);
        LocalDate fourDaysBeforeToday = LocalDate.now().minusDays(4);

        Mockito.when(rentedBook.getRentedDate()).thenReturn(fourDaysBeforeToday);

        Double calculatePrice = library.calculateBookRent(rentedBook);

        assertThat(calculatePrice, equalTo(2.0));

        //check whether getRentedDate is being called or not
        Mockito.verify(rentedBook, Mockito.times(2)).getRentedDate();
    }

    @Test
    public void calculateBookRent_should_return_6_if_number_of_days_is_6(){

        RentedBook rentedBook = Mockito.mock(RentedBook.class);
        LocalDate sixDaysBeforeToday = LocalDate.now().minusDays(6);

        Mockito.when(rentedBook.getRentedDate()).thenReturn(sixDaysBeforeToday);

        Double calculatePrice = library.calculateBookRent(rentedBook);

        assertThat(calculatePrice, equalTo(6.0));

        //check whether getRentedDate is being called or not
        Mockito.verify(rentedBook, Mockito.times(2)).getRentedDate();
    }
}