import {token, header} from './util/csrf.js'
import * as Joi from 'joi'
import {addChannelToHtmlTable} from './channels.js'

const nameInput = document.getElementById('nameInput')
const dateInput = document.getElementById('dateInput')
const subscribersInput = document.getElementById('subscribersInput')
const addButton = document.getElementById('addButton')
const form = document.getElementById('form')

async function addNewChannel() {

  const response = await fetch('/api/channels', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      [header]: token
    },
    body: JSON.stringify({
      name: nameInput.value,
      date: dateInput.value,
      subscribers: subscribersInput.value
    })
  })
  if (response.status === 201) {
    /**
     * @type {{id: number, name: string, date: localDate, subscribers: number}}
     */
    const channel = await response.json()
    addChannelToHtmlTable(channel)
    trySubmitFrom()
  }
}

function addButtonClicked(event) {
  event.preventDefault()
  trySubmitFrom()
}

function trySubmitFrom() {
  const schema = Joi.object({
    name: Joi.string()
      .min(3)
      .max(30)
      .required(),
    date: Joi.date()
      .default(Date.now)
      .max('now')
      .required()
      .messages({
        'date.base': 'Invalid date format',
        'date.max': 'Date cannot be in the future',
        'any.required': 'Date is required'
      }),
    subscribers: Joi.number()
      .positive()
      .required()
  })
  const channelObject = {
    name: nameInput.value,
    date: dateInput.value,
    subscribers: subscribersInput.value
  }
  const validationResult = schema.validate(channelObject, {
    abortEarly: false
  })
  if (validationResult.error) {
    const successContainer = document.getElementById('successContainer')
    successContainer.style.display = 'none'
    const errorMessages = validationResult.error.details.map(detail => detail.message)
    const errorContainer = document.getElementById('alert-container')
    errorContainer.innerHTML = errorMessages.join('<br>')
    errorContainer.style.display = 'block'

  } else {
    const successContainer = document.getElementById('successContainer')
    successContainer.innerHTML = 'Added successfully!'
    successContainer.style.display = 'block'

    const errorContainer = document.getElementById('alert-container')
    errorContainer.innerHTML = ''
    errorContainer.style.display = 'none'

    addNewChannel()
    form.reset()
  }

}

addButton?.addEventListener('click', addButtonClicked)