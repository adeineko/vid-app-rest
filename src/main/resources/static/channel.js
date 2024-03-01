const channelIdInput = document.getElementById("channelId");
const toggleVideosButton = document.getElementById("toggleVideosButton");
const videosTable = document.getElementById("videosTable");
const buttonWrapper = document.getElementById("dropdownButtonWrapper");
const tableBody = document.getElementById("channelInformationBody");

async function toggleVideosTable() {
    if (videosTable.style.display === "table") {
        hideVideosTable();
    } else {
        const response = await fetch(`/api/channels/${channelIdInput.value}/videos`,
            {headers: {"Accept": "application/json"}});
        if (response.status === 200) {
            const videos = await response.json();
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

