# Similar Products API – Spring Boot 🚀

This project exposes a REST API endpoint to retrieve similar products for a given one, using mocked external integrations.

---

## ✅ What it does

1. Exposes:  
   `GET /product/{productId}/similar`
2. Internally calls:
   - `GET /product/{productId}/similarids`
   - `GET /product/{id}` for each similar product
3. Returns:
   - An array of `ProductDetail` objects, filtering out non-existing ones

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3
- WebClient (reactive)
- Maven
- Docker
- K6 + InfluxDB + Grafana (for performance tests)

---

## 📁 Project Architecture

| Layer        | Responsibility                                 |
| ------------ | ---------------------------------------------- |
| `controller` | REST API layer                                 |
| `service`    | Business logic                                 |
| `client`     | External service calls (`WebClient`)           |
| `model`      | Input/output DTOs                              |
| `exception`  | Error handling (e.g., `404 Product Not Found`) |
| `config`     | Global config (`WebClient`, port, etc.)        |

---

## 🚀 How to Run the Project

### 1. Start mocks and performance testing services

```bash
docker-compose up -d simulado influxdb grafana
```

Make sure mocks are running:

```bash
curl http://localhost:3001/product/1/similarids
```

---

### 2. Run the application

```bash
mvn spring-boot:run
```

By default, the app runs on port `5000`.  
You can test it using:

```bash
curl http://localhost:5000/product/1/similar
```

---

### 3. Run Performance Tests

Make sure the application is running, then run:

```bash
docker-compose run --rm k6 run scripts/test.js
```

📊 View results in Grafana:

👉 http://localhost:3000/d/Le2Ku9NMk/k6-performance-test

---

## 🛡️ Error Handling

- If a similar product is not found, it is skipped
- External service failures are gracefully handled
- `404` responses are properly managed with resilience in mind

---

### 🧪 Unit Testing

The project includes a basic unit test for the core service logic:

- `ProductServiceTest.java`: verifies the logic for retrieving similar products using mocked dependencies.

#### 📦 How to run the tests

From the root directory of the project:

```bash
mvn test
```

This will run all unit tests inside `src/test/java`.

---

## 📌 Notes

- Compatible with macOS, Linux, and Windows
- No database required
- `WebClient` is configured to work properly with Docker via `host.docker.internal`
- The app is resilient to external service failures

---

Crafted by Juan Ignacio Cibils
