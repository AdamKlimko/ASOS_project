package sk.fei.stuba.asos.amqp.controllers;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fei.stuba.asos.amqp.models.MessagePOJO;

@RestController
@RequestMapping(
        value = "/send"
)
public class ProducerController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("DirectToQ4")
    private Binding directBinding;

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    Jackson2JsonMessageConverter converter;

    @PostMapping("/direct")
    public ResponseEntity<?> sendDirectMessage(
            @RequestBody MessagePOJO message
    ) {
        MessageProperties properties = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("__TypeId__", MessagePOJO.class)
                .build();

        Message msg = converter.toMessage(message, properties);

        rabbitTemplate.send(directBinding.getExchange(), directBinding.getRoutingKey(), msg);

        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

    @PostMapping("/topic-1")
    public ResponseEntity<?> sendTopic1(
            @RequestBody MessagePOJO message
    ) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "route.to.firstWord", message);

        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

    @PostMapping("/topic-2")
    public ResponseEntity<?> sendTopic2(
            @RequestBody MessagePOJO message
    ) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), "route.to.firstWord.secondWord", message);

        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

    @PostMapping("/fanout")
    public ResponseEntity<?> sendFanoutMessage(
            @RequestBody MessagePOJO message
    ) {
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", message);

        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }
}
