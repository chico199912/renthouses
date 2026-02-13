# Arquitetura

## Domínio
- **Senhorio** pode ter N casas arrendadas.
- **Inquilino** pode ter N casas arrendadas.
- **Contrato** associa casa + senhorio + inquilino + opcionalmente fiador.
- **Renda** inclui mensalidades, rendas de adiantamento e caução.

## Serviços
- `asset-service`: master data (users e imóveis)
- `contract-service`: lifecycle de contrato e PDFs
- `billing-service`: faturas/rendas/referências e estado
- `notification-service`: email e sms (adaptável para providers reais)

## Integrações externas previstas
- **Email**: SMTP / Sendgrid / SES
- **SMS**: Twilio / MessageBird
- **IFTHEN MB/MBWay**: substituindo geração mock de referência no billing-service

## Observabilidade e produção
- Actuator ativo em todos os serviços.
- Deploy recomendado com Kubernetes + Ingress + Mongo Atlas.
