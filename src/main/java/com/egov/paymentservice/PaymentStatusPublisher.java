package com.egov.paymentservice;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentStatusPublisher {

    private static final Logger logger = LoggerFactory.getLogger(PaymentStatusPublisher.class);

    //@Autowired
    //private Sinks.Many<OrderEvent> orderSinks;

    private static final String TOPIC = "payment-events";

    @Autowired //DEPENDENCY INJECTION PROMISE FULFILLED AT RUNTIME
    private KafkaTemplate<String, String> kafkaTemplate ;



    public void publishPaymentEvent(PaymentEvent paymentEvent) {
        //OrderEvent orderEvent=new OrderEvent(orderRequestDto,orderStatus);
        //orderSinks.tryEmitNext(orderEvent);
        ObjectMapper objectMapper = new ObjectMapper();
        String datum = null;
        try {
            datum = objectMapper.writeValueAsString(paymentEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        logger.info(String.format("#### -> Producing Payment message -> %s", datum));
        this.kafkaTemplate.send(TOPIC,"payment-key-1"+paymentEvent.getEventId(), datum);
    }
}
