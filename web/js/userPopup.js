document.getElementById("openProfile").addEventListener("click",function(){
    document.querySelector(".popup").style.display = "flex";
    document.querySelector(".form__change-password").style.display = "none";
    document.querySelector(".form_user-profile").style.display = "block";
})
document.querySelector(".close").addEventListener("click",function(){
    document.querySelector(".popup").style.display = "none";
})

document.getElementById("openChangePassword").addEventListener("click",function(){
    document.querySelector(".popup").style.display = "flex";
    document.querySelector(".form__change-password").style.display = "block";
    document.querySelector(".form_user-profile").style.display = "none";
})