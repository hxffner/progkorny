package hu.nye.albums.converter;

import hu.nye.albums.model.dto.AlbumDTO;
import hu.nye.albums.model.response.AlbumResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AlbumDtoToResponseConverter implements Converter<AlbumDTO, AlbumResponse> {

    @Override
    public AlbumResponse convert(@NonNull final AlbumDTO source) {
        log.info("Convert AlbumDTO:{} to AlbumResponse.", source);
        return new AlbumResponse(
                source.id(),
                source.name(),
                source.artist(),
                source.genre(),
                source.date(),
                source.description()
        );
    }
}
