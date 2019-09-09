package edu.gdut.imis.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * @author Leven
 * @date 2019/9/9
 * <p>
 * 1、WebApplicationInitializer是spring提供用来配置Servlet3.0+配置的接口，从而实现了替代
 * web.xml的位置。实现此接口将会自动被SpringServletContainerInitializer（用来启动Servlet3.0容器）获取到。
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //注册IoC 容器
        context.register(ApplicationConfig.class);
        //注册web容器
        context.register(WebMvcConfig.class);
        //新建WebAppServletContext,注册配置类，并将其和当前servletContext关联。
        context.setServletContext(servletContext);
        //注册spring mvc的dispatcherServlet
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        //开启异步方法支持
        servlet.setAsyncSupported(true);
    }
}
