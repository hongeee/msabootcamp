package com.vroong.msabootcamp.application.port.out;

import static com.vroong.msabootcamp.domain.Fixtures.anAlbum;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.vroong.msabootcamp.domain.Album;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class AlbumRepositoryTest {

  @Autowired private SingerRepository singerRepository;

  @Autowired private AlbumRepository albumRepository;

  @Test
  void save() {
    Album album = anAlbum();
    singerRepository.save(album.getSinger());
    albumRepository.save(album);

    assertNotNull(album.getId());
    assertNotNull(album.getTitle());
    assertNotNull(album.getPublished());
    assertNotNull(album.getSinger().getId());
    album.getSongs().forEach(s -> assertNotNull(s.getId()));

    log.info("{}", album);
  }
}
