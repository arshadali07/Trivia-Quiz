package com.project.triviaquiz.presentation.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.triviaquiz.domain.model.TriviaQuizDomain
import com.project.triviaquiz.presentation.model.TriviaQuizUi
import com.project.triviaquiz.presentation.model.toUi

object QuizSectionTestData {

    fun getTestData(): List<TriviaQuizUi> {
        val json = """
            [
              {
                "category": "society_and_culture",
                "id": "622a1c357cc59eab6f94fcdd",
                "correctAnswer": "Austronesian",
                "incorrectAnswers": [
                  "Austroasiatic",
                  "Niger–Congo",
                  "Japonic"
                ],
                "question": {
                  "text": "The language 'Javanese' belongs to which language family?"
                },
                "tags": [
                  "language",
                  "society_and_culture"
                ],
                "type": "text_choice",
                "difficulty": "hard",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "science",
                "id": "622a1c377cc59eab6f9504e7",
                "correctAnswer": "nerves",
                "incorrectAnswers": [
                  "embryos",
                  "the study and psychology of organisms with regard to their functions and structures",
                  "mollusks"
                ],
                "question": {
                  "text": "What is Neurology the study of?"
                },
                "tags": [
                  "science"
                ],
                "type": "text_choice",
                "difficulty": "medium",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "arts_and_literature",
                "id": "622a1c3c7cc59eab6f951abe",
                "correctAnswer": "Mary Shelley",
                "incorrectAnswers": [
                  "Bram Stoker",
                  "Edgar Allen Poe",
                  "Jane Austen"
                ],
                "question": {
                  "text": "Who was the author of Frankenstein?"
                },
                "tags": [
                  "arts_and_literature"
                ],
                "type": "text_choice",
                "difficulty": "medium",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "society_and_culture",
                "id": "6488889c5dba6c873ef65070",
                "correctAnswer": "New Zealand",
                "incorrectAnswers": [
                  "Japan",
                  "Canada",
                  "Brazil"
                ],
                "question": {
                  "text": "Which country's indigenous population is known as the Māori?"
                },
                "tags": [
                  "countries",
                  "society_and_culture"
                ],
                "type": "text_choice",
                "difficulty": "medium",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "film_and_tv",
                "id": "622a1c347cc59eab6f94fb06",
                "correctAnswer": "Helen Mirren",
                "incorrectAnswers": [
                  "Penélope Cruz",
                  "Judi Dench",
                  "Meryl Streep"
                ],
                "question": {
                  "text": "Who won the 2006 Academy Award for Best Leading Actress for playing the role of The Queen in The Queen?"
                },
                "tags": [
                  "acting",
                  "academy_awards",
                  "film",
                  "film_and_tv"
                ],
                "type": "text_choice",
                "difficulty": "hard",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "society_and_culture",
                "id": "622a1c3a7cc59eab6f9511cd",
                "correctAnswer": "Achilles",
                "incorrectAnswers": [
                  "Narcissus",
                  "Hercules",
                  "Jason"
                ],
                "question": {
                  "text": "In Greek mythology, who was the son of Peleus and Thetis?"
                },
                "tags": [
                  "society_and_culture",
                  "ancient_greece",
                  "mythology"
                ],
                "type": "text_choice",
                "difficulty": "hard",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "music",
                "id": "645cb17f7d263fd5097043da",
                "correctAnswer": "Basso",
                "incorrectAnswers": [
                  "Alto",
                  "Tenor",
                  "Baritone"
                ],
                "question": {
                  "text": "Which Italian term for the deepest range of male voice often precedes \"profundo\" or \"profondo\"?"
                },
                "tags": [
                  "opera",
                  "classical_music",
                  "singing",
                  "music"
                ],
                "type": "text_choice",
                "difficulty": "medium",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "arts_and_literature",
                "id": "63afff832d1dcedfbb654557",
                "correctAnswer": "Photography",
                "incorrectAnswers": [
                  "Painting",
                  "Sculpture",
                  "Architecture"
                ],
                "question": {
                  "text": "Which of the following is NOT a type of art commonly associated with the Renaissance?"
                },
                "tags": [
                  "history",
                  "renaissance",
                  "arts_and_literature"
                ],
                "type": "text_choice",
                "difficulty": "easy",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "food_and_drink",
                "id": "625006ba0d86c8f685d80ef8",
                "correctAnswer": "Gomiti",
                "incorrectAnswers": [
                  "Rotelle",
                  "Bigoli",
                  "Campanelle"
                ],
                "question": {
                  "text": "Which pasta's name means 'Elbows'?"
                },
                "tags": [
                  "food",
                  "words",
                  "language",
                  "food_and_drink"
                ],
                "type": "text_choice",
                "difficulty": "hard",
                "regions": [ ],
                "isNiche": false
              },
              {
                "category": "science",
                "id": "624333b4cfaae40c129613fb",
                "correctAnswer": "A Jack",
                "incorrectAnswers": [
                  "A Drake",
                  "A Dog",
                  "A Hob"
                ],
                "question": {
                  "text": "What is the word for a male donkey?"
                },
                "tags": [
                  "science"
                ],
                "type": "text_choice",
                "difficulty": "medium",
                "regions": [ ],
                "isNiche": false
              }
            ]
        """.trimIndent()
        val gson = Gson()
        val response = gson.fromJson<List<TriviaQuizDomain>>(json, object : TypeToken<List<TriviaQuizDomain>>() {}.type)
        return response.map { it.toUi() }
    }
}