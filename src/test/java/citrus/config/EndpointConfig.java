
package citrus.config;


import static org.citrusframework.actions.ExecuteSQLAction.Builder.sql;
import jakarta.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import org.apache.commons.dbcp.BasicDataSource;
import org.citrusframework.container.AfterSuite;
import org.citrusframework.container.BeforeSuite;
import org.citrusframework.container.SequenceAfterSuite;
import org.citrusframework.container.SequenceBeforeSuite;
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

    @Bean
    public BeforeSuite beforeSuite(BasicDataSource todoListDataSource) {
        return new SequenceBeforeSuite.Builder()
            .actions(sql(todoListDataSource)
                .statement("CREATE TABLE IF NOT EXISTS otus_students " +
                    "(id NUMBER, name VARCHAR(50))")
                .statement("INSERT INTO otus_students VALUES(1,'Andrey')"))
            .build();
    }

    @Bean
    public AfterSuite afterSuite(BasicDataSource todoListDataSource) {
        return new SequenceAfterSuite.Builder()
            .actions(sql(todoListDataSource)
                .statement("DELETE FROM otus_students"))
            .build();
    }

    @Bean(destroyMethod = "close", name="helperDataSource")
    public BasicDataSource helperDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(5);
        dataSource.setMaxIdle(2);
        return dataSource;
    }
    }
