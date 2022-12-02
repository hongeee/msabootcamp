package com.vroong.msabootcamp.adapter.in.rest.mapper;

import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.domain.Singer;
import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.rest.AlbumDto;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumFactory {

  private final DateTimeMapper dateTimeMapper;

  public Album createFrom(AlbumDto dto) {
    final Album entity = new Album();

    entity.setTitle(dto.getTitle());
    entity.setPublished(dateTimeMapper.toInstant(dto.getPublished()));

    if (dto.getSinger() != null) {
      final Singer singer = new Singer();

      singer.setId(dto.getSinger().getSingerId());
      singer.setName(dto.getSinger().getName());
      entity.setSinger(singer);
    }

    if (dto.getSongs() != null && !dto.getSongs().isEmpty()) {
      dto.getSongs()
          .forEach(
              s -> {
                final Song song = new Song();

                song.setTitle(s.getTitle());
                song.setPlayTime(Duration.parse(s.getPlayTime()));
                entity.addSong(song);
              });
    }

    return entity;
  }
}
