package io.github.sullis.dbos.playground;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.workflow.Workflow;

public class SimpleWorkflowImpl implements SimpleWorkflow {

  private final DBOS dbos;

  public SimpleWorkflowImpl(DBOS dbos) {
    this.dbos = dbos;
  }

  @Override
  @Workflow
  public String run(String name) {
    return dbos.runStep(() -> "Hello, " + name + " from DBOS!", "greet-step");
  }
}
