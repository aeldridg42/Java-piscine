package edu.school21.repositories;

import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private EmbeddedDatabase db;

    @BeforeEach
    void init() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql").addScript("data.sql")
                .build();
    }

    @Test
    void getConnectionShouldNotReturnNull() throws SQLException {
        Assertions.assertNotNull(db.getConnection());
    }

    @AfterEach
    void shutDown() {
        db.shutdown();
    }
}
