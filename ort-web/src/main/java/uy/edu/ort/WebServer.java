package uy.edu.ort;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import uy.edu.ort.context.WebContext;

public class WebServer {

    private static final int DEFAULT_PORT = 8084;
    private static final String CONTEXT_PATH = "/cadetify";

    private Server server;

    public static void main(String[] args) throws Exception {
        WebServer webServer = new WebServer();
        webServer.run();
    }

    public WebServer() throws Exception {
        this.server = new Server(DEFAULT_PORT);

        WebAppContext webAppContext = this.buildWebAppContext();
        this.server.setHandler(webAppContext);
        this.server.setStopAtShutdown(true);
    }

    private void run() throws Exception {
        this.server.start();
        this.server.join();
    }

    private WebAppContext buildWebAppContext() throws Exception {
        WebAppContext contextHandler = new WebAppContext();
        contextHandler.setContextPath(CONTEXT_PATH);

        AnnotationConfigWebApplicationContext applicationContext = this.buildApplicationContext();
        // crear dispatcher servlet de spring
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletHolder dispatcherServletHolder = new ServletHolder(dispatcherServlet);
        contextHandler.addServlet(dispatcherServletHolder, "/");

        // agregar el application context de spring a la aplicacion web
        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(applicationContext);
        contextHandler.addEventListener(contextLoaderListener);

        String resourceBasePath = new ClassPathResource("cadetifyapp").getURI().toString();
        contextHandler.setResourceBase(resourceBasePath);

        applicationContext.close();
        return contextHandler;
    }

    private AnnotationConfigWebApplicationContext buildApplicationContext() {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebContext.class);

        return applicationContext;
    }
}
