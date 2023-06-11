package com.microservice.notification;


import com.microservice.amqp.RabbitMQProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;




@SpringBootApplication(
        scanBasePackages = {
                "com.microservice.notification",
                "com.microservice.amqp"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

  /*  @Bean
    CommandLineRunner commandLineRunner(
            RabbitMQProducer producer,
            NotificationConfig notificationConfig
            ) {
        return args -> {
            producer.publish(
                    new Person("Ali", 18),
                    notificationConfig.getInternalExchange(),
                    notificationConfig.getInternalNotificationRoutingKey());
        };
    }*/

    record Person(String name, int age){}
}
