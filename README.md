# spring-data-jpa
Repositórios, Consultas, Projeções


## Banco de dados MariaDB

- Subindo o banco de dados MariaDB em um container docker:
```bash
docker pull mariadb  
docker run --name mariadb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=algafood -p 3306:3306 -d mariadb:10.5.8
```


## Referências
- https://hub.docker.com/_/mariadb
