package gameeventogroup.gamerevent.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginAttemptFilter extends OncePerRequestFilter {

    private Map<String, Integer> intentosFallidos = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().equals("/login") && request.getMethod().equalsIgnoreCase("POST")) {
            String ip = request.getRemoteAddr();

            if (intentosFallidos.getOrDefault(ip, 0) >= 5) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Demasiados intentos fallidos, intente más tarde");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    public void registrarIntento(String ip) {
        intentosFallidos.merge(ip, 1, Integer::sum);
    }

    public void resetearIntentos(String ip) {
        intentosFallidos.remove(ip);
    }
}
