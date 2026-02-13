# Endpoints principais

## Asset Service (`/api/assets`)
- `POST /landlords`
- `GET /landlords`
- `POST /tenants`
- `GET /tenants`
- `POST /properties`
- `GET /properties`
- `GET /landlords/{landlordId}/properties`
- `GET /tenants/{tenantId}/properties`

## Contract Service (`/api/contracts`)
- `POST /` cria contrato, gera PDF, notifica, cria adiantamento/caução
- `GET /` lista contratos
- `POST /{id}/terminate` termina contrato e gera PDF de rescisão

## Billing Service (`/api/billing`)
- `POST /installments` cria parcela/renda
- `GET /installments` lista todas
- `POST /installments/{id}/pay?channel=MBWAY|MULTIBANCO`
- `POST /jobs/mark-overdue` marca atraso e notifica envolvidos
- `GET /dashboard/tenant/{tenantEmail}`
- `GET /dashboard/landlord/{landlordEmail}`
- `GET /contracts/{contractId}/installments`

## Notification Service (`/api/notifications`)
- `POST /email`
- `POST /sms`
- `GET /?recipient=` histórico
