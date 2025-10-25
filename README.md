# microservices-usuarios-cursos
  proyecto pensado para aplicar los conocimientos de microservicios docker y kubernates


com.base
├── domain
│   ├── model                    <- Entidades de dominio (agregados, value objects)
│   ├── port
│   │   ├── in                   <- PUERTOS DE ENTRADA (casos de uso - interfaces)
│   │   │   └── service          <- Ej: CrearUsuarioUseCase, ObtenerCursoUseCase
│   │   └── out                  <- ⭐ PUERTOS DE SALIDA (interfaces para adapters)
│   │       ├── repository       <- Ej: UsuarioRepositoryPort, CursoRepositoryPort
│   │       ├── messaging        <- Ej: EventPublisherPort, MessageSenderPort
│   │       └── external         <- Ej: NotificationServicePort, PaymentGatewayPort
│   └── exception                <- ⭐ EXCEPCIONES DE DOMINIO
│       ├── UsuarioNoEncontradoException.java
│       ├── CursoYaInscritoException.java
│       └── DomainException.java (base)
│
├── application
│   ├── service                  <- Implementación de casos de uso (usa puertos de salida)
│   │   ├── UsuarioService.java
│   │   └── CursoService.java
│   ├── dto                      <- ⭐ DTOs con Java Records
│   │   ├── request
│   │   │   ├── CrearUsuarioRequest.java
│   │   │   └── InscribirCursoRequest.java
│   │   └── response
│   │       ├── UsuarioResponse.java
│   │       └── CursoResponse.java
│   └── mapper                   <- ⭐ Mappers entre DTOs y Domain Models
│       ├── UsuarioMapper.java
│       └── CursoMapper.java
│
├── adapter
│   ├── in
│   │   └── web                  <- Adaptadores de ENTRADA
│   │       ├── controller
│   │       │   ├── UsuarioController.java
│   │       │   └── CursoController.java
│   │       ├── exception        <- Exception handlers REST
│   │       │   └── GlobalExceptionHandler.java
│   │       └── dto              <- DTOs específicos de REST (si son diferentes)
│   │
│   └── out
│       ├── persistence          <- Adaptador de SALIDA - Base de datos
│       │   ├── entity           <- Entidades JPA
│       │   │   ├── UsuarioEntity.java
│       │   │   └── CursoEntity.java
│       │   ├── repository       <- Spring Data JPA repositories
│       │   │   ├
│       │   │   └── UsuarioJpaRepository.java
│       │   ├── adapter          <- Implementación de puertos OUT
│       │   │   ├
│       │   │   └── UsuarioRepositoryAdapter.java
│       │   └── mapper           <- Mappers Entity <-> Domain Model
│       │       ├
│       │       └── UsuarioEntityMapper.java
│       │
│       ├── messaging            <- Adaptador de SALIDA - Mensajería
│       │   ├── kafka
│       │   │   ├── KafkaEventPublisher.java
│       │   │   └── dto
│       │   │       └── UsuarioCreadoEvent.java
│       │   └── mapper
│       │       └── EventMapper.java
│       │
│       └── external             <- Adaptador de SALIDA - APIs externas
│           ├── notification
│           │   ├── NotificationServiceAdapter.java
│           │   └── dto
│           │       └── NotificationRequest.java
│           └── feign            <- Clientes Feign (si usas)
│               └── CursosExternoClient.java
│
└── infrastructure               <- ⭐ Configuración e infraestructura transversal
├── config                   <- ⭐ Configuraciones Spring
│   ├── BeanConfiguration.java
│   ├── DatabaseConfig.java
│   ├── KafkaConfig.java
│   ├── SecurityConfig.java
│   └── SwaggerConfig.java
│
├── util                     <- ⭐ Utilidades generales
│   ├── DateUtil.java
│   ├── StringUtil.java
│   └── ValidationUtil.java
│
├── constant                 <- Constantes globales
│   └── AppConstants.java
│
└── security                 <- Componentes de seguridad
├── JwtTokenProvider.java
└── SecurityUtil.java






| Ventaja | Descripción |
|---------|-------------|
| **Inversión de dependencias** | El dominio no depende de la infraestructura |
| **Testabilidad** | Fácil mockear los use cases en tests |
| **Claridad** | Cada interfaz representa un caso de uso específico |
| **Flexibilidad** | Puedes cambiar la implementación sin afectar los controllers |
| **Documentación** | Las interfaces documentan claramente qué puede hacer el sistema |

## 🎯 Flujo completo:
```
Controller (adapter/in/web/controller)
  ↓ (usa)
Use Case Interface (Puerto IN) (domain/in/services)
  ↓ (implementado por)
Service (Application)(application/services)
  ↓ (usa)
Repository Interface (Puerto OUT) (adapter/out/persistence/repositories)
  ↓ (implementado por)
Repository Adapter (Persistence)(adapter/out/persistence/adapter)