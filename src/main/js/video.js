import {header, token} from './util/csrf.js'

const videoIdInput = document.getElementById('videoIdInput')
const deleteButtons = document.querySelectorAll('button.btn-outline-dark')


for (const deleteButton of deleteButtons) {
  deleteButton.addEventListener('click', handleDeleteDetails)
}

async function handleDeleteDetails() {
  const response = await fetch(`/api/videos/${videoIdInput.value}`, {
    method: 'DELETE',
    headers: {
      [header]: token
    }
  })
  if (response.status === 204) {
    window.location.replace('/videos')
  }
}