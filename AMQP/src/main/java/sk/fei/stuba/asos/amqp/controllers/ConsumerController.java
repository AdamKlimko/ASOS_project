package sk.fei.stuba.asos.amqp.controllers;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fei.stuba.asos.amqp.models.MessagePOJO;

@RestController
@RequestMapping(
        value = "/receive"
)
public class ConsumerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("Q3")
    private Queue q1;

    @GetMapping("/")
    public ResponseEntity<?> consumeQ3() {
        MessagePOJO msg = (MessagePOJO) rabbitTemplate.receiveAndConvert(q1.getName());

        return ResponseEntity.status(HttpStatus.OK).body(
                msg
        );
    }
}
