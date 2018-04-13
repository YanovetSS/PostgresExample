package ukraine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/*
Аннотации:
В данной конфигурации мы указываем, где искать все наши контроллеры, сервисы и другие компоненты
 с помощью тега: context:component-scan,
 а также инициализируем InternalResourceViewResolver, который отвечайте за показ View в нашем случае это jsp страницы.

@Configuration — собственно эта аннотация и говорит о том, что данный класс является Java Configuration;
@EnableWebMvc — эта аннотация разрешает нашему проекту использовать MVC;
@ComponentScan(«com.devcolibri.common») — аналогично тому component-scan который
 был в mvc-dispatcher-servlet.xml, говорит, где искать компоненты проекта.
Bean — указывает на то что это инициализация бина, и он будет создан с помощью DI.

Конфигурация

WebMvcConfigurerAdapter — унаследовавшись от этого класса мы получим возможность сконфигурировать ResourceLocations.
addResourceHandlers(ResourceHandlerRegistry registry) — переопределив данный метод мы
можем указать где будут лежать ресурсы нашего проекта, такие как css, image, js и другие.
InternalResourceViewResolver — аналогичная конфигурация с mvc-dispatcher-servlet.xml.
 */
@Configuration
@EnableWebMvc
@ComponentScan("ukraine")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/pages/**").addResourceLocations("/pages/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-247-111-19.eu-west-1.compute.amazonaws.com:5432/d2g0l5bg11agt4?sslmode=require");
        dataSource.setUsername("ougizfhxhsklgt");
        dataSource.setPassword("de3ce7dd0d40a1f3c463645c36ee1e4def6a7b45cc2a6871b5dec3ffc13708ec");
        return dataSource;
    }
}
