package com.egov.paymentservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaymentConsumer
{
    private final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);

    private static final String TOPIC = "order-events";

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentStatusPublisher paymentStatusPublisher;


    @KafkaListener(topics = TOPIC, groupId = "order_group_id")
    public void consume(String message) throws IOException
    {

        //analytics_counter.increment();

        ObjectMapper mapper  = new ObjectMapper();
        logger.info("payment Consumer :: "+message);
        OrderEvent orderEvent =  mapper.readValue(message,OrderEvent.class);

        logger.info(orderEvent.toString());

        // get the user id
        // check the balance availability
        // if balance sufficient -> Payment completed and deduct amount from DB
        // if payment not sufficient -> cancel order event and update the amount in DB
        if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())){
            PaymentEvent paymentEvent = this.paymentService.newOrderEvent(orderEvent);
            paymentStatusPublisher.publishPaymentEvent(paymentEvent);
        }else{
            this.paymentService.cancelOrderEvent(orderEvent);
        }

        /*switch (datum.getType())
        {
            case "CREDENTIAL"   : logger.info(String.format("#### -> Consumed message -> %s", datum.getMessage()));break;
            case "USERDETAIL"   : logger.info( datum.getMessage()+" ---> "+mapper.readValue(datum.getPayload(), Userdetail.class));break;
            case "PRODUCTOFFER" : logger.
                    info(datum.getMessage()+" ---> "+mapper.readValue(datum.getPayload(), Productoffer.class));offer_counter.increment();break;
            default: logger.info(message);
        }*/
    }
}
