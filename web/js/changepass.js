/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function checkPassword() {
    var pw1 = document.getElementById("newPassword");
    var pw2 = document.getElementById("confirmNewPassword");
    pw2.title = "Must matches new password";
    pw2.pattern = pw1.value;
}

function checkOldNewPass() {
    var pwo = document.getElementById("currentPassword").value;
    var pwn = document.getElementById("newPassword").value;
    if(pwo == pwn){
        alert("The new password must be different from the old password!");
        return false;
    }
    return true;
}
