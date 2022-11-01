# spring-data-jpa
Repositórios, Consultas, Projeções


## Banco de dados MariaDB

- Subindo o banco de dados MariaDB em um container docker:
```bash
docker-compose up --build
```

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

## Referências
- https://hub.docker.com/_/mariadb
- https://github.com/DiUS/java-faker
