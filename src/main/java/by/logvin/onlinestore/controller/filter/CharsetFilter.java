package by.logvin.onlinestore.controller.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;
    private ServletContext servletContext;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("characterEncoding");
        servletContext = filterConfig.getServletContext();

        servletContext.log("Parameter encoding = " + encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

        servletContext.log("Charset was set.");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
