# dbos-java-playground

A Java playground for experimenting with [DBOS](https://dbos.dev) concepts.

## Requirements

- Java 21 ([Eclipse Temurin](https://adoptium.net) recommended)
- Maven

## Build & Test

```bash
# Compile and run tests
mvn clean package

# Run tests only
mvn clean test
```

## Example Application

This repository now includes a minimal DBOS sample app at:

- `/home/runner/work/dbos-java-playground/dbos-java-playground/src/main/java/io/github/sullis/dbos/playground/SimpleDbosApp.java`
- `/home/runner/work/dbos-java-playground/dbos-java-playground/src/main/java/io/github/sullis/dbos/playground/SimpleWorkflowImpl.java`

The app registers a single workflow and runs one durable step that returns a greeting.

To run it locally, set DBOS/Postgres environment variables first:

```bash
export DBOS_SYSTEM_JDBC_URL=jdbc:postgresql://localhost:5432/postgres
export PGUSER=postgres
export PGPASSWORD=postgres
```

Then run:

```bash
mvn -q -DskipTests compile exec:java -Dexec.mainClass=io.github.sullis.dbos.playground.SimpleDbosApp -Dexec.args="DBOS"
```

## Testcontainers Integration Test

`/home/runner/work/dbos-java-playground/dbos-java-playground/src/test/java/io/github/sullis/dbos/playground/SimpleWorkflowTest.java`
uses Testcontainers to start PostgreSQL and verify the workflow executes end-to-end against a real containerized database.

## Key Dependencies

| Dependency | Version |
|---|---|
| JUnit Jupiter | 6.0.3 |
| AssertJ | 4.0.0-M1 |
| Awaitility | 4.3.0 |
| Testcontainers | 2.0.4 |
| Jackson | 2.21.1 |
| Guava | 33.5.0-jre |
| SLF4J | 2.0.17 |
| Logback | 1.5.32 |

## License

[Apache License 2.0](LICENSE)
