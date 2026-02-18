FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=root

COPY ./seed.sql /docker-entrypoint-initdb.d/
