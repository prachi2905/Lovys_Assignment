package com.lovys.assignment.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.lovys.assignment.R


class FavouriteButtonView : ConstraintLayout {
    private lateinit var favouriteIcon: ImageView
    private lateinit var progressBarLayout: ProgressBar

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    var isActive: Boolean = false
        set(value) {
            field = value
            switchButtonImage()
        }

    private fun init(attrs: AttributeSet?) {
        inflate(context, R.layout.layout_favourite_button, this)

    }

    fun startLoading() {
        progressBarLayout = findViewById<ProgressBar>(R.id.progressBarLayout)
        favouriteIcon = findViewById<ImageView>(R.id.favouriteIcon)
        progressBarLayout.visibility = View.VISIBLE
        favouriteIcon.visibility = View.INVISIBLE
    }

    private fun switchButtonImage() {
        progressBarLayout.visibility = View.GONE
        favouriteIcon.visibility = View.VISIBLE
        val imageInt =
            if (isActive) R.drawable.ic_heart else R.drawable.ic_heart_empty
        favouriteIcon.setImageDrawable(ContextCompat.getDrawable(context, imageInt))
    }

}