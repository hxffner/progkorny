package hu.nye.albums.populator.impl;

import java.util.List;

import hu.nye.albums.model.Album;
import hu.nye.albums.model.Genres;
import hu.nye.albums.populator.DBPopulator;
import hu.nye.albums.repository.AlbumRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * Class that stores Album samples.
 */
@Component
@Slf4j
@Order(1)
public class AlbumInitializer implements DBPopulator {

    private static final List<Album> ALBUMS = List.of(
            new Album(1L, "To Pimp a Butterfly", "Kendrick Lamar", Genres.RAP, "2015",
                    "political, conscious"),
            new Album(2L,"Hybrid Theory","Linkin Park", Genres.ROCK,"2000",
                    "angry, energetic")
    );

    private final AlbumRepository albumRepository;

    public AlbumInitializer(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void populateDatabase() {
        log.info("Initialize Albums...");
        albumRepository.saveAll(ALBUMS);
        log.info("Finished initialization of Albums.");
    }
}

