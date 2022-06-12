/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


let popup = document.getElementById("register__popup");
let registerComplete = document.getElementById("register__complete");

function openPopup() {
    popup.classList.add("open__popup");
}
function closePopup() {
    popup.classList.remove("open__popup");
}

function openComplete() {
    registerComplete.classList.add("register__complete__add");
}
function closeComplete() {
    registerComplete.classList.remove("register__complete__add");
}
function closeAllForm(){
    closeComplete();
    closePopup();
}


