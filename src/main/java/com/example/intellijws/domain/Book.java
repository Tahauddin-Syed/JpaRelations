package com.example.intellijws.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "BOOK_TBL")
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ISBN")
    private String isbn;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "AUTHOR_BOOK", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "ID"),
                inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "ID")
    )
    private Set<Author> authors = new HashSet<Author>();

    @ManyToOne
    private Publisher publisher;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return id != null ? id.equals(book.id) : book.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
