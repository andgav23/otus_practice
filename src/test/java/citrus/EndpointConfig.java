
package citrus;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.citrusframework.dsl.endpoint.CitrusEndpoints;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class EndpointConfig {



    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("vm://0");
    }

    @Bean(name="MQEndpoint")
    public JmsEndpoint todoJmsEndpoint(ConnectionFactory connectionFactory) {
        return CitrusEndpoints.
            jms()
                .asynchronous()
                .connectionFactory(connectionFactory)
                .destination("otus.queue")
            .build();
    }

}
