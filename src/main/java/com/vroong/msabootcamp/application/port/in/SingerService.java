package com.vroong.msabootcamp.application.port.in;

import com.vroong.msabootcamp.application.port.out.SingerRepository;
import com.vroong.msabootcamp.domain.Singer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SingerService {

  private final SingerRepository repository;

  @Transactional
  public Singer createSinger(String name) {
    final Singer singer = new Singer();
    singer.setName(name);

    return repository.save(singer);
  }
}
