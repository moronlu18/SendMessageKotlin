package com.example.sendmessage.model

import java.io.Serializable

/**
 * Representa un mensaje intercambiado entre dos instancias de [Person].
 *
 * El modelo puede serializarse para facilitar el paso del mensaje entre
 * componentes de la aplicación.
 *
 * @property id **Identificador** único del mensaje.
 * @property content **Contenido** textual del mensaje.
 * @property sender [Person] que **envía** el mensaje.
 * @property receiver [Person] que **recibe** el mensaje.
 */
data class Message(
    val id: Int,
    val content: String,
    val sender: Person,
    val receiver: Person,
) : Serializable