package Group4.tracer.config;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {
    
    @Override
    public void sessionCreated(HttpSessionEvent session) {
        session.getSession().setAttribute("points", 0);
        session.getSession().setAttribute("role", "guest");
    }
    
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}