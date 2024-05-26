"use strict";
function deleteComment(commentId) {
    var xhr = new XMLHttpRequest();
    // Define the request parameters
    xhr.open('DELETE', "/api/comments/".concat(commentId), true);
    // Handle the response from the server
    xhr.onload = function () {
        if (xhr.status === 200) {
            // Comment deleted successfully
            // Remove the corresponding row from the table
            var commentRow = document.getElementById("comment_".concat(commentId));
            if (commentRow) {
                commentRow.remove();
            }
            else {
                console.error('Comment row not found');
            }
        }
        else {
            console.error('Failed to delete comment. Server returned status: ' + xhr.status);
        }
    };
    // Send the request
    xhr.send();
}
var deleteButton = document.getElementById('deleteButton');
if (deleteButton) {
    deleteButton.addEventListener('click', function () {
        var commentId = parseInt(deleteButton.dataset.commentId || '');
        if (!isNaN(commentId)) {
            deleteComment(commentId);
        }
        else {
            console.error('Invalid comment ID');
        }
    });
}
