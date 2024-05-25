import anime from 'animejs'
import {token, header} from './util/csrf.js'

const videoTableBody = document.getElementById('videoTableBody')
const deleteButtons = document.querySelectorAll('button.btn-outline-danger')

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener('click', handleDelete)
}

async function handleDelete(event) {
    const rowId = event.target.parentNode.parentNode.id
    const videoId = parseInt(rowId.substring(rowId.indexOf('_') + 1))
    const response = await fetch(`/api/videos/${videoId}`, {
        method: 'DELETE',
        headers: {
            [header]: token
        }
    })
    if (response.status === 204) {
        const row = document.getElementById(`video_${videoId}`)
        anime({
            targets: row,
            opacity: 0,
            easing: 'linear',
            duration: 600,
            direction: 'normal',
            complete: () => row.parentNode.removeChild(row)
        })
    }

}

export function addVideoToHtmlTable(video) {
    const tableRow = document.createElement('tr')
    tableRow.id = `video_${video.id}`
    tableRow.innerHTML = ` 
                <td th:text="${video.id}"></td>
                <td>
                    <a th:text="${video.title}" th:href="@{/videos/{id}(id=${video.id})}" id="titleInput"></a>
                </td>
                <td th:text="${video.views}" id="viewsInput"></td>
                <td th:text="${video.link}" id="linkInput"></td>
                <td th:text="${video.genre}" id="genreInput"></td>
                <td>
                    <button type="button" class="btn btn-outline-danger">Delete</button>
                </td>
    `
    videoTableBody.appendChild(tableRow)

    const newDeleteButton = tableRow.querySelector('button')
    newDeleteButton.addEventListener('click', handleDelete)
}