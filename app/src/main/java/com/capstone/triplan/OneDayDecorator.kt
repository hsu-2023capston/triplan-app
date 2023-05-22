package com.capstone.triplan

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.compose.ui.text.font.Font
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class OneDayDecorator: DayViewDecorator {
    private val date = CalendarDay.today()

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day == date
    }



    @SuppressLint("ResourceAsColor")
    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(Font(R.font.notosanskr_bold))
        view?.addSpan(StyleSpan(Typeface.BOLD))
        view?.addSpan(RelativeSizeSpan(1.4f))
        view?.addSpan(ForegroundColorSpan(R.color.blue_300))
    }
}