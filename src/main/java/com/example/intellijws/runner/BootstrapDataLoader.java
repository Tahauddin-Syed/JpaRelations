package com.example.intellijws.runner;

import com.example.intellijws.domain.Author;
import com.example.intellijws.domain.Book;
import com.example.intellijws.domain.Publisher;
import com.example.intellijws.repo.AuthorRepo;
import com.example.intellijws.repo.BookRepo;
import com.example.intellijws.repo.PublisherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class BootstrapDataLoader implements CommandLineRunner {


    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final PublisherRepo publisherRepo;

    public BootstrapDataLoader(AuthorRepo authorRepo, BookRepo bookRepo, PublisherRepo publisherRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = publisherRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Publisher publisher = Publisher.builder()
                .name("SFG Publishing")
                .city("St Petersburg")
                .state("FL")
                .addressLine1("Street 1 Building 45")
                .addressLine2("Near Public School")
                .zip("500245")
                .build();


        Author johnThompson = Author.builder()
                .firstName("John")
                .lastName("Thompsom")
                .build();

        Book springFramwworkGuru = Book.builder()
                .title("Spring Framwwork Guru")
                .isbn("123456789")
                .publisher(publisher)
                .build();

        publisher.getBooks().add(springFramwworkGuru);

        johnThompson.getBooks().add(springFramwworkGuru);
        springFramwworkGuru.getAuthors().add(johnThompson);

        authorRepo.save(johnThompson);
        bookRepo.save(springFramwworkGuru);

        Author thorbeyJanssen = Author.builder()
                .firstName("Thorbey")
                .lastName("Janssen")
                .build();

        Book hibernateAndJpa = Book.builder()
                .title("Hibernate And JPA")
                .isbn("321654987")
                .publisher(publisher)
                .build();

        publisher.getBooks().add(hibernateAndJpa);

        Publisher savedPublisher = publisherRepo.save(publisher);

        thorbeyJanssen.getBooks().add(hibernateAndJpa);
        hibernateAndJpa.getAuthors().add(thorbeyJanssen);

        authorRepo.save(thorbeyJanssen);
        bookRepo.save(hibernateAndJpa);


        System.out.println("Data Loaded is " + authorRepo.count());
        System.out.println("Publisher Data Saved : " + publisherRepo.count());
    }
}
