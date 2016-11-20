package pl.rwalski.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class SpringMvcInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // dispatcher servlet
        WebApplicationContext context = getContext();
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");

        // context loader listener
        servletContext.addListener(new ContextLoaderListener(context));

        // spring profile activator
        servletContext.setInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM, "pl.rwalski.config.profiles.ProfileActivator");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("pl.rwalski");
        return context;
    }
}
