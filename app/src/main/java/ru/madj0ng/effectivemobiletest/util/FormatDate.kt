package ru.madj0ng.effectivemobiletest.util

import java.text.SimpleDateFormat
import java.util.Locale

class FormatDate {
    fun formatDateToDayMonth(dateString: String, locale: String = "ru"): String =
        SimpleDateFormat("dd MMMM ", Locale(locale))
            .format(
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .parse(dateString)!!
            )
}