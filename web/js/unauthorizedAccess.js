/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var unauthorizedAccess = sessionStorage.getItem("unauthorizedAcess");
if(unauthorizedAccess !== null){
    window.location.reload(true);
    console.log("unauthorized "+unauthorizedAccess);
    window.addEventListener('load', () =>{
        alert("You are not allowed to access this source");
    });
    sessionStorage.clear();
}

