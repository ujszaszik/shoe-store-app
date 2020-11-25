package com.udacity.shoestore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoeSize(var sizeValue: String, var type: ShoeSizeType) : Parcelable {

    companion object {
        internal const val CONVERT_VALUE = 33
        private const val DEFAULT_VALUE = 6
        internal val INITIAL = ShoeSize(DEFAULT_VALUE.toString(), ShoeSizeType.US)
    }

    fun isTypeEuropean(): Boolean = type == ShoeSizeType.EU

    fun isTypeAmerican(): Boolean = type == ShoeSizeType.US

    private fun convertFrom(size: Int, type: ShoeSizeType): ShoeSize {
        return when (type) {
            ShoeSizeType.EU -> ShoeSize(
                size.minus(CONVERT_VALUE).toString(),
                ShoeSizeType.US
            )
            ShoeSizeType.US -> ShoeSize(
                size.plus(CONVERT_VALUE).toString(),
                ShoeSizeType.EU
            )
        }
    }

    private fun isSameSizeAs(other: ShoeSize): Boolean {
        if (type == other.type && sizeValue == other.sizeValue) return true
        val converted = convertFrom(other.sizeValue.toInt(), other.type)
        return this.type != other.type && this.sizeValue == converted.sizeValue
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ShoeSize
        return isSameSizeAs(other)
    }

    override fun hashCode(): Int {
        val unified = if (this.type == ShoeSizeType.US) this else convertFrom(
            sizeValue.toInt(),
            ShoeSizeType.EU
        )
        var result = unified.sizeValue.hashCode()
        result = 31 * result + unified.type.hashCode()
        return result
    }


}

enum class ShoeSizeType {
    EU, US
}