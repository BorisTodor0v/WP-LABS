package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.*;
import mk.finki.ukim.mk.lab.converter.AuthorFullNameConverter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "authors", schema = "public")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Convert(converter = AuthorFullNameConverter.class)
    private AuthorFullName fullName;

    /*
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;
    */

    @Column
    private String biography;

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    public Author(String name, String surname, String biography, LocalDate dateOfBirth) {
        this.fullName = new AuthorFullName(name, surname);
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }
}
