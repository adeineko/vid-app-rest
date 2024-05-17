import {token, header} from './util/csrf.js'
import * as Joi from 'joi'
import {addChannelToHtmlTable} from './channels.js'

const nameInput = document.getElementById('nameInput')
const dateInput = document.getElementById('dateInput')
const subscribersInput = document.getElementById('subscribersInput')
const addButton = document.getElementById('addButton')

async function addNewChannel() {

    const response = await fetch('/api/channels', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            [header]: token
        },
        body: JSON.stringify({
            name: nameInput.value,
            date: dateInput.value,
            subscribers: subscribersInput.value
        })
    })
    if (response.status === 201) {
        /**
         * @type {{id: number, name: string, date: localDate, subscribers: number}}
         */
        const channel = await response.json()
        addChannelToHtmlTable(channel)
        trySubmitFrom()
    } else {
        alert('Something went wrong!') //TODO: Change
    }
}

function addButtonClicked(event) {
    event.preventDefault()
    trySubmitFrom()
}

/*Check first than add the channel*/
function trySubmitFrom() {
    const schema = Joi.object({
        name: Joi.string()
            .min(3)
            .max(30)
            .required(),
        date: Joi.date()
            .default(Date.now)
            .required(),
        subscribers: Joi.number()
            .positive()
            .required()
    })
    const channelObject = {
        name: nameInput.value,
        date: dateInput.value,
        subscribers: subscribersInput.value
    }
    const validationResult = schema.validate(channelObject, {
        abortEarly: false
    })
    if (validationResult.error) {
        for (const errorDetail of validationResult.error.details) {
            console.log('DETAIL:' + errorDetail.message)
        }
    }
    addNewChannel()
}

addButton?.addEventListener('click', addButtonClicked)