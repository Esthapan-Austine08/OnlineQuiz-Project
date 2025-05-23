import React, { useEffect, useState } from 'react'
import { deleteQuestion, getAllQuestions } from '../../utils/QuizService'

const GetAllQuiz=()=> {
   const[question,setQuestion]=useState([{id:"",question:"",correctAnswers:"",choices:[]}])
   const[isLoading,setIsLoading] = useState(true)
   const[isQuestionDeleted,setIsQuestionDeleted] = useState(false)
   const[deleteSuccessMessage,setDeleteSuccessMessage] = useState("")

   useEffect(() =>{
     fetchAllQuestion()
   },[])

   const fetchAllQuestion = async() =>{
    try {
        const data = await getAllQuestions()
        setQuestion(data)
        setIsLoading(false)
    } catch (error) {
        console.error(error)
    }
   }

   const handleDelete = async(id) =>{
    try {
        await deleteQuestion(id)
        setQuestion(question.filter((question)=>question.id !== id))
        setIsQuestionDeleted(true)
        setDeleteSuccessMessage("Question deleted Successfully")
    } catch (error) {
        console.error(error)
    }
    setTimeout(()=>{
        setDeleteSuccessMessage("")
    },4000)
   }

   if(isLoading){
      return <div>Loading......</div>
   }

  return (
     <section className='container'>
        <div className='row mt-5'>
           <div className='col-md-6 mb-2 md-mb-0' style={{color:"GrayText"}}>
              <h4>All Quiz Questions</h4>
           </div>
           <div className='col-md-4 d-flex justify-content-end'>
               {/* Tdo : add a link to navigate to add new questiom form */}
           </div>
        </div>
        <hr/>
        {isQuestionDeleted && <div className='alert alert-success'>{deleteSuccessMessage}</div>}

        {question.map((question,index)=>(
          <div>
            <h4 style={{color:"GrayText"}}>{`${index+1}.${question.question}`}</h4>
              <ul>
                {question.choices.map((choice,index)=>(
                  <li key={index}>{choice}</li>
                ))}
              </ul>
              <p className='text-success'>Correct Answer: {question.correctAnswers}</p>
            <div className='btn-group mb-4'>
              {/* Tdo : add a link to navigate to update questiom form */}
                <button className='btn btn-outline-danger btn-sm'
                         onClick={()=>(handleDelete(question.id))}>
                    Delete Question
                </button>
            </div>
          </div>
        ))}
     </section>
  )
}

export default GetAllQuiz
