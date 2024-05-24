const videoTableBody = document.getElementById('videoTableBody')
const addChannelButton = document.getElementById('addChannelButton')
export function addVideoToHtmlTable(video) {
    const tableRow = document.createElement('tr')
    tableRow.id = `video_${video.id}`
    tableRow.innerHTML = ` 
                <td th:text="${video.id}"></td>
                <td>
                    <a th:text="${video.name}" th:href="@{/videos/{id}(id=${video.id})}" id="nameInput"></a>
                </td>
                <td th:text="${video.date}" id="dateInput"></td>
                <td th:text="${video.subscribers}" id="subscribersInput"></td>
                <td>
                    <button type="button" class="btn btn-outline-danger">Delete</button>
                </td>
    `
    videoTableBody.appendChild(tableRow)

    const newDeleteButton = tableRow.querySelector('button')
    // newDeleteButton.addEventListener('click', handleDelete)
}