package vn.elca.training;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import vn.elca.training.config.ApiConfig;
import vn.elca.training.exception.ControllerAdvisor;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;
import vn.elca.training.web.ApplicationController;
import vn.elca.training.web.ProjectController;

import java.io.File;

/**
 * @author gtn
 *
 */
@SpringBootApplication(scanBasePackages = "vn.elca.training")
//@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
@ComponentScan(basePackageClasses = { ApplicationController.class, ProjectController.class, ProjectService.class,
        ProjectRepository.class, ControllerAdvisor.class, ApiConfig.class})
@PropertySource({"classpath:/application.properties", "classpath:/messages.properties"})
@EnableWebMvc
public class ApplicationWebConfig extends SpringBootServletInitializer
        implements DisposableBean, CommandLineRunner
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationWebConfig.class);
    }
//
//    @Bean
//    ServletRegistrationBean h2servletRegistration() {
//        // access to h2 console by
//        // this link: http://localhost:8080/h2console
//        // JDBC URL: jdbc:h2:mem:onboardingexercise
//        // other fields left as default, this configuration will access on memory schema
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//        registrationBean.addUrlMappings("/h2console/*");
//        return registrationBean;
//    }

    @Override
    public void run(String... args) {
        String pidFile = System.getProperty("pidfile");
        if (pidFile != null) {
            new File(pidFile).deleteOnExit();
        }
    }

    @Override
    public void destroy() {
    }
}
