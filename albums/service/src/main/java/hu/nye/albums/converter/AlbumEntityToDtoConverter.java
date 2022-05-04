package hu.nye.albums.converter;

import hu.nye.albums.model.Album;
import hu.nye.albums.model.dto.AlbumDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converts Entities to DTOs.
 */
@Component
@Slf4j
public class AlbumEntityToDtoConverter implements Converter<Album, AlbumDTO> {

    @Override
    public AlbumDTO convert(@NonNull final Album source) {
        log.info("Convert Album:{} to AlbumDTO.", source);
        return new AlbumDTO(source.getId(),
                source.getName(),
                source.getArtist(),
                source.getGenre(),
                source.getDate(),
                source.getDescription()
        );
    }
}
