package com.cdev.achievementview

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView


/**
 * Created by aszabo on 6/1/17.
 */

class AchievementView : RelativeLayout {
    private lateinit var attributes: AchievementViewAttributes
    private lateinit var leftLayout: LinearLayout
    private lateinit var rightLayout: LinearLayout

    private var expandValueAnimator: ValueAnimator? = null
    private var collapseValueAnimator: ValueAnimator? = null
    private var revealAnimator: Animator? = null
    private var concealAnimator: Animator? = null

    private lateinit var firstLineTextView: TextView
    private lateinit var secondLineTextView: TextView

    private var isStarted: Boolean = false

    constructor(context: Context) : super(context) {
        initAttributes()
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    private fun initAttributes() {
        attributes = AchievementViewAttributes()
        attributes.colorLeft = R.color.achievement_color_left
        attributes.colorRight = R.color.achievement_color_right
        attributes.textColorFirstLine = R.color.white
        attributes.textColorSecondLine = R.color.white
        attributes.drawableLeft = R.drawable.trophy
    }

    private fun initAttributes(attrs: AttributeSet?) {
        attributes = AchievementViewAttributes()

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AchievementView, 0, 0)

        try {
            attributes.revealDuration = typedArray.getInteger(R.styleable.AchievementView_revealDuration,
                    AchievementViewAttributes.DEFAULT_REVEAL_DURATION.toInt()).toLong()
            attributes.expandDuration = typedArray.getInteger(R.styleable.AchievementView_expandDuration,
                    AchievementViewAttributes.DEFAULT_EXPAND_DURATION.toInt()).toLong()
            attributes.collapseStartDelay = typedArray.getInteger(R.styleable.AchievementView_collapseStartDelay,
                    AchievementViewAttributes.DEFAULT_COLLAPSE_START_DELAY.toInt()).toLong()
            attributes.concealStartDelay = typedArray.getInteger(R.styleable.AchievementView_concealStartDelay,
                    AchievementViewAttributes.DEFAULT_CONCEAL_START_DELAY.toInt()).toLong()

            attributes.colorLeft = typedArray.getColor(R.styleable.AchievementView_colorLeft,
                    ContextCompat.getColor(context, R.color.achievement_color_left))
            attributes.colorRight = typedArray.getColor(R.styleable.AchievementView_colorRight,
                    ContextCompat.getColor(context, R.color.achievement_color_right))

            attributes.textColorFirstLine = typedArray.getColor(R.styleable.AchievementView_textColorFirstLine,
                    ContextCompat.getColor(context, R.color.white))
            attributes.textColorSecondLine = typedArray.getColor(R.styleable.AchievementView_textColorSecondLine,
                    ContextCompat.getColor(context, R.color.white))

            attributes.drawableLeft = typedArray.getResourceId(R.styleable.AchievementView_drawableLeft, -1)

            attributes.textSizeFirstLine = typedArray.getDimensionPixelSize(R.styleable.AchievementView_textSizeFirstLine,
                    AchievementViewAttributes.DEFAULT_TEXT_SIZE.toInt()).toFloat()

            attributes.textSizeSecondLine = typedArray.getDimensionPixelSize(R.styleable.AchievementView_textSizeSecondLine,
                    AchievementViewAttributes.DEFAULT_TEXT_SIZE.toInt()).toFloat()

            attributes.rightPartWidth = typedArray.getDimensionPixelSize(R.styleable.AchievementView_rightPartWidth,
                    AchievementViewAttributes.DEFAULT_RIGHT_PART_WIDTH.toInt()).toFloat()

        } finally {
            typedArray.recycle()
        }
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_achievement_view, this)
        visibility = View.INVISIBLE

        leftLayout = findViewById(R.id.achievement_left_layout) as LinearLayout
        rightLayout = findViewById(R.id.achievement_right_layout) as LinearLayout

        firstLineTextView = findViewById(R.id.achievement_tv_first_line) as TextView
        secondLineTextView = findViewById(R.id.achievement_tv_second_line) as TextView

        // Set the left drawable
        if (attributes.drawableLeft != -1) {
            (findViewById(R.id.img_trophy) as ImageView).setImageResource(attributes.drawableLeft)
        }

        // Set left part background color
        (findViewById(R.id.achievement_left_relative_layout) as RelativeLayout).setShapeColor(attributes.colorLeft)

        // Set right part background color
        rightLayout.setShapeColor(attributes.colorRight)

        // Set text color
        firstLineTextView.setTextColor(attributes.textColorFirstLine)
        secondLineTextView.setTextColor(attributes.textColorSecondLine)

        // Set text size
        firstLineTextView.textSize = attributes.textSizeFirstLine
        secondLineTextView.textSize = attributes.textSizeSecondLine
    }

    override fun clearAnimation() {
        this.visibility = View.INVISIBLE

        expandValueAnimator?.cancel()
        collapseValueAnimator?.cancel()
        revealAnimator?.cancel()
        concealAnimator?.cancel()

        isStarted = false
    }

    fun show(firstLine: String) {
        show(firstLine, null)
    }

    fun show(firstLine: String, secondLine: String?) {

        if (isAnimationStarted()) {
            return
        }

        isStarted = true

        attributes.firstLine = firstLine
        attributes.secondLine = secondLine

        firstLineTextView.text = attributes.firstLine
        secondLineTextView.text = attributes.secondLine

        firstLineTextView.visibility = View.GONE
        secondLineTextView.visibility = View.GONE

        startRevealAnimation()
    }

    private fun isAnimationStarted(): Boolean {
        return isStarted
    }

    /**
     * Start to reveal the left part of the view
     */
    private fun startRevealAnimation() {
        isStarted = true

        revealAnimator = ViewAnimationUtils
                .createCircularReveal(leftLayout, leftLayout.measuredWidth / 2, leftLayout.measuredHeight / 2, 0f, 80f)

        visibility = View.VISIBLE
        revealAnimator?.duration = attributes.revealDuration
        revealAnimator?.start()

        revealAnimator?.addEndListener { startExpandAnimation() }
        revealAnimator?.addCancelListener {
            it ->
            it.removeAllListeners()
        }
    }

    /**
     * Start to expand the width of the right part
     */
    private fun startExpandAnimation() {
        rightLayout.visibility = View.VISIBLE
        expandValueAnimator = ValueAnimator.ofFloat(0F, attributes.rightPartWidth)

        expandValueAnimator?.addUpdateListener {
            val newVal: Float = expandValueAnimator?.animatedValue as Float
            rightLayout.updateWidth(newVal.toInt())
        }

        expandValueAnimator?.duration = attributes.expandDuration
        expandValueAnimator?.interpolator = AccelerateDecelerateInterpolator()
        expandValueAnimator?.addEndListener {

            showText()
            startCollapseAnimation()
        }

        // In case of cancel remove all listeners and set back the right part's width to 0
        expandValueAnimator?.addCancelListener {
            it ->
            it.removeAllListeners()
            rightLayout.updateWidth(0)
        }

        expandValueAnimator?.start()
    }

    private fun showText() {
        val fadeIn: AlphaAnimation = AlphaAnimation(0.0f, 1.0f)

        fadeIn.duration = 200

        firstLineTextView.startAnimation(fadeIn)
        firstLineTextView.visibility = View.VISIBLE

        if (attributes.secondLine != null) {
            secondLineTextView.startAnimation(fadeIn)
            secondLineTextView.visibility = View.VISIBLE
        }
    }

    /**
     * Start to collapse the width of the right part
     */
    private fun startCollapseAnimation() {

        collapseValueAnimator = ValueAnimator.ofFloat(attributes.rightPartWidth, 0F)
        collapseValueAnimator?.addUpdateListener {
            val newVal: Float = collapseValueAnimator?.animatedValue as Float
            rightLayout.updateWidth(newVal.toInt())
        }

        collapseValueAnimator?.duration = attributes.expandDuration
        collapseValueAnimator?.interpolator = AccelerateDecelerateInterpolator()
        collapseValueAnimator?.startDelay = attributes.collapseStartDelay

        collapseValueAnimator?.addStartListener { hideText() }
        collapseValueAnimator?.addEndListener { startConcealAnimation() }
        collapseValueAnimator?.addCancelListener {
            it ->
            it.removeAllListeners()
            rightLayout.updateWidth(0)
        }
        collapseValueAnimator?.start()
    }

    private fun hideText() {
        val fadeOut: AlphaAnimation = AlphaAnimation(1.0f, 0.0f)

        fadeOut.duration = 200
        fadeOut.addEndListener {
            firstLineTextView.visibility = View.GONE
            secondLineTextView.visibility = View.GONE
        }

        firstLineTextView.startAnimation(fadeOut)

        if (attributes.secondLine != null) {
            secondLineTextView.startAnimation(fadeOut)
        }
    }

    /**
     * Start to conceal the left part of the view
     */
    private fun startConcealAnimation() {
        concealAnimator = ViewAnimationUtils.createCircularReveal(leftLayout, leftLayout.measuredWidth / 2,
                leftLayout.measuredHeight / 2, 80f, 0f)
        concealAnimator?.duration = attributes.revealDuration
        concealAnimator?.startDelay = attributes.concealStartDelay
        concealAnimator?.addEndListener {
            visibility = View.INVISIBLE
            isStarted = false
        }
        concealAnimator?.start()
    }
}
