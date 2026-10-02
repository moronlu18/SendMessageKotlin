package com.example.sendmessage.model

import java.io.Serializable

/**
 * Representa a una persona dentro de la aplicación.
 *
 * Sus datos identifican al participante que envía o recibe un mensaje.
 *
 * @property dni Documento nacional de identidad de la persona.
 * @property name Nombre de la persona.
 * @property surname Apellido de la persona.
 */
data class Person (val dni: String, val name: String, val surname: String): Serializable