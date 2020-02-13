package com.example.contentproviderdemo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Celebrity( val firstName : String,
                      val lastName : String,
                      val job : String,
                      val favorite : String,
                      val id : String) : Parcelable