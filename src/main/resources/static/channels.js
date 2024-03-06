const deleteButtons = document.querySelectorAll('button.btn-outline-danger');

for (const deleteButton of deleteButtons) {
    deleteButton.addEventListener("click", handleDelete);
}

async function handleDelete(event) {
    const rowId = event.target.parentNode.parentNode.id;
    const channelId = parseInt(rowId.substring(rowId.indexOf('_') + 1));
    console.log(channelId)
    const response = await fetch(`/api/channels/${channelId}`, {
        method: "DELETE"
    })
    if (response.status === 204) {
        const row = document.getElementById(`channel_${channelId}`);
        row.parentNode.removeChild(row);
    }
}