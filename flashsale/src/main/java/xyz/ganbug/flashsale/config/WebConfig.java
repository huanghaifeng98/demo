package xyz.ganbug.flashsale.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters){
            if (converter instanceof MappingJackson2HttpMessageConverter){
                ObjectMapper objectMapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
                objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
//                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//                objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            }
        }
    }
}
