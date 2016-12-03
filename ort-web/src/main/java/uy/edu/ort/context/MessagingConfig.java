/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.edu.ort.context;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author docenteFI
 */
@Configuration
public class MessagingConfig {

    @Value("${queue.connection.expiry-timeout}")
    private long expiryTimeout;
    
    @Value("${queue.broker.url}")
    private String brokerURL;

    @Bean
    public PooledConnectionFactory connectionFactory() {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(this.directConnectionFactory());
        pooledConnectionFactory.setExpiryTimeout(expiryTimeout);
        return pooledConnectionFactory;
    }

    @Bean
    public ActiveMQConnectionFactory directConnectionFactory() {
        ActiveMQConnectionFactory directConnectionFactory = new ActiveMQConnectionFactory();
        directConnectionFactory.setBrokerURL(brokerURL);
        return directConnectionFactory;
    }
}
