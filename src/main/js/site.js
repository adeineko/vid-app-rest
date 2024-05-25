import '../scss/site.scss'
import 'bootstrap'

document.addEventListener('DOMContentLoaded', () => {
    const dropdownToggle = document.getElementById('channelsDropdown')
    const dropdownMenu = document.getElementById('channelsDropdownMenu')

    dropdownToggle.addEventListener('click', (event) => {
        event.preventDefault()
        dropdownMenu.classList.toggle('show')
    })

    document.addEventListener('click', (event) => {
        if (!dropdownToggle.contains(event.target)) {
            dropdownMenu.classList.remove('show')
        }
    })
})
