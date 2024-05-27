import '../scss/site.scss'
import 'bootstrap'

const pwShowHide = document.getElementById('checkBoxShowHide')

function showHide() {
  var x = document.getElementById('password')
  if (x.type === 'password') {
    x.type = 'text'
  } else {
    x.type = 'password'
  }
}

pwShowHide?.addEventListener('click', showHide)


