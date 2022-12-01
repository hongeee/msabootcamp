package com.vroong.msabootcamp.application.port.in;

import static com.vroong.msabootcamp.domain.Fixtures.DEFAULT_SINGER_NAME;
import static com.vroong.msabootcamp.domain.Fixtures.aSinger;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.vroong.msabootcamp.application.port.out.SingerRepository;
import com.vroong.msabootcamp.domain.Singer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@Slf4j
class SingerServiceTest {

  @MockBean private SingerRepository repository;
  @Autowired private SingerService service;

  @Test
  void createSinger() {
    when(repository.save(any(Singer.class))).thenReturn(aSinger());

    final Singer singer = service.createSinger(DEFAULT_SINGER_NAME);

    log.info("{}", singer);
  }
}
