package duksung.backend.hororok.ugeubi.common.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "Authorization";
    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        final String tokenHeader = request.getHeader(HEADER_AUTH);

        if(jwtProvider.isTokenValid(tokenHeader)){
            String userId = jwtProvider.getUserIdFromJwtHeader(tokenHeader);
            request.setAttribute("loginUserId", userId);
            return true;
        }else{
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
    }
}
