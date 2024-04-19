import {header, token} from "./util/csrf.js";

console.log("channels.js")
const deleteButtons = document.querySelectorAll('button.btn-outline-danger');

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDelete);
}

async function handleDelete(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const channelId = parseInt(rowId.substring(rowId.indexOf('_') + 1));
    console.log(channelId)
    console.log(token)
    const response = await fetch(`/api/channels/${channelId}`, {
        method: "DELETE",
        headers: {
            [header]: token
        }
    })
    if (response.status === 204) {
        const row = document.getElementById(`channel_${channelId}`);
        row.parentNode.removeChild(row);
    }

}

const channelTableBody = document.getElementById("channelTableBody");
const addChannelButton = document.getElementById("addChannelButton");

/**
 * @param {{id: number, name: string, date: localDate, subscribers: number}} channel
 */
export function addChannelToHtmlTable(channel) {
    const tableRow = document.createElement("tr");
    tableRow.id = `channel_${channel.id}`;
    tableRow.innerHTML = `
        <tr th:id="'channel_' + ${channel.id}" th:each="channel:${channels}">
                <td th:text="${channel.id}"></td>
                <td>
                    <a th:text="${channel.name}" th:href="@{/channels/{id}(id=${channel.id})}" id="nameInput"></a>
                </td>
                <td th:text="${channel.date}" id="dateInput"></td>
                <td th:text="${channel.subscribers}" id="subscribersInput"></td>
                <td>
                    <button type="button" class="btn btn-outline-danger">Delete</button>
                </td>
            </tr>
    `
    channelTableBody.appendChild(tableRow);

    const newDeleteButton = tableRow.querySelector('button');
    newDeleteButton.addEventListener("click", handleDelete)
}

function redirectToAddChannelPage() {
    window.location.href = "/channels/add";
}

addChannelButton.addEventListener("click", redirectToAddChannelPage);