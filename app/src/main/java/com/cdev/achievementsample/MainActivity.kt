package com.cdev.achievementsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
            achievementViewGreen.show("Achievement unlocked!", "Level 1 Human")
        }

        findViewById(R.id.btn_clear_animation).setOnClickListener {
            achievementViewBlue.clearAnimation()
            achievementViewGreen.clearAnimation()
        }
    }
}
