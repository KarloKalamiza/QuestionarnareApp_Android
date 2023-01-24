package hr.algebra.questionarnareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import hr.algebra.questionarnareapp.Utils.hideKeyboard
import hr.algebra.questionarnareapp.Utils.showToast
import hr.algebra.questionarnareapp.databinding.ActivityMainBinding
import hr.algebra.questionarnareapp.model.Question
import hr.algebra.questionarnareapp.repo.QuestionRepo
import java.time.Clock

private const val INDEX = "hr.algebra.questionarnareapp.index"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var questionIndex = 0
    private var question:Question? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.etAnswer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnNext.isEnabled = text?.isNotBlank() ?: false
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })

        binding.btnNext.setOnClickListener { 
            if(question!!.isCorrect(binding.etAnswer.text.toString().trim())){
                //Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
                showToast(getString(R.string.correct), Toast.LENGTH_SHORT)
            } else {
                //Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
                showToast(getString(R.string.incorrect), Toast.LENGTH_SHORT)
            }
            refresh()
        }
    }

    private fun refresh() {
        binding.etAnswer.text.clear()
        questionIndex++
        setCurrentQuestion()
    }

    override fun onResume() {
        super.onResume()
        setCurrentQuestion()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        super.onRestoreInstanceState(savedInstanceState)
        questionIndex = savedInstanceState.getInt(INDEX)
    }

    private fun setCurrentQuestion() {
        question = QuestionRepo[questionIndex]
        if (question != null){
            binding.tvQuestions.text = question.toString()
        } else {
            binding.tvQuestions.text = getString(R.string.thankYou)
            setWidgetsVisibility(View.INVISIBLE)
        }
        hideKeyboard()
    }

    private fun setWidgetsVisibility(visibility: Int) {
        binding.btnNext.visibility = visibility
        binding.etAnswer.visibility = visibility
    }
}