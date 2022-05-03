package hu.nye.albums.controller;

import hu.nye.albums.model.dto.AlbumDTO;
import hu.nye.albums.model.request.AlbumRequest;
import hu.nye.albums.model.response.AlbumResponse;
import hu.nye.albums.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
@Log4j2
public class AlbumRestController {

    private final AlbumService albumService;
    private final Converter<AlbumDTO, AlbumResponse> albumDtoToResponseConverter;
    private final Converter<AlbumRequest, AlbumDTO> albumRequestToDtoConverter;

    @GetMapping
    public List<AlbumResponse> getAlbums() {
        log.info("Get all Albums");
        return albumService.getAllAlbums().stream()
                .map(albumDtoToResponseConverter::convert)
                .toList();
    }

    @GetMapping("/{id}")
    public AlbumResponse getAlbum(final @PathVariable Long id) {
        log.info("Get an Album, ID:{}", id);
        return albumDtoToResponseConverter.convert(albumService.getAlbum(id));
    }

    @PostMapping
    public AlbumResponse addAlbum(final @RequestBody AlbumRequest albumRequest) {
        log.info("Add a new Album, details: {}", albumRequest);
        return albumDtoToResponseConverter.convert(
                albumService.addAlbum(albumRequestToDtoConverter.convert(albumRequest))
        );
    }

    @PutMapping("/{id}")
    public AlbumResponse updateAlbum(final @PathVariable Long id, final @RequestBody AlbumRequest albumRequest) {
        log.info("Update an Album with Id:{} to Album:{}", id, albumRequest);
        return albumDtoToResponseConverter.convert(
                albumService.updateAlbum(id, albumRequestToDtoConverter.convert(albumRequest))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(final @PathVariable Long id) {
        log.info("Delete an Album with ID:{}", id);
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
