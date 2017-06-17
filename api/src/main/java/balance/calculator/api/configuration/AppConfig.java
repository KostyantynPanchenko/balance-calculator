package balance.calculator.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ResourceConfig.class,
         FilterConfig.class,
         ExceptionConfig.class})
public class AppConfig {

}
