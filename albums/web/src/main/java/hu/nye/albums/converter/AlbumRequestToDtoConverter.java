package hu.nye.albums.converter;

import hu.nye.albums.model.dto.AlbumDTO;
import hu.nye.albums.model.request.AlbumRequest;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlbumRequestToDtoConverter implements Converter<AlbumRequest, AlbumDTO> {

    @Override
    public AlbumDTO convert(@NonNull final AlbumRequest source) {
        log.info("Convert AlbumRequest:{} to AlbumDTO.", source);
        return new AlbumDTO(null,
                source.getName(),
                source.getArtist(),
                source.getGenre(),
                source.getDate(),
                source.getDescription()
        );
    }
}
