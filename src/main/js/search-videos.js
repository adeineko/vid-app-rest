import '../scss/search.scss'
import anime from 'animejs'

const searchResultsSection = document.getElementById('searchResultsSection')
const searchTermInput = document.getElementById('searchTerm')
const tableBody = document.getElementsByTagName('tbody')[0]
const searchForm = document.getElementById('searchForm')

async function searchVideos() {
  const response = await fetch(`/api/videos?search=${searchTermInput.value}`,
    {headers: {'Accept': 'application/json'}})
  if (response.status === 200) {
    const videos = await response.json()
    tableBody.innerHTML = ''
    for (const video of videos) {
      tableBody.innerHTML += `
                <tr>
                    <td>${video.title}</td>
                    <td><a href="/video/${video.id}">Details</a></td>
                </tr> 
            `
    }
    anime({
      targets: searchResultsSection,
      opacity: [0, 1],
      duration: 500,
      easing: 'easeInOutQuad',
      begin: function () {
        searchResultsSection.style.display = 'block'
      }
    })
  } else {
    searchResultsSection.style.display = 'none'
  }
}

function searchTextEntered() {
  if (searchTermInput.value.length > 2) {
    searchVideos()
  } else {
    searchResultsSection.style.display = 'none'
  }
}

searchTermInput.addEventListener('input', searchTextEntered)

searchForm.addEventListener('submit', e => e.preventDefault())

