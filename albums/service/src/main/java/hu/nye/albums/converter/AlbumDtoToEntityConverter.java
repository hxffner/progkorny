package hu.nye.albums.converter;

import hu.nye.albums.model.Album;
import hu.nye.albums.model.dto.AlbumDTO;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;


@Component
@Slf4j
public class AlbumDtoToEntityConverter implements Converter<AlbumDTO, Album> {

    @Override
    public Album convert(@NonNull final AlbumDTO source) {
        log.info("Convert AlbumDTO:{} to Album.", source);
        return new Album(source.id(),
                source.name(),
                source.artist(),
                source.genre(),
                source.date(),
                source.description()
        );
    }
}
