package com.biswa.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import com.biswa.a7minuteworkout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExerciseBinding? = null

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1
    // Declaring a global text to speech object
    private var tts : TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)
        // Displaying the back button
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = Constants.defaultExerciseList()

        tts = TextToSpeech(this, this)

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

        setupRestView()

    }

    private fun setupRestView(){
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE

        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        speakOut("Rest for 10 seconds")
        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition+1].getName()

        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseLabel?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        // Reset the 30 sec timer
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        speakOut(exerciseList!![currentExercisePosition].getName())
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressbar?.progress = restProgress

        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressbar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()

            }

            override fun onFinish() {
//                Toast.makeText(this@ExerciseActivity,
//                "Start Exercise",
//                Toast.LENGTH_LONG).show()
                currentExercisePosition++
                setupExerciseView()
            }

        }.start()

    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
//                Toast.makeText(this@ExerciseActivity,
//                    "30 seconds are over, lets go the rest view",
//                    Toast.LENGTH_LONG).show()
                if (currentExercisePosition < exerciseList?.size!! - 1){
                    setupRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity,
                   "Congratulations, You have completed the 7-minute workout.",
                    Toast.LENGTH_LONG).show()
                }
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        if (tts != null){
            tts?.stop()
            tts?.shutdown()
        }
        binding = null
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "The language specified is not supported.")
            }else{
                Log.e("TTS", "Initialization Failed.")
            }
        }
    }

    private fun speakOut(text : String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}