import {token, header} from "../util/csrf.js";

const nameInput = document.getElementById("nameInput");
const dateInput = document.getElementById("dateInput");
const subscribersInput = document.getElementById("subscribersInput");
const addButton = document.getElementById("addButton");

async function addNewChannel() {
    console.log("fetching")
    const response = await fetch('/api/channels', {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json",
            [header]: token
        },
        body: JSON.stringify({
            name: nameInput.value,
            date: dateInput.value,
            subscribers: subscribersInput.value
        })
    });
    if (response.status === 201) {
        /**
         * @type {{id: number, name: string, date: localDate, subscribers: number}}
         */
        const channel = await response.json();
        addChannelToHtmlTable(channel);
    } else {
        alert("Something went wrong!"); //TODO: Change
    }
}

addButton.addEventListener("click", addNewChannel);