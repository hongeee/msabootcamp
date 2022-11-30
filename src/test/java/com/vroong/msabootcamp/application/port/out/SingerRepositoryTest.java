package com.vroong.msabootcamp.application.port.out;

import static com.vroong.msabootcamp.domain.Fixtures.aSinger;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.vroong.msabootcamp.domain.Singer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Slf4j
class SingerRepositoryTest {

  @Autowired private SingerRepository repository;

  @Test
  void save() {
    Singer singer = aSinger();
    repository.save(singer);

    assertNotNull(singer.getId());
    assertNotNull(singer.getName());

    log.info("{}", singer);
  }
}
