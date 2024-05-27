import {addVideoToHtmlTable} from './videos.js'
import * as Joi from 'joi'

const titleInput = document.getElementById('titleInput')
const viewsInput = document.getElementById('viewsInput')
const linkInput = document.getElementById('linkInput')
const genreInput = document.getElementById('genreInput')
const addButton = document.getElementById('addButton')

async function addNewVideo() {
  const response = await fetch('/api/videos', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      title: titleInput.value,
      views: viewsInput.value,
      link: linkInput.value,
      genre: genreInput.value
    })
  })
  if (response.status === 201) {
    /**
     * @type {{id: number, title: string, views: number, link: string, genre: VideoGenre}}
     */
    const video = await response.json()
    addVideoToHtmlTable(video)
  }
}

function addButtonClicked(event) {
  event.preventDefault()
  trySubmitFrom()
}

function trySubmitFrom() {
  const schema = Joi.object({
    title: Joi.string()
      .min(3)
      .max(30)
      .required(),
    views: Joi.number()
      .positive()
      .required(),
    link: Joi.string()
      .min(10)
      .max(50)
      .required(),
    genre: Joi.required()
  })
  const videoObject = {
    title: titleInput.value,
    views: viewsInput.value,
    link: linkInput.value,
    genre: genreInput.value
  }
  const validationResult = schema.validate(videoObject, {
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
    successContainer.innerHTML = 'Validation successful!'
    successContainer.style.display = 'block'

    const errorContainer = document.getElementById('alert-container')
    errorContainer.innerHTML = ''
    errorContainer.style.display = 'none'

    addNewVideo()
  }
}

addButton?.addEventListener('click', addButtonClicked)

async function loadGenres() {
  const response = await fetch('/api/videos/genres',
    {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
  const genres = await response.json()
  if (response.status === 200) {
    genreInput.innerHTML = ''
    genres.forEach(genre => {
      const option = document.createElement('option')
      option.value = genre
      option.textContent = genre
      genreInput.appendChild(option)
    })
  }
}

loadGenres()
