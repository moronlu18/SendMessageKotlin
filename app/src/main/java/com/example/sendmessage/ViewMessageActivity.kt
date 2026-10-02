package com.example.sendmessage

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.sendmessage.model.Message

/**
 * Actividad secundaria (`ViewMessageActivity`) encargada de recibir y mostrar la información
 * enviada desde [SendMessageActivity].
 *
 * En esta actividad se practican los siguientes conceptos:
 * <ol>
 *     <li>Recuperación de datos mediante <code>Intent</code> y <code>Bundle</code>.</li>
 *     <li>Recepción de objetos complejos implementando <code>Serializable</code>.</li>
 *     <li>Asociación de datos a componentes visuales (<code>TextView</code>).</li>
 *     <li>Seguimiento del ciclo de vida de la actividad y consola LogCat.</li>
 * </ol>
 *
 * @author Lourdes Rodríguez
 * @version 1.2
 * @see android.widget.TextView
 * @see android.os.Bundle
 * @see SendMessageActivity
 * @see com.example.sendmessage.model.Message
 */
class ViewMessageActivity : AppCompatActivity() {

    companion object{
        const val TAG: String ="LogViewMessageActivity "
    }

    /**
     * Método onCreate encargado de inicializar la actividad de visualización.
     *
     * Como medida de aprendizaje, aquí se muestra cómo se recogería un dato tipo String simple
     * mediante un [Bundle] y [Bundle.getString]:
     * ```kotlin
     * val bundle = intent.extras
     * if (bundle != null) {
     *     val messageText = bundle.getString("KEY_MESSAGE")
     *     tvMessage.text = messageText
     * }
     * ```
     *
     * En la implementación actual, se recibe un objeto [Message] serializable utilizando
     * [Bundle.getSerializable].
     *
     * @param savedInstanceState Estado guardado de la actividad.
     */
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_message)
        
        // Se obtienen los objetos TextView donde mostraremos el remitente y el contenido
        val tvSender = findViewById<TextView>(R.id.tvSender)
        val tvMessage = findViewById<TextView>(R.id.tvMessageContent)

        // Recuperamos los extras (el Bundle) del intent que inició esta actividad
        val bundle = intent.extras

        // Verificamos que el bundle no sea nulo para evitar errores
        if (bundle != null) {
            // Recuperamos el objeto Message serializable enviado desde SendMessageActivity
            val message = bundle.getSerializable("KEY_MESSAGE") as? Message

            if (message != null) {
                // Se asigna el nombre y apellido del remitente a tvSender
                //tvSender.text = "${message.sender.name} ${message.sender.surname}"
                //Cadenas con formato: plantilla "%1$s %2$s" desde los recursos de strings,
                tvSender.text = getString(
                    R.string.sender_full_name,
                    message.sender.name,
                    message.sender.surname
                )
                // Se asigna el contenido del mensaje a tvMessage
                tvMessage.text = message.content
            }
        }
        Log.d(TAG, "ViewMessageActivity -> onCreate()")

    }

    //region Ciclo de Vida de una Actividad

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "ViewMessageActivity -> onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "ViewMessageActivity -> onResume()")

    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "ViewMessageActivity -> onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "ViewMessageActivity -> onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "ViewMessageActivity -> onDestroy()")
    }

//endregion
}
