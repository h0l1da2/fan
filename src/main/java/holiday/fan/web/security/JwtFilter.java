package holiday.fan.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter implements Filter {

    private final JwtTokenParser jwtTokenParser;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    private String resolveAccessToken(HttpServletRequest request) {

        return "";
    }
}
