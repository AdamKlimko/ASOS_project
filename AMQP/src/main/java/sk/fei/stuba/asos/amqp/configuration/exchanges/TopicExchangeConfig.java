package sk.fei.stuba.asos.amqp.configuration.exchanges;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    @Autowired
    @Qualifier("Q1")
    private Queue q1;

    @Autowired
    @Qualifier("Q2")
    private Queue q2;

    @Autowired
    @Qualifier("Q3")
    private Queue q3;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean("TopicToQ1")
    public Binding topicToPojoBinding() {
        return BindingBuilder.bind(q1).to(topicExchange()).with("route.to.*");
    }

    @Bean("TopicToQ2")
    public Binding topicToMessageBinding() {
        return BindingBuilder.bind(q2).to(topicExchange()).with("route.to.*");
    }

    @Bean("TopicToQ3")
    public Binding topicToAllBinding() {
        return BindingBuilder.bind(q3).to(topicExchange()).with("route.#");
    }
}
