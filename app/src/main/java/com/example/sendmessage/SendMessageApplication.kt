package com.example.sendmessage

import android.app.Application

/**
 * Clase de aplicación global (`SendMessageApplication`) que extiende de [Application].
 *
 * Se declara en el archivo `AndroidManifest.xml` mediante el atributo `android:name=".SendMessageApplication"`
 * dentro de la etiqueta `<application>` para inicializar componentes globales o configuraciones
 * a nivel de toda la aplicación antes de que se cree cualquier Actividad.
 */
class SendMessageApplication : Application()
