package sk.fei.stuba.asos.amqp.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Queues {
    @Bean("Q4")
    public Queue q4() {
        return new Queue("Q4");
    }

    @Bean("Q3")
    public Queue q3() {
        return new Queue("Q3");
    }

    @Bean("Q2")
    public Queue q2() {
        return new Queue("Q2");
    }

    @Bean("Q1")
    public Queue q1() {
        return new Queue("Q1");
    }
}
