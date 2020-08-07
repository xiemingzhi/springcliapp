# Setup Cassandra 

Docker start cassandra 

Create keyspace
```
sudo docker run -it --network cassandra_default --rm cassandra cqlsh cassandra
CREATE KEYSPACE showcase WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
```

List keyspaces 
```
sudo docker run -it --network cassandra_default --rm cassandra cqlsh -e "describe keyspaces" cassandra
```

Create table 
```
CREATE TABLE person (
  id text,
  name text,
  age int,
  PRIMARY KEY (id)); 
```

List tables
```
sudo docker run -it --network cassandra_default --rm cassandra cqlsh -e "use showcase;describe tables" cassandra
```

# Build
```
mvn package
```

# Run
```
mvn spring-boot:run
```
