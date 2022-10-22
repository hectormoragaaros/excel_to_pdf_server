package org.hectormoraga.exceltopdfclient.config;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("org.hectormoraga.exceltopdfclient")
@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:project.properties")
})
public class ExcelToPdfAppConfig implements WebMvcConfigurer{
	@Autowired
	@Value("${store.directory}")
	private String directory;
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	// add resource handler for loading css, images, etc	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }
    
    @PostConstruct
    public void clearDirectory() throws IOException, NullPointerException {
    	Path dirStore = Paths.get(directory);
    	
    	if (!dirStore.toFile().exists()) {
    		FileUtils.forceMkdir(dirStore.toFile());
    	}
    	
    	FileUtils.cleanDirectory(dirStore.toFile());
    }
}
