package com.anacarolina.todonow

import android.os.Parcel
import android.os.Parcelable

data class Tarefa(val titulo: String, val descricao: String, val prioridade: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(titulo)
        parcel.writeString(descricao)
        parcel.writeString(prioridade)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tarefa> {
        override fun createFromParcel(parcel: Parcel): Tarefa {
            return Tarefa(parcel)
        }

        override fun newArray(size: Int): Array<Tarefa?> {
            return arrayOfNulls(size)
        }
    }
}
