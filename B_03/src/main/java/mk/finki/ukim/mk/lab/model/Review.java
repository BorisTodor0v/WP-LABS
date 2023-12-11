package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeStamp;

    public Review(Integer score, String description, Book book, LocalDateTime timeStamp){
        this.score = score;
        this.description = description;
        this.book = book;
        this.timeStamp = timeStamp;
    }

    public String toString(){
        //Racno formatirano
        return timeStamp.getDayOfMonth() + "-" + timeStamp.getMonthValue() + "-" + timeStamp.getDayOfMonth() + " "
                + timeStamp.getHour() + ":" + timeStamp.getMinute() + ":" + timeStamp.getSecond() + " | " + score.toString()
                + " | " + description;
    }

}
