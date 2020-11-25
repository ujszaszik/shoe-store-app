package com.udacity.shoestore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String,
    var size: Int,
    var company: String,
    var description: String,
    var state: ShoeValueState = ShoeValueState.TO_BE_SENT
) : Parcelable {

    companion object {
        internal fun initial(): Shoe {
            return Shoe(
                "",
                0,
                "",
                ""
            )
        }
    }

    fun areAllFieldsSet(): Boolean {
        return name.isNotBlank()
                && size != 0
                && company.isNotBlank()
                && description.isNotBlank()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Shoe
        return name == other.name && size == other.size && company == other.company
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + size.hashCode()
        result = 31 * result + company.hashCode()
        return result
    }

    fun getCapital(): String = name[0].toUpperCase().toString()

}