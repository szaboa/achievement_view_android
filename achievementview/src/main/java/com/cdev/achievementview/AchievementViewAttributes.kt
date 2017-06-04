package com.cdev.achievementview

/**
 * Created by aszabo on 6/2/17.
 */
data class AchievementViewAttributes(
        var revealDuration: Long = AchievementViewAttributes.DEFAULT_REVEAL_DURATION,
        var expandDuration: Long = AchievementViewAttributes.DEFAULT_EXPAND_DURATION,
        var collapseStartDelay: Long = AchievementViewAttributes.DEFAULT_COLLAPSE_START_DELAY,
        var concealStartDelay: Long = AchievementViewAttributes.DEFAULT_CONCEAL_START_DELAY,
        var rightPartWidth: Float = AchievementViewAttributes.DEFAULT_RIGHT_PART_WIDTH,
        var colorLeft: Int = -1,
        var colorRight: Int = -1,
        var textColorFirstLine: Int = -1,
        var textColorSecondLine: Int = -1,
        var textSizeFirstLine: Float = AchievementViewAttributes.DEFAULT_TEXT_SIZE,
        var textSizeSecondLine: Float = AchievementViewAttributes.DEFAULT_TEXT_SIZE,
        var secondLine: String? = null,
        var firstLine: String? = null,
        var drawableLeft: Int = -1
) {
    companion object {
        val DEFAULT_REVEAL_DURATION: Long = 500
        val DEFAULT_EXPAND_DURATION: Long = 500
        val DEFAULT_COLLAPSE_START_DELAY: Long = 1500
        val DEFAULT_CONCEAL_START_DELAY: Long = 500
        val DEFAULT_RIGHT_PART_WIDTH: Float = 300F
        val DEFAULT_TEXT_SIZE: Float = 14F
    }
}