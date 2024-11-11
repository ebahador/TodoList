package com.todolist.userservice;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.model.token.Token;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;

public class DaoTest {
    // Successfully inserts a valid token into the database
    @Test
    public void test_create_api_token_success() {
        // Arrange
        JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        Dao dao = new Dao(jdbcTemplate);
        Token token = new Token.Builder()
                .tokenId("123")
                .userId("user1")
                .token("token123")
                .expiryDate(System.currentTimeMillis() + 100000)
                .creationDate(System.currentTimeMillis())
                .active(true)
                .build();

        // Act
        dao.createApiToken(token);

        // Assert
        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(
                Mockito.anyString(),
                Mockito.eq("123"),
                Mockito.eq("user1"),
                Mockito.eq("token123"),
                Mockito.any(Timestamp.class),
                Mockito.any(Timestamp.class),
                Mockito.eq("true")
        );
    }
}
