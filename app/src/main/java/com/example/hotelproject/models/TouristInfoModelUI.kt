package com.example.hotelproject.models

import com.example.hotelproject.presentation.base.IBaseDiffModel

data class TouristInfoModelUI(
    var passwordNumber: String?=null,
    override var id: Int,
    var name: String?=null,
    var surname: String?=null,
    var dateOfBirthday: String?=null,
    var citizenship: String?=null,
    var validityPeriod: String?=null
) : IBaseDiffModel<Int>