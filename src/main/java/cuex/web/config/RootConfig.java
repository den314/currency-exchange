package cuex.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(value = "cuex",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = EnableWebMvc.class))
public class RootConfig {
}