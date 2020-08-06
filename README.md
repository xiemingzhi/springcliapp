# Setup Cassandra 
Create keyspace
```
CREATE KEYSPACE showcase WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
```

Create table 
```
CREATE TABLE person (
  id text,
  name text,
  age int,
  PRIMARY KEY (id)); 
```

# Build
```
mvn package
```

# Run
```
mvn spring-boot:run
```
