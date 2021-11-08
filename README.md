# Vinilos App UniAndes

### Requisitos mínimos del proyecto
* Android Studio 3.2 o Mas alto.
* Mínimo android SDK version 21
* Java version 1.8
* Kotlin version 1.3.7 o Mas alto.

### Instalación de la aplicación
* Clonar Repositorio usando Android Studio.
* Android Studio le pedirá que cambie la ruta de su sdk de Android a su máquina local.
* Asegúrese de estar en la rama ** master ** y permita que Android Studio termine de compilar.
* Ejecutar aplicación

### Documentación Api
* [Vinilos Api Uniandes](https://documenter.getpostman.com/view/8840688/SzmZd1L6#b7d73d9e-bc61-4ce1-a89a-1bde475986a6) Para ver los métodos API.

### Funciones de la aplicación
* [MVVM APP Architecture](https://developer.android.com/jetpack/guide) con patrón de repositorio.
* Implementación de [Android Navigation library](https://developer.android.com/guide/navigation))
* [Data binding](https://developer.android.com/jetpack/androidx/releases/databinding) de componentes en diseños y fuentes de datos.
* Soporte sin conexión a medida que los datos se guardan en la base de datos sql light usando [ROOM](https://developer.android.com/topic/libraries/architecture/room)

### Escenarios de prueba E2E
* Para la ejecución de las pruebas debe dirigirse a la sección com.example.vinilosapp(androidTest) --> presentation.vm --> AlbumSearchViewModelTest, clic derecho sobre el archivo y seleccionar la opción Run 'AlbumSearchViewModelTest..'
* Para la ejecución de las pruebas debe dirigirse a la sección com.example.vinilosapp(androidTest) --> ui --> SearchListInstrumentedTest, clic derecho sobre el archivo y seleccionar la opción Run 'SearchListInstrumentedTest..'
