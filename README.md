# OTP Service

A Spring Boot application that provides OTP (One-Time Password) generation and validation through RESTful API endpoints.

## 🎯 Purpose

This is an exercise project to practice and learn:
- **Java** - Core programming language
- **Spring Boot** - Application framework and REST API development
- **PostgreSQL** - Database integration
- **Antigravity** - AI-assisted code generation
- **Claude AI** - Requirements analysis and technical design

## 📋 Features

- **OTP Generation**: Create time-limited one-time passwords with configurable length and expiration
- **OTP Validation**: Secure validation of OTP tokens with automatic expiration checking
- **Flexible Configuration**: Customizable token length and expiration time (minutes/seconds)
- **RESTful API**: Clean and simple REST endpoints
- **PostgreSQL Integration**: Persistent storage with JSON data structure

## 🛠️ Tech Stack

- **Java** (JDK 17+)
- **Spring Boot** 3.x
- **PostgreSQL** 
- **Maven** - Dependency management
- **Spring Data JPA** - Database access

## 🚀 API Endpoints

### Create OTP
```http
POST /otp/create
Content-Type: application/json

{
  "length": 6,        // Optional: Token length (default: 6)
  "minutes": 5,       // Optional: Expiration in minutes (default: 0)
  "seconds": 0        // Optional: Expiration in seconds (default: 0)
}
```

**Response:**
```json
{
  "guid": "550e8400-e29b-41d4-a716-446655440000",
  "token": "123456",
  "expiration": "2026-01-28T15:30:00Z"
}
```

### Validate OTP
```http
POST /otp/validate
Content-Type: application/json

{
  "guid": "550e8400-e29b-41d4-a716-446655440000",
  "token": "123456"
}
```

**Success Response (200):**
```json
{
  "message": "Success"
}
```

**Error Response (500):**
```json
{
  "error": "Invalid Token"
}
```

## 💾 Database Schema

**Table:** `otp_tokens`

| Column | Type | Description |
|--------|------|-------------|
| `guid` | UUID | Primary key, unique identifier |
| `data` | JSONB | Contains token and expiration data |

**JSON Structure:**
```json
{
  "token": "123456",
  "expiration": "2026-01-28T15:30:00Z"
}
```

## 🎓 Learning Approach

This project was developed using AI-assisted development tools to accelerate the learning process:

1. **Requirements Definition** - Clearly defined project requirements and specifications
2. **Claude AI** - Used for requirements structuring and technical design validation
3. **Antigravity** - Generated the Spring Boot application codebase
4. **Code Analysis** - Reviewing and understanding each component to learn Java patterns and Spring Boot best practices

### Why This Approach?

AI tools handle boilerplate code generation, allowing focus on:
- Understanding architectural decisions
- Learning Spring Boot patterns and conventions
- Grasping database integration concepts
- Studying security and validation implementations
- Analyzing best practices in real-world code

## 🏃 Getting Started

### Prerequisites
- JDK 17 or higher
- PostgreSQL 12+
- Maven 3.6+

### Installation

1. Clone the repository
```bash
git clone https://github.com/martintraverso/otp-service.git
cd otp-service
```

2. Configure database connection in `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/otpdb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🧪 Testing

Run tests with:
```bash
mvn test
```

## 📚 What I'm Learning

- Spring Boot application structure and configuration
- RESTful API design and implementation
- Spring Data JPA and PostgreSQL integration
- Request validation and error handling
- JSON data handling in PostgreSQL
- Time-based expiration logic
- Random number generation for security tokens

## 🤝 Contributing

This is a learning project, but feedback and suggestions are welcome! Feel free to:
- Open issues for bugs or improvements
- Submit pull requests
- Share learning resources

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 🔗 Connect

- **GitHub**: [@martintraverso](https://github.com/martintraverso)
- **LinkedIn**: [Martin Traverso](https://www.linkedin.com/in/martintraverso)

---

**Built with** ☕ **Java** | 🍃 **Spring Boot** | 🤖 **Antigravity** | 🧠 **Claude AI**

*From idea to working code in 60 minutes. Learning never stops.*
