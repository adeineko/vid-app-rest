"use strict";
function deleteComment(commentId) {
    var xhr = new XMLHttpRequest();
    xhr.open('DELETE', "/api/comments/".concat(commentId), true);
    xhr.onload = function () {
        if (xhr.status === 204) {
            var commentRow = document.getElementById("comment_".concat(commentId));
            if (commentRow) {
                commentRow.remove();
                console.log("Comment with ID ".concat(commentId, " removed successfully."));
            }
            else {
                alert("Error: Comment row with ID ".concat(commentId, " not found"));
            }
        }
    };
    xhr.send();
}
document.addEventListener('DOMContentLoaded', function () {
    var deleteButtons = document.querySelectorAll('.deleteButton');
    deleteButtons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            var row = this.closest('tr');
            if (row && row.id) {
                var rowId = row.id;
                var commentId = parseInt(rowId.substring(rowId.indexOf('_') + 1), 10);
                if (!isNaN(commentId)) {
                    deleteComment(commentId);
                }
                else {
                    alert('Error: Invalid comment ID');
                }
            }
        });
    });
});
