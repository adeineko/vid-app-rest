import {addVideoToHtmlTable} from './videos.js'

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
    } else {
        alert(response.status)
    }
}

addButton?.addEventListener('click', addNewVideo)
