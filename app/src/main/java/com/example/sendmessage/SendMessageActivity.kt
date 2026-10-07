package com.example.sendmessage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.sendmessage.model.Message
import com.example.sendmessage.model.Person
import com.google.android.material.floatingactionbutton.FloatingActionButton


/**
 * Actividad principal (`SendMessageActivity`) encargada de capturar un mensaje de texto
 * introducido por el usuario y enviarlo a otra actividad.
 *
 * En esta actividad se practican los siguientes conceptos y operaciones:
 * <ol>
 *     <li>Creación y uso de componentes visuales (<code>EditText</code> y <code>FloatingActionButton</code>) en XML.</li>
 *     <li>Configuración de eventos de clic (<code>setOnClickListener</code>) en componentes visuales.</li>
 *     <li>Creación de un <code>Intent</code> explícito junto con un <code>Bundle</code> para pasar objetos serializables entre actividades.</li>
 *     <li>Gestión y monitorización de los métodos del ciclo de vida de la <code>Activity</code>.</li>
 *     <li>Trazabilidad y control de la pila de actividades mediante la consola LogCat.</li>
 * </ol>
 *
 * @author Lourdes Rodríguez
 * @version 1.1
 * @see android.widget.EditText
 * @see com.google.android.material.floatingactionbutton.FloatingActionButton
 * @see android.content.Intent
 * @see android.os.Bundle
 * @see ViewMessageActivity
 */
class SendMessageActivity : AppCompatActivity() {
   lateinit var etMessageText: EditText
   lateinit var btSend: FloatingActionButton

    companion object{
        const val TAG: String ="LogSendMessageActivity"
    }


    /**
     * Método llamado al crear la actividad. Se encarga de inicializar la interfaz de usuario,
     * enlazar los componentes visuales y configurar los eventos de clic.
     *
     * Como medida de aprendizaje, aquí se muestra cómo pasar datos dato a dato utilizando un [Bundle]:
     * ```kotlin
     * val intent = Intent(this, ViewMessageActivity::class.java)
     * val bundle = Bundle()
     * bundle.putString("KEY_MESSAGE", etMessageText.text.toString())
     * intent.putExtras(bundle)
     * startActivity(intent)
     * ```
     *
     * @param savedInstanceState Estado guardado previamente de la actividad, si lo hubiera.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_message)

        //Se obtiene el objeto view de la vista que se ha inflado
        etMessageText = findViewById(R.id.etMessageText)
        btSend = findViewById(R.id.btSend)

        btSend.setOnClickListener {
            sendMessage()
        }
        //Se escriben mensajes de depuración en la consola LogCat
        Log.d(TAG, "SendMessageActivity -> onCreate()")
    }

    /**
     * Crea un objeto de tipo [Message] que contiene la información del remitente,
     * el destinatario y el texto introducido por el usuario, y lo envía a
     * [ViewMessageActivity] mediante un [Intent] explícito con un [Bundle] serializable.
     */
    private fun sendMessage(){
        //1. Crear el Intent
        val intent = Intent(this, ViewMessageActivity::class.java)
        //2. Crear el Bundle
        val bundle = Bundle()
        //3. La información del mensaje
        val sender = Person("123456789A", "María","Cortés Martin")
        val receiver = Person ("98765432A", "Lourdes","Rodríguez")

        val message = Message(1, etMessageText.text.toString(),sender, receiver)
        //bundle.putSerializable("KEY_MESSAGE", message)
        bundle.putParcelable("KEY_MESSAGE", message)
        intent.putExtras(bundle)
        startActivity(intent)

    }
//region Ciclo de Vida de una Actividad

    /**
     * Se llama cuando la actividad se vuelve visible para el usuario.
     * Sigue a [onCreate] si es la primera vez que se inicia, o a [onRestart]
     * si la actividad vuelve a estar en primer plano después de haber sido detenida.
     */

    override fun onStart() {
        super.onStart()
    Log.d(TAG, "SendMessageActivity -> onStart()")
    }

    /**
     * Método que se ejecuta cuando la actividad comienza a interactuar con el usuario.
     * Es el estado en el que la aplicación permanece hasta que algo le quita el foco.
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "SendMessageActivity -> onResume()")

    }

    /**
     * Método del ciclo de vida que se llama cuando la actividad deja de estar en primer plano,
     * generalmente porque se está lanzando otra actividad o el usuario vuelve a la anterior.
     */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "SendMessageActivity -> onPause()")
    }

    /**
     * Método del ciclo de vida que se llama cuando la
     * actividad ya no es visible para el usuario.
     */
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "SendMessageActivity -> onStop()")
    }

    /**
     * Método final del ciclo de vida de la actividad, llamado antes de que sea destruida
     * por el sistema o se finalice manualmente.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "SendMessageActivity -> onDestroy()")
    }

//endregion
}
