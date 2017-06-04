package com.cdev.achievementsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cdev.achievementview.AchievementView

class MainActivity : AppCompatActivity() {

    private lateinit var achievementViewBlue: AchievementView
    private lateinit var achievementViewGreen: AchievementView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        achievementViewBlue = findViewById(R.id.achievement_view) as AchievementView
        achievementViewGreen = findViewById(R.id.achievement_view_green) as AchievementView

        findViewById(R.id.btn_start_animation).setOnClickListener {
            achievementViewBlue.show("Congratulations", "You just got a hacker badge!")
            achievementViewGreen.show("Achievement unlocked!")
        }

        findViewById(R.id.btn_clear_animation).setOnClickListener {
            achievementViewBlue.clearAnimation()
            achievementViewGreen.clearAnimation()
        }
    }

    override fun onPause() {
        super.onPause()
        achievementViewBlue.clearAnimation()
        achievementViewGreen.clearAnimation()
    }
}
