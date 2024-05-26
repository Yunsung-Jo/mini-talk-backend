package dev.yunsung.minitalk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MiniTalkApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> System.getProperty("MARIADB_URL"));
        registry.add("spring.datasource.username", () -> System.getProperty("MARIADB_USERNAME"));
        registry.add("spring.datasource.password", () -> System.getProperty("MARIADB_PASSWORD"));
    }

    @Test
    @DisplayName("데이터베이스 연결 테스트")
    void databaseConnection() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertThat(result).isEqualTo(1);
    }

}
