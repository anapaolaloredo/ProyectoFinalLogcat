## Mobile App - Final Project 

===============================================================================
  ProyectoFinalPrueba - Aplicacion de Seguimiento de Habitos
===============================================================================

REQUISITOS
  - Android Studio (ultima version estable recomendada)
  - JDK 11
  - Dispositivo Android o emulador con API 24 o superior

COMPILAR Y EJECUTAR DESDE ANDROID STUDIO
  1. Abrir el proyecto en Android Studio.
  2. Esperar a que Gradle sincronice las dependencias.
  3. Seleccionar un dispositivo o emulador.
  4. Ejecutar la configuracion "app" (Run > Run 'app' o Shift+F10).

COMPILAR Y EJECUTAR DESDE LINEA DE COMANDOS
  1. Abrir una terminal en la raiz del proyecto.
  2. Generar el APK de depuracion:
       ./gradlew :app:assembleDebug
  3. El APK se encuentra en:
       app/build/outputs/apk/debug/app-debug.apk
  4. Instalar en un dispositivo conectado:
       adb install -r app/build/outputs/apk/debug/app-debug.apk

PRUEBAS
  Ejecutar pruebas unitarias (JVM):
    ./gradlew :app:testDebugUnitTest

  Ejecutar una clase de prueba especifica:
    ./gradlew :app:testDebugUnitTest --tests "com.anapaolaloredo.proyectofinalprueba.HabitUtilsTest"

  Ejecutar pruebas de instrumentacion (requiere dispositivo/emulador):
    ./gradlew :app:connectedDebugAndroidTest

LINT Y VERIFICACION
  Ejecutar analisis estatico:
    ./gradlew :app:lintDebug

FUNCIONAMIENTO DE LA APP
  1. Al abrir la app se muestra la pantalla de inicio de sesion.
  2. Si no tiene cuenta, use el boton de registro para crear una.
  3. Tras iniciar sesion, accede a la pantalla principal donde puede:
     - Agregar nuevos habitos con el boton correspondiente.
     - Marcar habitos como completados (una vez por dia).
     - Ver su racha, progreso y nivel en la pestaña de perfil.
  4. La sesion se mantiene guardada hasta que cierre sesion manualmente.

===============================================================================
