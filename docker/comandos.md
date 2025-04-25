## Comandos para iniciar o container docker

### Perfil de desenvolvimento
**Construir o container**  
`docker-compose -f docker/docker-compose.dev.yaml --env-file .env up --build`

**Iniciar o container**  
`docker-compose -f docker/docker-compose.dev.yaml --env-file .env start`

**Parar o container**  
`docker-compose -f docker/docker-compose.dev.yaml --env-file .env stop`

---

