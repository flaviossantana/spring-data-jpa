# spring-data-jpa
Repositórios, Consultas, Projeções


## Banco de dados MariaDB

- Subindo o banco de dados MariaDB em um container docker:
```bash
docker-compose up --build
```
- https://hub.docker.com/_/mariadb
- 
## Java Faker
- É uma bliblioteca que gera dados falsos para testes.
- Para adicionar a dependência no projeto:
```xml  
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```
- https://github.com/DiUS/java-faker
- 
## Query Methods
- São métodos que são criados no repositório para fazer consultas no banco de dados.
````java
public interface UserRepository extends Repository<User, Long> {
  List<User> findDistinctByLastnameAndFirstname(String emailAddress, String lastname);
}
````
- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
