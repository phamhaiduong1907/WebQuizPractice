/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var check = function () {
    var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}(?=.*[@$!%*?&])/
    var password = document.getElementById('password').value;
    var confirm_password = document.getElementById('confirmPassword').value;
    if (password == confirm_password) {
        document.getElementById('matchingMessage').style.color = 'green';
        document.getElementById('matchingMessage').innerHTML = 'matching';
        document.getElementById('resetButton').disabled = false;

    } else {
        document.getElementById('matchingMessage').style.color = 'red';
        document.getElementById('matchingMessage').innerHTML = 'not matching ';
        document.getElementById('resetButton').disabled = true;

    }
    if (passw.test(password)) {
        document.getElementById('failed__message').innerHTML = 'Right format';
        document.getElementById('resetButton').disabled = false;
        document.getElementById('failed__message').style.color = 'green';


    } else {
        document.getElementById('failed__message').innerHTML = 'The password must have at least 8 characters in which contain at least an capital, a special character and a number';
        document.getElementById('resetButton').disabled = true;
        document.getElementById('validFormMessage').style.color = 'red';



    }


}