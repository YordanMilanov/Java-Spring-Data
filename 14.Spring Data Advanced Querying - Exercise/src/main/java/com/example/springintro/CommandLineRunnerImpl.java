package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();
        Scanner scanner = new Scanner(System.in);

        //printAllBooksAfterYear(2000);

        /*
        final String ageRestrictionType = scanner.nextLine();
        final AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionType.toUpperCase());

        List<Book> allByAgeRestriction = this.bookService.findAllByAgeRestriction(ageRestriction);

        if (!allByAgeRestriction.isEmpty()) {

            for (Book book : allByAgeRestriction) {
                System.out.println(book.getTitle().toString());
            }
        }
        */


        //2. Golden Books
/*
        List<Book> allByEditionTypeAndCopiesLessThan = this.bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);
        allByEditionTypeAndCopiesLessThan.forEach(b -> System.out.println(b.getTitle()));
*/
        //3. Books By Price
        /*
        List<Book> allByPriceLessThanOrPriceGreaterThan = this.bookService.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
        allByPriceLessThanOrPriceGreaterThan.forEach(book -> System.out.printf("%s - $%.2f %n",book.getTitle(),book.getPrice().doubleValue()));
        */

        //4.NotReleasedBooks
        /*
        this.bookService.findAllByReleaseDateNot(LocalDate.of(2000,1,1))
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
                */
        //printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);

        //5.BooksReleasedBefore
        /*
        String [] dateInfo = scanner.nextLine().split("-");
        int day = Integer.parseInt(dateInfo[0]);
        int month = Integer.parseInt(dateInfo[1]);
        int year = Integer.parseInt(dateInfo[2]);
        this.bookService.findAllByReleaseDateBefore(LocalDate.of(year,month,day))
                .stream()
                .map(book -> book.getTitle() + " " + book.getEditionType().toString() + " " + book.getPrice().doubleValue())
                .forEach(System.out::println);
                */

        //   printAllAuthorsAndNumberOfTheirBooks();
        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
