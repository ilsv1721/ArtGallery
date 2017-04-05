#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;

import javax.servlet.Multip${artifactId}ConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ArtGalleryAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class<?>[] { SecurityConfig.class, DataConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultip${artifactId}Config(new Multip${artifactId}ConfigElement("/home/ilya/123/", 2097152, 4194304, 0));
	}

	@Override
	public void onSt${artifactId}up(ServletContext servletContext) throws ServletException {
		super.onSt${artifactId}up(servletContext);
		// Either move to spring boot or external profile defenition
		//servletContext.setInitParameter("spring.profiles.active", "devServerMySQL");
		servletContext.setInitParameter("spring.profiles.active", "devEmbed");
	}

}
