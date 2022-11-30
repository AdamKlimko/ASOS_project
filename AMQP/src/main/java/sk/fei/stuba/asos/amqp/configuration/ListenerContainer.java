package sk.fei.stuba.asos.amqp.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class ListenerContainer {

    private static final Logger logger = LoggerFactory.getLogger(ListenerContainer.class);

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    @Qualifier("Q1")
    private Queue q1;

    @Autowired
    @Qualifier("Q2")
    private Queue q2;

    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(q1, q2);
        container.setMessageListener(m -> {
            String message = new String(m.getBody(), StandardCharsets.UTF_8);

            logger.info("From: " + m.getMessageProperties().getConsumerQueue() + " received -> " + message);
        });
        container.setMissingQueuesFatal(false);
        return container;
    }
}
