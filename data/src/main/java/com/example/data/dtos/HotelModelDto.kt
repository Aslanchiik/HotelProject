package com.example.data.dtos

import com.example.data.utils.DataMapper
import com.example.domain.models.HotelModel
import com.google.gson.annotations.SerializedName

data class HotelModelDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("adress")
    val address: String,
    @SerializedName("minimal_price")
    val minimalPrice: Int,
    @SerializedName("price_for_it")
    val priceForIt: String,
    @SerializedName("rating")
    val rating: Int,
    @SerializedName("rating_name")
    val ratingName: String,
    @SerializedName("image_urls")
    val imageUrls: List<String>,
    @SerializedName("about_the_hotel")
    val aboutTheHotel: HotelInfoDto
) : DataMapper<HotelModel> {
    override fun mapToDomain() = HotelModel(
        id,
        name,
        address,
        minimalPrice,
        priceForIt,
        rating,
        ratingName,
        imageUrls,
        aboutTheHotel = aboutTheHotel.mapToDomain()
    )
}
