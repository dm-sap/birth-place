package it.maeci.territory.security;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Failure handler che gestisce eccezioni nella validazione del token e dei parametri passati in ingresso durante
 * la chiamata iniziale quali redirectUrl e codici documento
 */
public class AuthenticationFailureHandler implements AuthenticationEntryPoint {

    /**
     * Commence
     *
     * @param request       request
     * @param response      response
     * @param authException authException
     * @throws IOException IOException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String url = request.getRequestURL().toString() + "/-/";
        String contextApplication = extractContextApplication(url);
        String server = extractServer(url);
        response.sendRedirect(server + "/" + contextApplication + "/error/401");

    }

    /**
     * Estrazione del context
     *
     * @param url url
     * @return String
     */
    private String extractContextApplication(String url) {
        return url.split("/")[3];
    }

    /**
     * Estrazione del server
     *
     * @param url url
     * @return String
     */
    private String extractServer(String url) {
        String[] parts = url.split("/");
        return parts[0] + "//" + parts[1] + parts[2];
    }

}
