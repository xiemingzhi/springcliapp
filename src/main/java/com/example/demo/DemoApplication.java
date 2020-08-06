package com.example.demo;

import com.datastax.oss.driver.api.core.CqlSession;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	protected static Person newPerson(String name, int age) {
		return new Person(UUID.randomUUID().toString(), name, age);
	}
	
	@Autowired
	CassandraConfiguration cassConfig;

	@Autowired
	PersonRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//System.out.println("finished startup");
	}

	@Override
	public void run(String... arg0) throws Exception {
		//usingTemplate();
		Iterable<Person> persons = repository.findAll();
		for (Person person : persons) {
			LOGGER.info("person id {}, name {}", person.getId(), person.getName());
		}
		cassConfig.cassandraSession().destroy();
	}

	private void usingTemplate() {
		//CqlSession cqlSession = CqlSession.builder().withKeyspace("showcase").build();

		//CassandraOperations template = new CassandraTemplate(cqlSession);
		CassandraOperations template = cassConfig.cassandraTemplate();

		Person jonDoe = template.insert(newPerson("Jon Doe", 40));

		LOGGER.info(template.selectOne(Query.query(Criteria.where("id").is(jonDoe.getId())), Person.class).getId());

		//template.truncate(Person.class);
		//cqlSession.close();
		cassConfig.cassandraSession().destroy();
	}
	
}
