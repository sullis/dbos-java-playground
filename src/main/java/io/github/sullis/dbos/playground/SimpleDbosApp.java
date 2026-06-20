package io.github.sullis.dbos.playground;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.config.DBOSConfig;

public final class SimpleDbosApp {

  private static final String APP_NAME = "dbos-java-playground";

  private SimpleDbosApp() {}

  public static void main(String[] args) {
    DBOSConfig config = DBOSConfig.defaultsFromEnv(APP_NAME);
    if (config.databaseUrl() == null || config.databaseUrl().isBlank()) {
      throw new IllegalStateException(
          "DBOS_SYSTEM_JDBC_URL must be set. Use a PostgreSQL database before running this app.");
    }

    String workflowInput = args.length > 0 ? args[0] : "world";
    try (DBOS dbos = new DBOS(config)) {
      SimpleWorkflow workflow = dbos.registerProxy(SimpleWorkflow.class, new SimpleWorkflowImpl(dbos));
      dbos.launch();
      String result = workflow.run(workflowInput);
      System.out.println(result);
      dbos.shutdown();
    }
  }
}
