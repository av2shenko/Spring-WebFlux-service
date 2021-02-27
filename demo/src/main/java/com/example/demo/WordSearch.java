package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

//import javax.persistence.*;

//@Entity
@Data //@Getter @Setter All generated getters and setters will be public https://projectlombok.org/features/Data
@NoArgsConstructor
public class WordSearch {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String word;
    private String path;

    public WordSearch(String word, String path) {
        this.word = word;
        this.path = path;
    }

    @Override
    public String toString() {
        return "WordSearch{" + "id=" + id + ", word=" + word + ", path=" + path + "}";
    }
}