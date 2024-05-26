import '../scss/site.scss'
import 'bootstrap'

const alertContainer = document.getElementById('alert-container')

export function showAlert(message, type) {
    alertContainer.innerHTML = ''
    const alertDiv = document.createElement('div')
    alertDiv.className = `alert ${type}`
    alertDiv.textContent = message
    alertContainer.appendChild(alertDiv)
    alertDiv.style.display = 'block'

    setTimeout(() => {
        alertDiv.style.display = 'none'
    }, 3000)
}

