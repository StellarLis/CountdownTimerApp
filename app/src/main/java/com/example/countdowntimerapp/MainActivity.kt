package com.example.countdowntimerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.example.countdowntimerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        init()
    }
    private fun init() {
        binding.apply {
            startBtn.setOnClickListener {
                var sec: Long = 0
                var min: Long = 0
                var hours: Long = 0
                var allTogether: Long = 0
                if (edSeconds.text.isNotEmpty()) {
                    sec = edSeconds.text.toString().toLong() * 1000
                }
                if (edMinutes.text.isNotEmpty()) {
                    min = edMinutes.text.toString().toLong() * 60000
                }
                if (edHours.text.isNotEmpty()) {
                    hours = edHours.text.toString().toLong() * 3600000
                }
                allTogether = sec + min + hours
                startTimer(allTogether)
                resetBtn.visibility = View.VISIBLE
            }
            resetBtn.setOnClickListener {
                resetTimer()
            }
        }
    }

    private fun startTimer(timeMillis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(timeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvHours.text = ((millisUntilFinished / 1000) / 60 / 60).toInt().toString()
                binding.tvMinutes.text = ((millisUntilFinished / 1000) / 60 % 60).toInt().toString()
                binding.tvSeconds.text = ((millisUntilFinished / 1000) % 60).toInt().toString()
            }

            override fun onFinish() {
                binding.resetBtn.visibility = View.INVISIBLE
            }
        }.start()
    }
    private fun resetTimer() {
        timer?.cancel()
        binding.resetBtn.visibility = View.INVISIBLE
        binding.tvHours.text = "00"
        binding.tvMinutes.text = "00"
        binding.tvSeconds.text = "00"
    }
}