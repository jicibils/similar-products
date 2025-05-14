# Similar Products API – Spring Boot 🚀

Este proyecto expone un endpoint REST para obtener productos similares a uno dado, usando integraciones externas mockeadas.

---

## ✅ ¿Qué hace?

1. Exponde:  
   `GET /product/{productId}/similar`
2. Internamente llama a:
   - `GET /product/{productId}/similarids`
   - `GET /product/{id}` por cada similar
3. Devuelve:
   - Array de objetos `ProductDetail`, filtrando los que no existen

---

## 🔧 Tecnologías utilizadas

- Java 17
- Spring Boot 3
- WebClient (reactivo)
- Maven
- Docker
- K6 + InfluxDB + Grafana (para tests de performance)

---

## 📁 Arquitectura del código

| Capa         | Rol                                            |
| ------------ | ---------------------------------------------- |
| `controller` | Manejo de endpoints REST                       |
| `service`    | Lógica de negocio                              |
| `client`     | Llamadas a servicios externos (`WebClient`)    |
| `model`      | DTOs de entrada/salida                         |
| `exception`  | Manejo de errores como `404 Product Not Found` |
| `config`     | Config global (`WebClient`, puertos, etc)      |

---

## 🚀 ¿Cómo correr el proyecto?

### 1. Levantar los mocks y servicios de test

```bash
docker-compose up -d simulado influxdb grafana
```

Verificá que los mocks funcionen:

```bash
curl http://localhost:3001/product/1/similarids
```

---

### 2. Correr la app

```bash
mvn spring-boot:run
```

Por defecto corre en el puerto `5000`.  
Podés probarlo con:

```bash
curl http://localhost:5000/product/1/similar
```

---

### 3. Correr los tests automáticos de rendimiento

Desde la raíz del repo de los mocks (`backendDevTest`):

```bash
docker-compose run --rm k6 run scripts/test.js
```

📊 Ver resultados en Grafana:

👉 http://localhost:3000/d/Le2Ku9NMk/k6-performance-test

---

## 🛡️ Manejo de errores

- Si un producto similar no existe, se ignora
- Si falla una llamada externa, no rompe la app
- `404` controlado y resiliencia total

---

## 📌 Notas

- Compatible con macOS, Linux y Windows
- No se requiere Base de Datos
- `WebClient` está configurado con `host.docker.internal` para funcionar bien con Docker
- La app es robusta ante fallos de servicios externos

---

Desarrollado con 💪 por Juan Ignacio Cibils
