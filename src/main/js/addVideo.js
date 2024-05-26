import {addVideoToHtmlTable} from './videos.js'
import {showAlert} from './site.js'

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
        showAlert('Video added successfully', 'success')
        addVideoToHtmlTable(video)
    } else {
        showAlert('Failed to add video', 'error')
    }
}

addButton?.addEventListener('click', addNewVideo)

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
