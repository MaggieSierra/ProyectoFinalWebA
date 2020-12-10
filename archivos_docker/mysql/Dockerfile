FROM mysql:latest

ENV MYSQL_DATABASE="proyecto_tec_mvc"
ENV MYSQL_ROOT_PASSWORD = "root"

COPY ./database/proyecto_mvc.sql /docker-entrypoint-initdb.d/proyecto_mvc.sql