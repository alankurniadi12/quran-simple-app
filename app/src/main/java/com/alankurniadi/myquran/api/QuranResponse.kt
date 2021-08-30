package com.alankurniadi.myquran.api

import com.google.gson.annotations.SerializedName

data class QuranResponse(

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("ayat")
	val ayat: Int,

	@field:SerializedName("arti")
	val arti: String,

	@field:SerializedName("asma")
	val asma: String,

	@field:SerializedName("audio")
	val audio: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("nomor")
	val nomor: String

)
