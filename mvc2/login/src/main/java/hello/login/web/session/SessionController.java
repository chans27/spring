package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "session is not exist";
        }

        //print session data
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name={}, value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId());
        log.info("session.getMaxInactiveInterval={}", session.getMaxInactiveInterval());
        log.info("session.getCreationTime={}", session.getCreationTime());
        log.info("lastAccessedTime={}", session.getLastAccessedTime());
        log.info("isNew={}", session.isNew());

        return "PRINT SESSION DATA";
    }
}
