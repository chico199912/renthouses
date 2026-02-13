# RentHouses Platform (Spring Boot + Microserviços)

Projeto base para gestão de rendas de várias casas com arquitetura de microserviços preparada para frontend React e produção.

## Microserviços
- **asset-service (8081)**: senhorios, inquilinos e imóveis.
- **contract-service (8082)**: contratos, geração de PDF (base64), fiador e rescisão.
- **billing-service (8083)**: rendas pagas/não pagas, adiantamento, caução, referências MB/MBWay (mock IFTHEN), atraso e notificações.
- **notification-service (8084)**: envio/log de email e SMS.

## Fluxos implementados
1. Criar senhorio/inquilino/imóvel.
2. Criar contrato com cláusulas, renda, caução e adiantamento.
3. Geração de PDF do contrato e envio por email/SMS para inquilino e fiador.
4. Geração automática de pagamentos iniciais (adiantamento + caução).
5. Dashboard por senhorio e inquilino (pagas, pendentes, atrasadas).
6. Pagamento por referência em canal `MULTIBANCO` ou `MBWAY`.
7. Atraso: notifica senhorio, inquilino e fiador.
8. Terminar contrato: gera PDF de rescisão e notifica senhorio para envio ao inquilino (email/carta registada).

## Subir localmente
```bash
mvn -DskipTests package
```

Ou via Docker:
```bash
docker compose up --build
```

## OpenAPI
Cada serviço expõe Swagger em:
- `http://localhost:8081/swagger-ui/index.html`
- `http://localhost:8082/swagger-ui/index.html`
- `http://localhost:8083/swagger-ui/index.html`
- `http://localhost:8084/swagger-ui/index.html`

## Coleção Postman
Arquivo: `postman/RentHouses.postman_collection.json`

## Documentação adicional
- `docs/ARQUITETURA.md`
- `docs/ENDPOINTS.md`
- `docs/DEPLOY.md`
