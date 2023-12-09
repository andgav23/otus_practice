/*
 * Copyright 2006-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package citrus;

import jakarta.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import org.citrusframework.dsl.endpoint.CitrusEndpoints;
import org.citrusframework.http.client.HttpClient;
import org.citrusframework.jms.endpoint.JmsEndpoint;
import org.citrusframework.jms.endpoint.JmsSyncEndpoint;
import org.citrusframework.xml.namespace.NamespaceContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import java.util.Collections;

/**
 * @author Christoph Deppisch
 */

@Configuration
public class EndpointConfig {

    @Bean
    public HttpClient todoClient() {
        return CitrusEndpoints
            .http()
                .client()
                .requestUrl("http://localhost:8080")
            .build();
    }

    @Bean
    public NamespaceContextBuilder namespaceContextBuilder() {
        NamespaceContextBuilder namespaceContextBuilder = new NamespaceContextBuilder();
        namespaceContextBuilder.setNamespaceMappings(Collections.singletonMap("xh", "http://www.w3.org/1999/xhtml"));
        return namespaceContextBuilder;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("vm://0");
    }

    @Bean
    public JmsEndpoint todoJmsEndpoint(ConnectionFactory connectionFactory) {
        return CitrusEndpoints.
            jms()
                .asynchronous()
                .connectionFactory(connectionFactory)
                .destination("jms.todo.inbound")
            .build();
    }

    @Bean
    public JmsEndpoint todoReportEndpoint(ConnectionFactory connectionFactory) {
        return CitrusEndpoints.jms()
                .asynchronous()
                .connectionFactory(connectionFactory)
                .destination("jms.todo.report")
                .build();
    }

    @Bean
    public JmsSyncEndpoint todoJmsSyncEndpoint(ConnectionFactory connectionFactory) {
        return CitrusEndpoints.jms()
                .synchronous()
                .connectionFactory(connectionFactory)
                .destination("jms.todo.inbound.sync")
                .replyDestination("jms.todo.inbound.sync.reply")
                .build();
    }
}
