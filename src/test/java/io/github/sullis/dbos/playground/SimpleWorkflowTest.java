package io.github.sullis.dbos.playground;

import static org.assertj.core.api.Assertions.assertThat;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.config.DBOSConfig;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class SimpleWorkflowTest {

  @Container
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:18");

  @Test
  void workflowRunsAgainstPostgresContainer() {
    DBOSConfig config =
        DBOSConfig.defaults("dbos-java-playground-test")
            .withDatabaseUrl(postgres.getJdbcUrl())
            .withDbUser(postgres.getUsername())
            .withDbPassword(postgres.getPassword());

    try (DBOS dbos = new DBOS(config)) {
      SimpleWorkflow workflow = dbos.registerProxy(SimpleWorkflow.class, new SimpleWorkflowImpl(dbos));
      dbos.launch();
      assertThat(workflow.run("Testcontainers")).isEqualTo("Hello, Testcontainers from DBOS!");
      dbos.shutdown();
    }
  }
}
