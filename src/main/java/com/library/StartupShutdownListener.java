package com.library;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

// @WebListener
public class StartupShutdownListener implements ServletContextListener {
    public StartupShutdownListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(servletContext.getContextPath());
        System.out.println("Send email to someone");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(servletContext.getContextPath());
        System.out.println("Send Alarm to development team");
    }
}
