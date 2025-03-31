package gameeventogroup.gamerevent.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final LoginAttemptFilter loginAttemptFilter;

    public CustomAuthFailureHandler(LoginAttemptFilter loginAttemptFilter) {
        this.loginAttemptFilter = loginAttemptFilter;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
        String ip = request.getRemoteAddr();
        loginAttemptFilter.registrarIntento(ip);
        super.onAuthenticationFailure(request, response, exception);
    }
}
