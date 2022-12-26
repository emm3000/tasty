# Arquitectura de la aplicación

La aplicación esta divide en 3 modulos de la base de `Clean Architecture`, Capa de UI o presentación, capa de datos y capa de dominio.

En la capa de presentación se usa el patrón MVVM, para separar la lógica de negocio y la presentación de los datos en la interfaz de usuario.

---

## Data Layer - Repository

Se usa este componente como mediador entre las diferentes fuentes de datos `DATA-SOURCE` (fuentes remota, preferencias locales, base de datos, etc) y los casos de uso donde se manejan la lógica de negocio.

Además, el repositorio debe ser responsable de mapear la información de los modelos (DTOs, o model response) a las clases de datos específicas de la capa de dominio.

## Data Layer - Data Source

La clase de fuente de datos, tiene la responsabilidad de consultar una sola fuente de datos ya sea: lectura de archivos locales, una fuente de red o una base de datos local.

---

## Domain Layer - Use Case

El propósito de los casos de uso es realizar cualquier acción específica que ocurra en una pantalla. Estas acciones puede tratarse de obtener o enviar datos, filtrar elementos. Además de mantaner logica de negocio, este interactua con los repositorios que se encargan solo de solicitar y recibir datos.

## Domain Layer - Porque usar los casos de uso

En una arquitectura limpia los casos de uso añaden una capa extra de complejidad a una pantalla. Los casos de uso se justifican en proyectos que cuentan con compleja logica de negocio, donde se necesitan interactuar entre distintos repositorios, combinar respuestas y los datos de entrada y salida.

Ademas añadir una capa extra entre el repositorio facilita con la organización y los pruebas unitarias.

---

## UI Layer - ViewModel

Se uso este componente para conectar una pantalla con cualquier caso de uso que se necesite. El viewModel consume eventos de la UI, escuchar el cambio de un input, eventos de scroll etc. Se activan los casos de uso y se procesa/mapean los datos recibidos a un `ViewState` que es un modelo que se usa para representar los datos que se van a mostrar en la UI.

---

## Librerías

### Desarrollo

- Para la injección de dependencias se uso `Koin`, de facil configuración que nos brinda diferentes funciones de extensión para facil acceso a las dependencias desde una vista Android. [Koin](https://insert-koin.io/docs/reference/koin-android/get-instances)

```kotlin
 implementation("io.insert-koin:koin-android:3.3.1")
 implementation("io.insert-koin:koin-core:3.3.0")
```

- Para las peticiones HTTP usamos `Retrofit`, okhttp3, para usar interceptores y logs.

```kotlin
 implementation("com.squareup.retrofit2:retrofit:2.9.0")
 implementation("com.squareup.retrofit2:converter-gson:2.9.0")
 implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
```

- `Coil` para carga de imagenes.

```kotlin
 implementation("io.coil-kt:coil:2.2.2")
```

- `Navigation Component`, librería de androidx para la navegación entre fragmentes.

```kotlin
 implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
 implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
```

- `Android Sdk Google Map` se añadio las librerías para trabajar con mapas, y utilitarios que implementan diferentes funciones de extensión, compatibilidad con corrutinas y flow.

```kotlin
implementation("com.google.android.gms:play-services-maps:18.1.0")
implementation("com.google.maps.android:maps-ktx:3.0.0")
implementation("com.google.maps.android:maps-utils-ktx:3.0.0")
```

### Testing

- `Truth` `JUnit` `Coroutines-Test` `Mockk`. Truth para realizar aserciones. JUnit librería estandar para los test unitarios en Android, coroutines-test, para manejar funciones suspendidas y correr los flow test. Mockk librería para mockear clases desarrollada en kotlin.
- [Truth](https://truth.dev/)
- [JUnit](https://junit.org/junit5/)
- [Mockk](https://mockk.io/)

```kotlin
testImplementation("junit:junit:4.13.2")
testImplementation("io.mockk:mockk:1.12.2")
testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
testImplementation("com.google.truth:truth:1.1.3")
```
