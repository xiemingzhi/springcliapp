package com.example.demo;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Autowired
    private Environment environment;
    
    /*
    * Provide a contact point to the configuration.
    */
    public String getContactPoints() {
        return environment.getProperty("cassandra.contactpoints");
    }

    /*
    * Provide a keyspace name to the configuration.
    */
    public String getKeyspaceName() {
        return environment.getProperty("cassandra.keyspace");
    }
    
    @Override
    protected String getLocalDataCenter() {
        return "datacenter1";
    }

    @Override
    public int getPort() {
        return Integer.parseInt(environment.getProperty("cassandra.port"));
    }

    /*
    * Use the standard Cassandra driver API to create a com.datastax.oss.driver.api.core.CqlSession instance.
    */
    // public @Bean CqlSession session() {
    //     return CqlSession.builder().addContactPoint(new InetSocketAddress(getContactPoints(), getPort())).withKeyspace(getKeyspaceName()).withLocalDatacenter(getLocalDataCenter()).build();
    // }
}