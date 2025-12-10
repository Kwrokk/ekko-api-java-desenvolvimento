package com.openphilosophy.api.infra.filter;


import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Chegamos ao filtro.");

        System.out.println("Request:" + req);

        filterChain.doFilter(req, res);
    }
}
