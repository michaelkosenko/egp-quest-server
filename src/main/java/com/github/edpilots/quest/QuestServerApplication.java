package com.github.edpilots.quest;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
@EnableMongoAuditing
// @EnableMongoRepositories
public class QuestServerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(QuestServerApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/pilot").setViewName("/pilot/index");
        registry.addViewController("/").setViewName("/index");
    }

    
    //@Bean
//    public MongoCustomConversions customConversions(){
//        List<Converter<?,?>> converters = new ArrayList<Converter<?,?>>();
//        converters.add(new DateToZonedDateTimeConverter());
//        converters.add(new ZonedDateTimeToDateConverter());
//        return new MongoCustomConversions(converters);
//    }
//
//    class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
//
//         @Override
//         public ZonedDateTime convert(Date source) {
//                  return source == null ? null : ZonedDateTime.ofInstant(source.toInstant(), ZoneOffset.UTC);
//             }
//         }
//
//    class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
//
//        @Override
//        public Date convert(ZonedDateTime source) {
//                 return source == null ? null : Date.from(source.toInstant());
//           }
//       }
}
