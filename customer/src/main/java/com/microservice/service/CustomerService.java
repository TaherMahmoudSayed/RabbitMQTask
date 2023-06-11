package com.microservice.service;

import com.microservice.amqp.RabbitMQProducer;
import com.microservice.clients.notification.NotificationRequest;
import com.microservice.entity.Customer;
import com.microservice.clients.fraud.FraudCheckResponse;
import com.microservice.clients.fraud.FraudClient;
import com.microservice.repository.CustomerRepository;
import com.microservice.requestResponceDTO.CustomerRegistrationRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQProducer rabbitMQProducer;
    private final RestTemplate restTemplate;
    public CustomerRegistrationRequestDTO registerCustomer(CustomerRegistrationRequestDTO customerDTO){
        Customer customer=Customer.builder().firstName(customerDTO.firstName())
                .email(customerDTO.email())
                .lastName(customerDTO.lastName()).build();
        try {
            customerRepository.saveAndFlush(customer);
          /* FraudCheckResponse fraudCheckResponse= restTemplate.getForObject(
                    "http://localhost/api/v1/fraud/check/{customerId}",
                    FraudCheckResponse.class,
                    customer.getId() );*/
            FraudCheckResponse fraudCheckResponse= fraudClient.isFraudster(customer.getId());
           if(fraudCheckResponse.isFraudster()){
               throw new IllegalArgumentException();
           }
           NotificationRequest notificationRequest=NotificationRequest.builder()
                   .toCustomerId(customer.getId())
                   .toCustomerName(customer.getFirstName())
                   .message( String.format("Hi %s, welcome to Amigoscode...",
                           customer.getFirstName())).build();
           rabbitMQProducer.publish
                   (notificationRequest,"internal.exchange","internal.notification.routing-key");
          log.info("**** publishing the notification request");

        return customerDTO;

        }catch (Exception ex){
            return null;
        }
        }
}
