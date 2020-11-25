package com.skill_factory.unit4

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.skill_factory.unit4.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val user = User(50_000, arrayListOf(Stock("Яндекс", 5000, R.drawable.yandex_icon), Stock("Mail.ru Group", 2175, R.drawable.mailru_icon)))
        binding.user = user

        (binding.recyclerView.adapter) = StockAdapter(user.stockList)
        val r = Random(System.currentTimeMillis())
        countDownTimer = object : CountDownTimer(10_000, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                user.cash += -1000 + r.nextInt(2000)
                user.stockList.forEach {
                    it.price += (-0.1 * it.price).toInt() + r.nextInt((0.2 * it.price).toInt())
                }
            }

            override fun onFinish() {
                countDownTimer.start()
            }
        }
        countDownTimer.start()
    }
}
