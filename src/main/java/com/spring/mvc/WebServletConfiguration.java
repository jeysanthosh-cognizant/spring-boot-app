package com.spring.mvc;//package com.spring.mvc;
//
//import javax.servlet.ServletContext;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import com.executor.Configuration;
//public class WebServletConfiguration implements WebApplicationInitializer{
//	@Override
//    public void onStartup(ServletContext ctx) throws ServletException {
//    	System.out.println("in servlet>>>>>>>>>>>>>>>>>>>>>");
//        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
//        webCtx.register(Configuration.class);
//        webCtx.setServletContext(ctx);
//        ctx.addListener(new ContextLoaderListener(webCtx));
//        ServletRegistration.Dynamic servlet = ctx.addServlet("dispatcher", new DispatcherServlet(webCtx));
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//    	
//    	/*AnnotationConfigWebApplicationContext context
//        = new AnnotationConfigWebApplicationContext();
//      context.setConfigLocation("com.executor");
//
//      ctx.addListener(new ContextLoaderListener(context));
//
//      ServletRegistration.Dynamic dispatcher = ctx
//        .addServlet("dispatcher", new DispatcherServlet(context));
//       
//      dispatcher.setLoadOnStartup(1);
//      dispatcher.addMapping("/");*/
//    }
//    
// /*   protected Class<?>[] getServletConfigClasses() {
//    return new Class[] { Configuration.class };
//    }
//    
//   
//    protected Class<?>[] getRootConfigClasses() {
//        return new Class[] { HibernateUtil.class };
//     }*/
//}