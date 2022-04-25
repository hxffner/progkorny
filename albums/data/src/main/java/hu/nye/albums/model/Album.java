package hu.nye.albums.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
public class Album {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String artist;

    private String genre;

    private String date;

    private String description;
}

