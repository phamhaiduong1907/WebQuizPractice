document.getElementById("loginButton").addEventListener("click", function(){
    document.querySelector(".popup").style.display = "flex";
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";})

document.querySelector(".close").addEventListener("click",function(){
    document.querySelector(".popup").style.display = "none";
})

document.querySelector(".popup__signup a").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
    document.querySelector(".popup__signup-form").style.display = "block";
})

document.querySelector(".popup__reset a").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
})

document.querySelector(".popup__signup-form i").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
})

document.querySelector(".popup__reset-form i").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
})




