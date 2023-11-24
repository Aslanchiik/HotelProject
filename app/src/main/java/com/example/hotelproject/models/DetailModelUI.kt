package com.example.hotelproject.models

import com.example.domain.models.DetailModel

data class DetailModelUI(
    val id: Int,
    val hotelName: String,
    val hotelAdress: String,
    val horating: Int,
    val ratingName: String,
    val departure: String,
    val arrivalCountry: String,
    val tourDateStart: String,
    val tourDateStop: String,
    val numberOfNights: Int,
    val room: String,
    val nutrition: String,
    val tourPrice: Int,
    val fuelCharge: Int,
    val serviceCharge: Int,
)

fun DetailModel.toUI() = DetailModelUI(
    id,
    hotelName,
    hotelAdress,
    horating,
    ratingName,
    departure,
    arrivalCountry,
    tourDateStart,
    tourDateStop,
    numberOfNights,
    room,
    nutrition,
    tourPrice,
    fuelCharge,
    serviceCharge
)