package duksung.backend.hororok.ugeubi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.nio.charset.Charset;

@EnableScheduling
@Configuration
@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class UgeubiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UgeubiApplication.class, args);
    }

//    /**한글깨짐 문제 해결을 위한 Bean추가**/
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        return new StringHttpMessageConverter(Charset.forName("euc-kr"));
//    }
//
//    @Bean
//    public CharacterEncodingFilter characterEncodingFilter()  {
//        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//        characterEncodingFilter.setEncoding("euc-kr");
//        characterEncodingFilter.setForceEncoding(true);
//        return characterEncodingFilter;
//    }
}
