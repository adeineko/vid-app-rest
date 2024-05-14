const signInButton = document.getElementById("signInButton");
const pwShowHide = document.getElementById("checkBoxShowHide");
const signUpButton = document.getElementById("signUpButton");

function showHide() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function redirectToIndex() {
    window.location.href = "/";
}

function redirectToSignUp() {
    window.location.href = "/signup"
}

signInButton?.addEventListener("click", redirectToIndex);
pwShowHide?.addEventListener("click", showHide);
signUpButton?.addEventListener("click", redirectToSignUp);
