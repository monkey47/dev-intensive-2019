package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(paramDate: Date = Date()): String {
    val tmp = paramDate.time - this.time
    val isFuture = tmp < 0
    val interval = Math.abs(tmp)
    if (interval < 1 * SECOND) {
        return "только что"
    } else if (interval > 360 * DAY) {
        return "более ${if (isFuture) "чем через год" else "года назад"}"
    }
    val core = when {
        interval < 45 * SECOND -> "несколько секунд"
        interval < 75 * SECOND -> "минуту"
        interval < 45 * MINUTE -> getUnit(interval / MINUTE, TimeUnits.MINUTE)
        interval < 75 * MINUTE -> getUnit(interval / MINUTE, TimeUnits.MINUTE)
        interval < 22 * HOUR -> getUnit(interval / HOUR, TimeUnits.HOUR)
        interval < 26 * HOUR -> "день"
        else -> getUnit(interval / DAY, TimeUnits.DAY)
    }
    return if (isFuture) "через $core" else "$core назад"
}

private fun getUnit(count: Long, units: TimeUnits): String {
    val unit = when (count % 10) {
        in 2..4 -> when (units) {
            TimeUnits.SECOND -> "секунды"
            TimeUnits.MINUTE -> "минуты"
            TimeUnits.HOUR -> "часа"
            TimeUnits.DAY -> "дня"
        }
        1L -> when (units) {
            TimeUnits.SECOND -> "секунду"
            TimeUnits.MINUTE -> "минуту"
            TimeUnits.HOUR -> "час"
            TimeUnits.DAY -> "дней"
        }
        else -> when (units) {
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
    }
    return "$count $unit"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

fun TimeUnits.plural(count: Int): String {
    return getUnit(count.toLong(), this)
}