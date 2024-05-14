import {token, header} from "../util/csrf.js";

const channelIdInput = document.getElementById("channelId");
const toggleVideosButton = document.getElementById("toggleVideosButton");
const videosTable = document.getElementById("videosTable");
const buttonWrapper = document.getElementById("dropdownButtonWrapper");
const tableBody = document.getElementById("channelInformationBody");
const deleteButtons = document.querySelectorAll('button.btn-outline-dark');


for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDeleteDetails);
}

async function handleDeleteDetails() {
    const response = await fetch(`/api/channels/${channelIdInput.value}`, {
        method: "DELETE",
        headers: {
            [header]: token
        }
    })
    if (response.status === 204) {
        window.location.replace("/channels");
    }
}

async function toggleVideosTable() {
    if (videosTable.style.display === "table") {
        hideVideosTable();
    } else {
        const response = await fetch(`/api/channels/${channelIdInput.value}/videos`,
            {
                headers: {
                    "Accept": "application/json",
                    [header]: token
                }
            });
        if (response.status === 200) {
            const videos = await response.json();
            tableBody.innerHTML = '';
            for (const video of videos) {
                tableBody.innerHTML += `
                    <tr>
                        <td>${video.id}</td>
                        <td>${video.title}</td>
                        <td>${video.views}</td>
                    </tr>
                `;
            }
            showVideosTable();
        }
    }
}

function hideVideosTable() {
    videosTable.style.display = "none";
    buttonWrapper.classList.remove("dropup");
    if (!buttonWrapper.classList.contains("dropdown")) {
        buttonWrapper.classList.add("dropdown");
    }
}

function showVideosTable() {
    videosTable.style.display = "table";
    buttonWrapper.classList.remove("dropdown");
    if (!buttonWrapper.classList.contains("dropup")) {
        buttonWrapper.classList.add("dropup");
    }
}

toggleVideosButton.addEventListener('click', toggleVideosTable);

const updateButton = document.getElementById("updateButton");
const nameTextArea = document.getElementById("nameTextArea");
const subscribersTextArea = document.getElementById("subscribersTextArea");

async function handleUpdate() {
    const response = await fetch(`/api/channels/${channelIdInput.value}`, {
        method: "PATCH",
        headers: {
            "Content-type": "application/json",
            [header]: token
        },
        body: JSON.stringify({
            name: nameTextArea.value,
            subscribers: subscribersTextArea.value
        })
    })
    if (response.status === 204) {
        updateButton.disabled = true;
    } else {
        alert('Something went wrong!');
    }
}

updateButton?.addEventListener('click', handleUpdate);
nameTextArea?.addEventListener("input", () => updateButton.disabled = false);
subscribersTextArea?.addEventListener("input", () => updateButton.disabled = false);


