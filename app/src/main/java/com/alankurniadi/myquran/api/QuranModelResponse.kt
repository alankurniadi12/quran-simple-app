package com.alankurniadi.myquran.api

import com.google.gson.annotations.SerializedName

data class QuranModelResponse(

    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: List<DataItem>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: String
)

data class DataItem(

    @field:SerializedName("number")
    val number: Int,

    @field:SerializedName("sequence")
    val sequence: Int,

    @field:SerializedName("numberOfVerses")
    val numberOfVerses: Int,

    @field:SerializedName("revelation")
    val revelation: Revelation,

    @field:SerializedName("name")
    val name: Name,

    @field:SerializedName("tafsir")
    val tafsir: Tafsir
)

data class Name(

    @field:SerializedName("translation")
    val translation: Translation,

    @field:SerializedName("short")
    val jsonMemberShort: String,

    @field:SerializedName("long")
    val jsonMemberLong: String,

    @field:SerializedName("transliteration")
    val transliteration: Transliteration
)

data class Revelation(

    @field:SerializedName("en")
    val en: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("arab")
    val arab: String
)

data class Tafsir(

    @field:SerializedName("id")
    val id: String
)

data class Transliteration(

    @field:SerializedName("en")
    val en: String,

    @field:SerializedName("id")
    val id: String
)

data class Translation(

    @field:SerializedName("en")
    val en: String,

    @field:SerializedName("id")
    val id: String
)
