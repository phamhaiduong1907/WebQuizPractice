let registerComplete = document.getElementById("register__complete");

function openPopup(courseID) {
    var popup = document.getElementById(courseID);
    popup.classList.add("open__popup");
}
function closePopup(courseID) {
    var popup = document.getElementById(courseID);
    popup.classList.remove("open__popup");
}

function openComplete() {
    registerComplete.classList.add("register__complete__add");
}
function closeComplete() {
    registerComplete.classList.remove("register__complete__add");
}
function closeAllForm(courseID) {
    closeComplete();
    closePopup(courseID);
}