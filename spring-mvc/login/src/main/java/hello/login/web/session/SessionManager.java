package hello.login.web.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * create session
     */
    public void createSession(Object value, HttpServletResponse response) {

        //セッションIDを生成し、セッションに値として設定
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //cookie生成
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }

    /**
     * find session
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    /**
     * session expiration
     */
     public void expire(HttpServletRequest request) {
         Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
         if (sessionCookie != null) {
             sessionStore.remove(sessionCookie.getValue());
         }
     }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(SESSION_COOKIE_NAME))
                .findAny()
                .orElse(null);
    }


}
