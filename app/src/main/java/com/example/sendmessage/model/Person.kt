package com.example.sendmessage.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Representa a una persona dentro de la aplicación.
 *
 * Sus datos identifican al participante que envía o recibe un **mensaje**.
 *  - Contiene un objeto ``` Persona ``` llamado *sender*
 *
 * @property dni Documento nacional de identidad de la persona.
 * @property name Nombre de la persona.
 * @property surname Apellido de la persona.
 */
@Parcelize
data class Person (val dni: String, val name: String, val surname: String): Parcelable