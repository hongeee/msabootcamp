package com.vroong.msabootcamp.adapter.in.rest;

import com.vroong.msabootcamp.adapter.in.rest.mapper.AlbumFactory;
import com.vroong.msabootcamp.adapter.in.rest.mapper.AlbumMapper;
import com.vroong.msabootcamp.adapter.in.rest.mapper.SongFactory;
import com.vroong.msabootcamp.application.port.in.AlbumService;
import com.vroong.msabootcamp.domain.Album;
import com.vroong.msabootcamp.rest.AlbumApiDelegate;
import com.vroong.msabootcamp.rest.AlbumDto;
import com.vroong.msabootcamp.rest.AlbumListDto;
import com.vroong.msabootcamp.rest.PageDto;
import com.vroong.msabootcamp.rest.SongDto;
import com.vroong.msabootcamp.support.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlbumApiDelegateImpl implements AlbumApiDelegate {

  private final AlbumFactory albumFactory;
  private final SongFactory songFactory;
  private final AlbumMapper mapper;
  private final AlbumService service;

  @Override
  public ResponseEntity<Void> addSong(Long albumId, SongDto songDto) {
    service.addSong(albumId, songFactory.createFrom(songDto));

    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> createAlbum(AlbumDto albumDto) {
    return ResponseEntity.created(
            HeaderUtils.uri(
                "/{albumId}", service.createAlbum(albumFactory.createFrom(albumDto)).getId()))
        .build();
  }

  @Override
  public ResponseEntity<AlbumListDto> listAlbums(Integer page, Integer size) {
    final Page<Album> pageEntityList = service.listAlbums(PageRequest.of(page - 1, size));

    return ResponseEntity.ok(
        new AlbumListDto()
            .data(mapper.toDto(pageEntityList.getContent()))
            .page(
                new PageDto()
                    .size(pageEntityList.getSize())
                    .totalElements(pageEntityList.getTotalElements())
                    .totalPages(pageEntityList.getTotalPages())
                    .number(pageEntityList.getNumber())));
  }

  @Override
  public ResponseEntity<Void> removeSong(Long albumId, Long songId) {
    service.removeSong(albumId, songId);

    return ResponseEntity.noContent().build();
  }
}
