# Deploy para produção

## Estratégia recomendada
1. MongoDB Atlas para dados por serviço.
2. Imagens Docker versionadas por serviço.
3. Kubernetes (AKS/EKS/GKE) com 1 deployment por microserviço.
4. Config via variáveis de ambiente + Secret Manager.
5. API Gateway/WAF na frente dos serviços.

## Variáveis importantes
- `MONGODB_URI`
- `NOTIFICATION_SERVICE_URL`
- `BILLING_SERVICE_URL`

## Hardening futuro
- OAuth2/JWT (Keycloak/Auth0)
- Retry/Circuit breaker (Resilience4j)
- Mensageria assíncrona (Kafka/RabbitMQ)
- Assinatura digital real de PDFs
- Integração oficial IFTHEN para MB/MBWay
