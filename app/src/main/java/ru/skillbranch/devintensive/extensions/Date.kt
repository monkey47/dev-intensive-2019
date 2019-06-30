package ru.skillbranch.devintensive.extensions

import android.R
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return  dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(paramDate: Date=Date()): String {
    val tmp = paramDate.time - this.time
    val isFuture = tmp < 0
    var suffix = ""
    var ending = " назад"
    if (isFuture) {
        suffix = "через "
        ending = ""
    }
    var interval = Math.abs( tmp )
    return when {
        interval < 1 * SECOND -> "только что"
        interval < 45 * SECOND-> "${suffix}несколько секунд$ending"
        interval < 75 * SECOND -> "${suffix}минуту$ending"
        interval < 45 * MINUTE -> {
            var count = interval / MINUTE
            "$suffix${GetUnit(count, TimeUnits.MINUTE)}$ending"
        }
        interval < 75 * MINUTE -> {
            var count = interval / MINUTE
            return "$suffix${GetUnit(count, TimeUnits.MINUTE)}$ending"
        }
        interval < 22 * HOUR -> {
            var count = interval / HOUR
            return "$suffix${GetUnit(count, TimeUnits.HOUR)}$ending"
        }
        interval < 26 * HOUR -> "${suffix}день$ending"
        interval < 360 * DAY -> {
            var count = interval / DAY
            "$suffix${GetUnit(count, TimeUnits.DAY)}$ending"
        }
        else -> "более ${if(isFuture) "чем через год" else "года назад"}"
    }
}

private fun GetUnit(count:Long, units:TimeUnits) :String {
    val tmp = count % 10
    var result:String
    if (tmp in 2 .. 4) {
        result = when(units) {
            TimeUnits.SECOND -> "секунды"
            TimeUnits.MINUTE -> "минуты"
            TimeUnits.HOUR -> "часа"
            TimeUnits.DAY -> "дня"
        }
    }
    else if (tmp == 1L) {
        result = when(units) {
            TimeUnits.SECOND -> "секунду"
            TimeUnits.MINUTE -> "минуту"
            TimeUnits.HOUR -> "час"
            TimeUnits.DAY -> "дней"
        }
    }
    else {
        result = when(units) {
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
    }
    return "$count $result"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}