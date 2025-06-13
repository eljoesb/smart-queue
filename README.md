# Smart Queue

A modern, intelligent appointment management system built with Quarkus, following clean architecture principles. Smart Queue helps healthcare providers optimize their operations and improve patient experience through intelligent scheduling and queue management.

## 🎯 Purpose

Smart Queue is designed to revolutionize clinic operations by providing an intelligent solution for appointment management, patient communication, and queue optimization. It leverages AI to predict wait times and optimize scheduling, making healthcare more accessible and efficient.

## 🔑 Key Problems Solved

1. **Efficient Appointment Scheduling**

   - Manages patient appointments effectively
   - Reduces scheduling conflicts and double-bookings
   - Optimizes clinic resources and staff time

2. **Smart Wait Time Management**

   - Uses AI to predict optimal appointment times
   - Estimates wait times for patients
   - Helps patients plan their visits better
   - Reduces patient frustration from long waits

3. **Patient Communication**

   - Supports multiple notification channels (Email, SMS, Push)
   - Keeps patients informed about their appointment status
   - Sends reminders and updates automatically

4. **Clinic Operations Optimization**
   - Tracks appointment statuses (Scheduled, In Progress, Completed, Cancelled)
   - Manages patient records efficiently
   - Provides insights into clinic operations

## ✨ Key Features

### 1. Appointment Management

- Schedule new appointments
- Reschedule existing appointments
- Cancel appointments
- View appointment details
- Track appointment status

### 2. Patient Management

- Maintain patient records
- Track patient history
- Manage patient preferences
- Handle patient notifications

### 3. Intelligent Features

- AI-powered wait time prediction
- Optimal appointment time suggestions
- No-show probability prediction
- Smart queue management

### 4. Technical Highlights

- Built with modern Java technologies (Quarkus)
- Clean architecture design
- Scalable and maintainable
- RESTful API interface
- PostgreSQL database for reliable data storage

## 💡 Benefits

### For Patients

- Reduced wait times
- Better appointment scheduling
- Improved communication
- More convenient healthcare access

### For Clinics

- Improved operational efficiency
- Better resource utilization
- Reduced administrative workload
- Enhanced patient satisfaction

### For Healthcare Providers

- More organized schedule
- Better patient management
- Reduced stress from scheduling conflicts
- More time for patient care

## 🏥 Use Cases

### 1. Primary Care Clinics

- Manage daily patient appointments
- Handle walk-in patients
- Coordinate with specialists

### 2. Specialist Practices

- Schedule follow-up appointments
- Manage referral appointments
- Coordinate with primary care providers

### 3. Urgent Care Centers

- Handle emergency appointments
- Manage walk-in patients
- Coordinate with emergency services

### 4. Medical Centers

- Coordinate multiple departments
- Manage complex scheduling
- Handle multiple healthcare providers

## 🏗️ Project Structure

The project follows a clean architecture approach with the following layers:

- **Domain Layer**: Contains the core business logic and entities
- **Application Layer**: Contains use cases and business rules
- **Infrastructure Layer**: Contains implementations of repositories and external services
- **API Layer**: Contains REST controllers and API endpoints

## 🚀 Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.8.x or later
- Docker & Docker Compose

### Quick Start with Docker Compose

Puedes levantar la aplicación y la base de datos juntas usando Docker Compose. Esto es lo más sencillo para desarrollo y pruebas.

1. Compila la aplicación Quarkus:

```bash
./mvnw clean package -DskipTests
```

2. Levanta los servicios (app y base de datos):

```bash
docker-compose up --build
```

Esto construirá la imagen de la app y levantará tanto la base de datos PostgreSQL como la aplicación Quarkus. La app esperará a que la base de datos esté lista antes de iniciar.

- La app estará disponible en: http://localhost:8080
- La base de datos estará disponible en: localhost:5432 (usuario: postgres, password: postgres, base: smartqueue)

> **Nota:** La configuración de la base de datos en `src/main/resources/application.properties` ya está preparada para funcionar con Docker Compose. Si necesitas cambiar credenciales, hazlo tanto en el `docker-compose.yml` como en ese archivo.

### Ejecución en modo desarrollo

Si prefieres correr la app en modo desarrollo (hot reload):

```bash
./mvnw quarkus:dev
```

Asegúrate de tener PostgreSQL corriendo localmente o usa Docker Compose solo con el servicio de la base de datos:

```bash
docker-compose up postgres
```

### Ejecución manual (sin Docker Compose)

1. Crea la base de datos PostgreSQL llamada `smartqueue` y configura el archivo `src/main/resources/application.properties` según tus credenciales.
2. Compila y ejecuta la app:

```bash
./mvnw clean package -DskipTests
java -jar target/quarkus-app/quarkus-run.jar
```

## 📚 API Documentation

Una vez corriendo la app, accede a:

- Swagger UI: http://localhost:8080/q/swagger-ui
- OpenAPI: http://localhost:8080/q/openapi

## 🧪 Testing

Run the tests with:

```bash
./mvnw test
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🔧 Development

### Running in Dev Mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

### Packaging and Running

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it's not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## 📞 Support

For support, please open an issue in the GitHub repository or contact the development team.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/smart-queue-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
