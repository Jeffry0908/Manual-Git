/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rauda
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = {"/Secure/*"})
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {        
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
    HttpServletResponse response = (HttpServletResponse) sr1;
    HttpSession session = request.getSession(false);
    String loginURI = request.getContextPath() + "/faces/login.xhtml";

    boolean loggedIn = session != null && session.getAttribute("usuario") != null;
    boolean loginRequest = request.getRequestURI().equals(loginURI);
    boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

    if (loggedIn || loginRequest || resourceRequest) {
        fc.doFilter(request, response);
    } else {
        response.sendRedirect(loginURI);
    }
    }

    @Override
    public void destroy() {
        
    }

}
