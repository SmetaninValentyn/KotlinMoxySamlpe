package com.kotlingithubapi.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by User on 16.07.2017.
 */
data class Contributor(
        val login: String,
        @SerializedName("html_url") val url: String?,
        val contributions: Int? = 0,
        @SerializedName("avatar_url") val avatarUrl: String?) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(url)
        parcel.writeValue(contributions)
        parcel.writeString(avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contributor> {
        override fun createFromParcel(parcel: Parcel): Contributor {
            return Contributor(parcel)
        }

        override fun newArray(size: Int): Array<Contributor?> {
            return arrayOfNulls(size)
        }
    }

}