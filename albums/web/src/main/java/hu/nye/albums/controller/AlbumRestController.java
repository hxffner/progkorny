package hu.nye.albums.controller;

import java.util.List;

import hu.nye.albums.model.dto.AlbumDTO;
import hu.nye.albums.model.request.AlbumRequest;
import hu.nye.albums.model.response.AlbumResponse;
import hu.nye.albums.service.AlbumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * REST Controller for Albums.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
@Log4j2
public class AlbumRestController {

    private final AlbumService albumService;
    private final Converter<AlbumDTO, AlbumResponse> albumDtoToResponseConverter;
    private final Converter<AlbumRequest, AlbumDTO> albumRequestToDtoConverter;

    /**
     * Lists all Albums.
     *
     * @return album list.
     */
    @GetMapping
    public List<AlbumResponse> getAlbums() {
        log.info("Get all Albums");
        return albumService.getAllAlbums().stream()
                .map(albumDtoToResponseConverter::convert)
                .toList();
    }

    /**
     * Lists Album with {id}.
     *
     * @param id represents album id.
     * @return album with {id}.
     */
    @GetMapping("/{id}")
    public AlbumResponse getAlbum(final @PathVariable Long id) {
        log.info("Get an Album, ID:{}", id);
        return albumDtoToResponseConverter.convert(albumService.getAlbum(id));
    }

    /**
     * Adds album.
     *
     * @param albumRequest represents album request.
     * @return adds album to the list.
     */
    @PostMapping
    public AlbumResponse addAlbum(final @RequestBody AlbumRequest albumRequest) {
        log.info("Add a new Album, details: {}", albumRequest);
        return albumDtoToResponseConverter.convert(
                albumService.addAlbum(albumRequestToDtoConverter.convert(albumRequest))
        );
    }

    /**
     * Updates album.
     *
     * @param id represents album id.
     * @param albumRequest represents album request.
     * @return updates album with {id}.
     */
    @PutMapping("/{id}")
    public AlbumResponse updateAlbum(final @PathVariable Long id, final @RequestBody AlbumRequest albumRequest) {
        log.info("Update an Album with Id:{} to Album:{}", id, albumRequest);
        return albumDtoToResponseConverter.convert(
                albumService.updateAlbum(id, albumRequestToDtoConverter.convert(albumRequest))
        );
    }

    /**
     * Deletes album.
     *
     * @param id represents album id.
     * @return deletes album with {id}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(final @PathVariable Long id) {
        log.info("Delete an Album with ID:{}", id);
        albumService.deleteAlbum(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
