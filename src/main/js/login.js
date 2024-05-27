const signInButton = document.getElementById('signInButton')

function redirectToIndex() {
  window.location.href = '/'
}

signInButton?.addEventListener('click', redirectToIndex)
const signUpButton = document.getElementById('signUpButton')
function redirectToSignUp() {
  window.location.href = '/signup'
}
signUpButton?.addEventListener('click', redirectToSignUp)
