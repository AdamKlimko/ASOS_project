package sk.fei.stuba.asos.amqp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import sk.fei.stuba.asos.amqp.configuration.ListenerContainer;
import sk.fei.stuba.asos.amqp.models.MessagePOJO;

@Component
public class Listeners {
    private static final Logger logger = LoggerFactory.getLogger(ListenerContainer.class);

    @RabbitListener(queues = {"Q4"})
    public void listenToQ4 (MessagePOJO message) {
        logger.info(message.toString());
    }
}
