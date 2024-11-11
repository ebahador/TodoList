package com.todolist.userservice;

import com.todolist.userservice.database.Dao;
import com.todolist.userservice.router.internal.core.v1.token.TokenController;
import com.todolist.userservice.router.internal.core.v1.token.handlers.TokenInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class TokenControllerTest {
    // Valid token returns HTTP 200 OK response
    @Test
    public void test_valid_token_returns_http_200() {
        // Arrange
        String validToken = "validToken1234";
        Dao daoMock = Mockito.mock(Dao.class);
        Mockito.when(daoMock.isTokenValid(validToken)).thenReturn(true);
        TokenInfoService tokenInfoServiceMock = Mockito.mock(TokenInfoService.class);
        TokenController tokenController = new TokenController(tokenInfoServiceMock, daoMock);

        // Act
        ResponseEntity<Void> response = tokenController.validateUser(validToken);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    // Token is null or empty
    @Test
    public void test_token_is_null_or_empty() {
        // Arrange
        String emptyToken = "";
        Dao daoMock = Mockito.mock(Dao.class);
        Mockito.when(daoMock.isTokenValid(emptyToken)).thenReturn(false);
        TokenInfoService tokenInfoServiceMock = Mockito.mock(TokenInfoService.class);
        TokenController tokenController = new TokenController(tokenInfoServiceMock, daoMock);

        // Act
        ResponseEntity<Void> response = tokenController.validateUser(emptyToken);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Invalid token returns HTTP 404 Not Found response
    @Test
    public void test_invalid_token_returns_404() {
        Dao daoMock = Mockito.mock(Dao.class);
        TokenInfoService tokenInfoServiceMock = Mockito.mock(TokenInfoService.class);
        TokenController tokenController = new TokenController(tokenInfoServiceMock, daoMock);

        String invalidToken = "invalidToken";
        Mockito.when(daoMock.isTokenValid(invalidToken)).thenReturn(false);

        ResponseEntity<Void> response = tokenController.validateUser(invalidToken);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
