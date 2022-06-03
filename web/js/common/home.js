/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


var check = function () {
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/
    var email = document.getElementById('emailReset').value;

    if (emailRegex.test(email)) {
        document.getElementById('validFormMessage').style.color = 'green';
        document.getElementById('validFormMessage').innerHTML = 'Right email format';
        document.getElementById('resetButton').disabled  = false;
        
    } else {
        document.getElementById('validFormMessage').style.color = 'red';
        document.getElementById('validFormMessage').innerHTML = 'Wrong email format';
        document.getElementById('resetButton').disabled  = true;

    }


}