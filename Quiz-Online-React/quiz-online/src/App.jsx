import 'bootstrap/dist/css/bootstrap.min.css' 
import './App.css'
import GetAllQuiz from '../components/quiz/GetAllQuiz'
import AddQuestion from '../components/question/AddQuestion'
import { api } from '../utils/QuizService'
import { getAllQuestions } from '../utils/QuizService'
import { getQuestionById } from '../utils/QuizService'
import { getSubjects } from '../utils/QuizService'
import { fetchQuizForUser } from '../utils/QuizService'
import { updateQuestion } from '../utils/QuizService'
import { deleteQuestion } from '../utils/QuizService'
import { createQuestion } from '../utils/QuizService'



function App() {

  return (
    <>
       
      <AddQuestion/>
      <GetAllQuiz/>
    </>
  )
}

export default App
