package hr.algebra.questionarnareapp.repo

import hr.algebra.questionarnareapp.model.Question

object QuestionRepo {
    private val questions = listOf(
            Question("Capital of Croatia?", "Zagreb"),
            Question("Capital of Bosnia?", "Sarajevo"),
            Question("Capital of Slovenia?", "Ljubljana")
    )

    operator fun get(index: Int) =
        if (index in questions.indices) questions[index]
        else null
}