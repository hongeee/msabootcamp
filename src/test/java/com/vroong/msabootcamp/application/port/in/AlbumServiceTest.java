package com.vroong.msabootcamp.application.port.in;

import static com.vroong.msabootcamp.domain.Fixtures.aSong;
import static com.vroong.msabootcamp.domain.Fixtures.anAlbum;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.vroong.msabootcamp.application.port.out.AlbumRepository;
import com.vroong.msabootcamp.domain.Album;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
@Slf4j
class AlbumServiceTest {

  @MockBean private AlbumRepository repository;
  @Autowired private AlbumService service;

  @Test
  void createAlbum() {
    when(repository.save(any(Album.class))).thenReturn(anAlbum());

    final Album album = service.createAlbum(anAlbum());

    log.info("{}", album);
  }

  @Test
  void listAlbum() {
    final Pageable pageable = PageRequest.of(0, 1);
    Page<Album> pageEntityList = new PageImpl<>(List.of(anAlbum()), pageable, 1L);
    when(repository.findAll(any(Pageable.class))).thenReturn(pageEntityList);

    pageEntityList = service.listAlbums(pageable);

    log.info("{}", pageEntityList);
  }

  @Test
  void addSong() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(anAlbum()));

    service.addSong(1L, aSong());
  }

  @Test
  void removeSong() {
    when(repository.findById(anyLong())).thenReturn(Optional.of(anAlbum()));

    service.removeSong(1L, 1L);
  }
}
