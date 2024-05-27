import * as Joi from 'joi'

const signUpButton = document.getElementById('signUpButton')
const signUpForm = document.getElementById('signUpForm')
const successMessage = document.getElementById('successContainer')
const failedContainer = document.getElementById('failedContainer')

const username = document.getElementById('username')
const password = document.getElementById('password')

async function createUser(event) {
  event.preventDefault()


  const response = await fetch('/api/signup', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      'username': username.value,
      'password': password.value
    })
  })

  if (response.status === 201) {
    trySubmitForm()
  }
}

function trySubmitForm() {
  const schema = Joi.object({
    username: Joi.string()
      .min(3)
      .max(30)
      .required(),
    password: Joi.string()
      .min(6)
      .max(30)
      .required()
  })

  const userInput = {
    'username': username.value,
    'password': password.value
  }

  const validationResult = schema.validate(userInput, {abortEarly: false})

  const errorContainer = document.getElementById('alert-container')
  const signUpForm = document.getElementById('signUpForm')
  if (validationResult.error) {
    const errorMessages = validationResult.error.details.map(detail => detail.message)
    errorContainer.innerHTML = errorMessages.join('<br>')
    errorContainer.style.display = 'block'
    successMessage.style.display = 'none'
  } else {
    errorContainer.style.display = 'none'
    signUpForm.style.display = 'none'
    successMessage.innerHTML = 'Registered successfully!'
    successMessage.style.display = 'block'
    signUpForm.style.display = 'none'
    createUser()
  }
}

signUpButton?.addEventListener('click', createUser)




