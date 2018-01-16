package cn.pahot.upms.configure;

import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class DefaultConfigure {
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		TomcatContextCustomizer contextCustomizer = new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				context.addWelcomeFile("/index.jsp");
			}
		};
		factory.addContextCustomizers(contextCustomizer);

		return factory;
	}
	
	@Bean
	public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
		ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
		reg.getUrlMappings().clear();
		reg.addUrlMappings("*.ak");
		reg.addUrlMappings("*.sec");
		return reg;
	}
}
