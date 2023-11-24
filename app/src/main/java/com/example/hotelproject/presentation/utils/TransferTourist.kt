package com.example.hotelproject.presentation.utils

object TransferTourist {
    enum class TouristNumber {
        Первый, Второй, Третий, Четвертый, Пятый, Шестой, Седьмой, Восьмой, Девятый, Десятый, Следующий
    }

    fun getNumber(index: Int): TouristNumber {
        return TouristNumber.values().getOrNull(index) ?: TouristNumber.Следующий
    }
}