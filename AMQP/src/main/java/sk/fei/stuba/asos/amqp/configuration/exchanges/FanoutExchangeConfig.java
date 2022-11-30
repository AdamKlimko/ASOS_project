package sk.fei.stuba.asos.amqp.configuration.exchanges;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {

    @Autowired
    @Qualifier("Q1")
    private Queue q1;

    @Autowired
    @Qualifier("Q2")
    private Queue q2;

    @Autowired
    @Qualifier("Q3")
    private Queue q3;

    @Autowired
    @Qualifier("Q4")
    private Queue q4;

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-exchange");
    }

    @Bean
    public Binding fanoutToQ1() {
        return BindingBuilder.bind(q1).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutToQ2() {
        return BindingBuilder.bind(q2).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutToQ3() {
        return BindingBuilder.bind(q3).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutToQ4() {
        return BindingBuilder.bind(q4).to(fanoutExchange());
    }
}
