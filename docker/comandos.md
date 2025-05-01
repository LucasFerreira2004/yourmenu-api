# Comandos para iniciar o container docker

## Perfil de desenvolvimento  
*Perfil usado para bando de dados e testes pelos desenvolvedores*  
  
**Construir o container**  
`docker-compose -f docker/docker-compose.dev.yaml --env-file .env up --build`

**Iniciar o container**  
`docker-compose -f docker/docker-compose.dev.yaml --env-file .env start`

**Parar o container**  
`docker-compose -f docker/docker-compose.dev.yaml --env-file .env stop`
  
**Resetar volumes**  
`docker-compose -f docker/docker-compose.dev.yaml down -v`

---

## Perfil de staging
*Perfil usado para pelos para adicionar a aplicação em container para testes de desenvolvedores do **frontend***

**Construir o container**  
`docker-compose -f docker/docker-compose.staging.yaml --env-file .env up --build`

**Iniciar o container**  
`docker-compose -f docker/docker-compose.staging.yaml --env-file .env start`

**Parar o container**  
`docker-compose -f docker/docker-compose.staging.yaml --env-file .env stop`

**Resetar volumes**  
`docker-compose -f docker/docker-compose.staging.yaml down -v`
---

## Liberar memória  
Em casos que builds frequentes, pode ser necessário liberar memória do docker, a seguir estão alguns comando úteis:  

**Limpa containers parados**  
`docker container prune`  

**Limpa imagens não usadas**  
`docker image prune -a`  

**Limpa volumes não usados (atenção se guardar dados no banco)**  
`docker volume prune`  

**Limpa cache de build (libera bastante espaço)**  
`docker builder prune`  
