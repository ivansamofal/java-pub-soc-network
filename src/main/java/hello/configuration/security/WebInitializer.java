//package hello.configuration.security;
//
////import org.apache.catalina.core.ApplicationContext;
//import org.springframework.context.ApplicationContext;
//import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;
//
////public class WebInitializer extends AbstractSecurityWebApplicationInitializer {
////
////    @Override
////    public void onStartup(ServletContext servletContext) {
////        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
////        applicationContext.register(ApplicationContext.class);
////        servletContext.addListener(new ContextLoaderListener(applicationContext));
////        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("test-dispatcher", new DispatcherServlet(applicationContext));
////        dispatcher.setLoadOnStartup(1);
////        dispatcher.setAsyncSupported(true);
////        dispatcher.addMapping("/");
////    }
////}
//
//public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//
//    @Override
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class<?>[] {ApplicationContext.class};
//    }
//
//    @Override
//    protected Class<?>[] getServletConfigClasses() {
//        return null;
//    }
//
//    @Override
//    protected String[] getServletMappings() {
//        return new String[] {"/"};
//    }
//
//    @Override
//    protected String getServletName() {
//        return "test-dispatcher";
//    }
//}
