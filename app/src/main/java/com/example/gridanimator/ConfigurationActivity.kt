package com.example.gridanimator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        createGridButton.setOnClickListener {
            GridActivity.apply {
                elementSize = if (elementSizeEditText.text.isNullOrEmpty()) {
                    AppConfig.DEFAULT_ELEMENT_SIZE
                } else {
                    elementSizeEditText.text.toString().toInt()
                }

                elementAnimationDelay = if (elementAnimationSpeedEditText.text.isNullOrEmpty()) {
                    AppConfig.DEFAULT_ELEMENT_ANIMATION_DELAY
                } else {
                    elementAnimationSpeedEditText.text.toString().toInt()
                }

                elementSpacing = if (elementSpacingEditText.text.isNullOrEmpty()) {
                    AppConfig.DEFAULT_ELEMENT_SPACING
                } else {
                    elementSpacingEditText.text.toString().toInt()
                }

                numOfElements = if (elementNumberEditText.text.isNullOrEmpty()) {
                    AppConfig.DEFAULT_ELEMENT_NUM
                } else {
                    elementNumberEditText.text.toString().toInt()
                }
            }
            startActivity(Intent(this, GridActivity::class.java))
        }
    }
}
