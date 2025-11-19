# AGENTS.md - BrisimTech Development Log

This document tracks significant changes, features, and architectural decisions made in the BrisimTech project.

## Project Overview

**BrisimTech** is a Java Spring Boot backend application for electronics and software acquisition, developed for BrisimTech Kenya. The platform provides comprehensive customer management, service management, and notification systems.

### Technology Stack
- **Framework**: Spring Boot 3.3.4
- **Java Version**: 21
- **Database**: MySQL
- **Security**: Spring Security with JWT
- **API Documentation**: SpringDoc OpenAPI (Swagger)
- **Notifications**: Email (Spring Mail) and SMS (Twilio)
- **Build Tool**: Maven

---

## Initial Commit (August 5, 2025)

### Core Application Setup
- **Main Application**: `brisimApplication.java` - Spring Boot entry point
- **Server Configuration**: Running on port 8200 with context path `/brisimtech`
- **Database**: MySQL with Hibernate JPA (auto-update DDL)
- **Connection Pool**: HikariCP configured with optimized settings

### Module Architecture

#### 1. Customer Management (`cusmangt`)

**Profile Management**
- **Entity**: `Customer.java` - Customer data model
- **DTO**: `CustomerDTO.java` - Data transfer object for customer operations
- **Repository**: `CustomerRepository.java` - JPA repository for customer persistence
- **Service**: 
  - `ICustomerService.java` - Service interface
  - `CustomerService.java` - Implementation with business logic
- **Controller**: `CustomerController.java` - REST endpoints for customer operations

**Feedback & Review System**
- **Entity**: `Feedback.java` - Feedback data model
- **DTO**: `FeedbackDTO.java` - Feedback transfer object
- **Repository**: `FeedbackRepository.java` - Feedback persistence layer
- **Service**: `FeedbackService.java` - Feedback business logic
- **Controller**: `FeedbackController.java` - REST API for feedback operations

**Service Request Management**
- **Entity**: `ServiceRequest.java` - Service request model
- **DTO**: `ServiceRequestDTO.java` - Request transfer object
- **Repository**: `ServiceRequestRepository.java` - Request persistence
- **Service**: `ServiceRequestService.java` - Request handling logic
- **Controller**: `ServiceRequestController.java` - Request REST endpoints

#### 2. Security Module (`security`)

**Configuration**
- `SecurityConfig.java` - Spring Security configuration
- `CorsConfig.java` - CORS policy configuration
- `WebConfig.java` - Web MVC configuration

**Authentication & Authorization**
- **Entities**:
  - `User.java` - User account model
  - `Role.java` - Role-based access control
  - `Token.java` - JWT token management
  - `OTP.java` - One-time password for verification
- **Controllers**:
  - `AuthController.java` - Login/registration endpoints
  - `OTPController.java` - OTP generation and verification
- **Services**:
  - `CustomUserDetailsService.java` - User details for authentication
  - `TokenService.java` - JWT token management
  - `OTPService.java` - OTP generation and validation
- **Utilities**:
  - `JwtUtil.java` - JWT token utilities
  - `TokenProvider.java` - Token generation and validation
  - `PasswordEncoderUtil.java` - Password encryption
  - `SecretKeyGenerator.java` - Cryptographic key generation

**DTOs**
- `LoginRequest.java` - Login credentials
- `RegisterRequest.java` - User registration data
- `OTPRequest.java` - OTP verification request
- `TokenResponse.java` - Authentication token response

**Repositories**
- `UserRepository.java` - User data access
- `RoleRepository.java` - Role management
- `OTPRepository.java` - OTP storage

**Exception Handling**
- `UserNotFoundException.java` - User not found exception
- `OTPExpiredException.java` - Expired OTP exception

#### 3. Service Management (`servicemgt`)

**Service Catalog**
- **Entity**: `Svce.java` - Service model with detailed attributes
- **DTO**: `ServiceDTO.java` - Service transfer object
- **Repository**: `ServiceRepository.java` - Service persistence
- **Service**: `ServiceService.java` - Service business logic
- **Controller**: `ServiceController.java` - Service REST API
- **Mapper**: `ServiceMapper.java` - Entity-DTO mapping

**Service Requests**
- **Entity**: `SviceReqst.java` - Service request model
- **DTO**: `ServiceRequestDTO.java` - Request transfer object
- **Repository**: `SvceReqstRepository.java` - Request persistence
- **Service**: `SvceReqstService.java` - Request processing
- **Controller**: `SvceReqstController.java` - Request endpoints
- **Mapper**: `ServiceRequestMapper.java` - Entity-DTO mapping

**Installation & Maintenance Tracking**
- **Entities**:
  - `Installation.java` - Installation record model
  - `Mantainance.java` - Maintenance record model (note: spelling preserved from codebase)
- **Controllers**:
  - `InstallationController.java` - Installation management endpoints
  - `MaintenanceController.java` - Maintenance management endpoints
- **Repositories**:
  - `InstallationRepository.java` - Installation data access
  - `MaintenanceRepository.java` - Maintenance data access
- **Services**:
  - `InstallationService.java` - Installation business logic
  - `MaintenanceService.java` - Maintenance business logic

**Utilities**
- `StatusUpdater.java` - Automated status update functionality
- `ServiceStatus.java` - Service status enumeration

**Exception Handling**
- `ResourceNotFoundException.java` - Resource not found exception

#### 4. Notification & Reporting (`notreportmgt`)

**Notification System**
- **Entity**: `Notification.java` - Notification model
- **Repository**: `NotificationRepository.java` - Notification persistence
- **Services**:
  - `NotificationService.java` - Core notification logic
  - `SRequestService.java` - Service request notifications

**Communication Channels**
- **Email Service**: `EmailService.java` - Email notification implementation using Spring Mail
- **SMS Service**: `SmsService.java` - SMS notification via Twilio API
- **Controller**: `NotsController.java` - Notification management endpoints
- **Service**: `NotsService.java` - Unified notification service

### Key Features

1. **JWT Authentication**: Secure token-based authentication system
2. **OTP Verification**: Two-factor authentication support
3. **Role-Based Access Control**: User roles and permissions
4. **Multi-Channel Notifications**: Email and SMS support
5. **Service Lifecycle Management**: Track installations and maintenance
6. **Customer Feedback System**: Collect and manage customer reviews
7. **API Documentation**: Swagger UI available at `/swagger-ui.html`

### Configuration Highlights

- **Database**: MySQL on localhost:3306 with auto-creation enabled
- **Connection Pool**: HikariCP with 10 max connections, 5 minimum idle
- **API Documentation**: SpringDoc OpenAPI enabled
- **Email**: Gmail SMTP configured on port 587
- **SMS**: Twilio integration for SMS notifications
- **CORS**: Configured for cross-origin requests

### Dependencies

**Core Framework**
- Spring Boot Starter (Web, Data JPA, Security, Mail, Test)
- Jakarta Servlet API 6.0.0
- Jakarta Persistence API 3.1.0

**Security**
- Spring Security with OAuth2 JOSE
- JWT (JJWT) 0.9.1
- Spring Security Crypto

**Database**
- MySQL Connector 8.0.33
- Hibernate Validator 6.2.0

**Utilities**
- Lombok 1.18.34
- Google Guava 32.1.3-jre
- JetBrains Annotations 24.1.0

**Communication**
- Spring Boot Mail Starter
- Twilio SDK 8.32.0

**Documentation**
- SpringDoc OpenAPI Starter 2.6.0

---

## Future Considerations

Based on the current architecture, potential areas for enhancement:
- Add comprehensive unit and integration tests
- Implement caching layer (Redis) for performance
- Add audit logging for security and compliance
- Implement API rate limiting
- Add monitoring and health checks
- Consider microservices architecture for scalability
- Implement file upload/download for service documentation
- Add real-time notifications using WebSockets
- Implement payment gateway integration

---

*This document is maintained by the development team and updated with each significant change to the codebase.*
