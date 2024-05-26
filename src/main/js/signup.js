const signUpButton = document.getElementById('signUpButton')
const username = document.getElementById('username')
const password = document.getElementById('password')
async function createUser() {
  const response = await fetch('/api/signup', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      username: username.value,
      password: password.value
    })
  })
  if (response.status === 201) {
    window.location.href = '/login'
    alert(response.status)
    console.log('success', response.status)
  } else {
    alert(response.status)
  }
}

signUpButton?.addEventListener('click', createUser)



