package it.maeci.territory.errors;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/401")
    public void visualizza401(HttpServletResponse response, HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
