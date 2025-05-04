package gameeventogroup.gamerevent.security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;


public class CustomAuthFailureHandlerTest {
     @Test
    public void testOnAuthenticationFailure_registraIntentoYDelegacion() throws IOException, ServletException {
        LoginAttemptFilter loginAttemptFilter = mock(LoginAttemptFilter.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException exception = mock(AuthenticationException.class);

        when(request.getRemoteAddr()).thenReturn("127.0.0.1");

        CustomAuthFailureHandler handler = spy(new CustomAuthFailureHandler(loginAttemptFilter));

        handler.onAuthenticationFailure(request, response, exception);

        verify(loginAttemptFilter).registrarIntento("127.0.0.1");

        verify(handler).onAuthenticationFailure(request, response, exception);
    }

}
