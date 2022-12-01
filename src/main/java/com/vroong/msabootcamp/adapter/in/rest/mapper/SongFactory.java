package com.vroong.msabootcamp.adapter.in.rest.mapper;

import com.vroong.msabootcamp.domain.Song;
import com.vroong.msabootcamp.rest.SongDto;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SongFactory {

  public Song createFrom(SongDto dto) {
    final Song entity = new Song();

    entity.setTitle(dto.getTitle());
    entity.setPlayTime(Duration.parse(dto.getPlayTime()));

    return entity;
  }

  public Set<Song> createFrom(List<SongDto> dtoList) {
    final Set<Song> entitySet = new HashSet<>();

    if (dtoList == null) {
      return entitySet;
    }

    dtoList.forEach(d -> entitySet.add(createFrom(d)));

    return entitySet;
  }
}
