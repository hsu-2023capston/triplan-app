package com.myosa.triplan

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.Calendar

class SundayDecorator : DayViewDecorator {
    private val calendar: Calendar = Calendar.getInstance()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day?.copyTo(calendar)
        var weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(ForegroundColorSpan(Color.RED))
    }
}