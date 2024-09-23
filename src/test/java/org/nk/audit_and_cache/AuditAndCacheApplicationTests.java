package org.nk.audit_and_cache;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(
    webEnvironment = WebEnvironment.RANDOM_PORT,
    properties = {
        "org.springframework.transaction=debug"
    }
)
@AutoConfigureMockMvc
class AuditAndCacheApplicationTests {

  @Container
  @ServiceConnection
  private static final MySQLContainer mySQLContainer = new MySQLContainer<>("mysql");

  @Test
  void check_db_and_cache_sync(@Autowired final StudentClient studentClient) {
    var student = buildStudent();

    var studentStored = studentClient.create(student);

    var studentUpdated = studentClient.update(studentStored.withName(randomAlphabetic(5)), studentStored.getId());

    var studentUpdatedAgain = studentClient.update(studentUpdated.withName(randomAlphabetic(5)), studentUpdated.getId());

    assertThat(studentUpdatedAgain.getVersion()).isEqualTo(2);
  }

  private StudentResource buildStudent() {
    return StudentResource
        .builder()
        .name(randomAlphabetic(5))
        .build();
  }

  @Lazy
  @TestConfiguration(proxyBeanMethods = false)
  static class WebClientTestConfiguration {

    @Bean
    StudentClient studentRestClient(@Value("${local.server.port}") final int localServerPort) {
      var restClient = RestClient.create(
          String.format("http://localhost:%s/students", localServerPort));
      var adapter = RestClientAdapter.create(restClient);
      var factory = HttpServiceProxyFactory.builderFor(adapter).build();
      return factory.createClient(StudentClient.class);
    }
  }
}
