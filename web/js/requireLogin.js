/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var requiredLogin = sessionStorage.getItem("requiredlogin");
var unauthorizedAccess = sessionStorage.getItem("unauthorizedAcess");
var popup = document.getElementById("popupSection");
var popup_login = document.getElementById("popupLoginForm");
if(requiredLogin !== null){
    sessionStorage.removeItem("requiredlogin");
    console.log("required login is "+requiredLogin);
    popup.style.display = "flex";
    popup_login.style.display = "block";
}



