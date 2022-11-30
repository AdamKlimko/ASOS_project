package sk.fei.stuba.asos.amqp.configuration.exchanges;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    @Autowired
    @Qualifier("Q4")
    private Queue q4;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean("DirectToQ4")
    public Binding directToMessageBinding() {
        return BindingBuilder.bind(q4).to(directExchange()).with("route.to.q4");
    }
}
