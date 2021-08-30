package com.alankurniadi.myquran.api

import com.google.gson.annotations.SerializedName

data class QuranSurahModelResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Revelationn(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("arab")
	val arab: String
)

data class Number(

	@field:SerializedName("inQuran")
	val inQuran: Int,

	@field:SerializedName("inSurah")
	val inSurah: Int
)

data class Transliterationn(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("id")
	val id: String
)

data class Namee(

	@field:SerializedName("translation")
	val translation: Translation,

	@field:SerializedName("short")
	val jsonMemberShort: String,

	@field:SerializedName("long")
	val jsonMemberLong: String,

	@field:SerializedName("transliteration")
	val transliteration: Transliterationn
)

data class Sajda(

	@field:SerializedName("obligatory")
	val obligatory: Boolean,

	@field:SerializedName("recommended")
	val recommended: Boolean
)

data class Audio(

	@field:SerializedName("secondary")
	val secondary: List<String>,

	@field:SerializedName("primary")
	val primary: String
)

data class Meta(

	@field:SerializedName("hizbQuarter")
	val hizbQuarter: Int,

	@field:SerializedName("ruku")
	val ruku: Int,

	@field:SerializedName("manzil")
	val manzil: Int,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("sajda")
	val sajda: Sajda,

	@field:SerializedName("juz")
	val juz: Int
)

data class Data(

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("sequence")
	val sequence: Int,

	@field:SerializedName("numberOfVerses")
	val numberOfVerses: Int,

	@field:SerializedName("revelation")
	val revelation: Revelationn,

	@field:SerializedName("name")
	val name: Namee,

	@field:SerializedName("tafsir")
	val tafsir: Tafsir,

	@field:SerializedName("preBismillah")
	val preBismillah: PreBismillah,

	@field:SerializedName("verses")
	val verses: List<VersesItem>
)

data class PreBismillah(

	@field:SerializedName("text")
	val text: arab,

	@field:SerializedName("translation")
	val translation: Any
)

data class arab(

	@field:SerializedName("arab")
	val arab: String
)

data class VersesItem(

	@field:SerializedName("number")
	val number: Number,

	@field:SerializedName("meta")
	val meta: Meta,

	@field:SerializedName("translation")
	val translation: Translationn,

	@field:SerializedName("tafsir")
	val tafsir: Tafsirr,

	@field:SerializedName("text")
	val text: Text,

	@field:SerializedName("audio")
	val audio: Audio
)

data class Text(

	@field:SerializedName("transliteration")
	val transliteration: Transliteration,

	@field:SerializedName("arab")
	val arab: String
)

data class Translationn(

	@field:SerializedName("en")
	val en: String,

	@field:SerializedName("id")
	val id: String
)

data class Id(

	@field:SerializedName("short")
	val jsonMemberShort: String,

	@field:SerializedName("long")
	val jsonMemberLong: String
)

data class Tafsirr(

	@field:SerializedName("id")
	val id: Id
)
