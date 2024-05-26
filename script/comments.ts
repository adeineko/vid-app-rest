function deleteComment(commentId: number): void {
  const xhr = new XMLHttpRequest()

  xhr.open('DELETE', `/api/comments/${commentId}`, true)

  xhr.onload = function () {
    if (xhr.status === 204) {
      const commentRow = document.getElementById(`comment_${commentId}`)
      if (commentRow) {
        commentRow.remove()
        console.log(`Comment with ID ${commentId} removed successfully.`)
      } else {
        alert(`Error: Comment row with ID ${commentId} not found`)
      }
    }
  }

  xhr.send()
}

document.addEventListener('DOMContentLoaded', () => {
  const deleteButtons = document.querySelectorAll<HTMLButtonElement>('.deleteButton')
  deleteButtons.forEach(button => {
    button.addEventListener('click', function (event) {
      event.preventDefault()
      const row = this.closest('tr')
      if (row && row.id) {
        const rowId = row.id
        const commentId = parseInt(rowId.substring(rowId.indexOf('_') + 1), 10)
        if (!isNaN(commentId)) {
          deleteComment(commentId)
        } else {
          alert('Error: Invalid comment ID')
        }
      }
    })
  })
})
