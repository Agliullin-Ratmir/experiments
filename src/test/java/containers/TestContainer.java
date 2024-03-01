package containers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
class TestContainer {

    @Container
    private PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer();

    @Test
    void test() {
        assertThat(postgresqlContainer.isRunning()).isTrue();
    }
}
