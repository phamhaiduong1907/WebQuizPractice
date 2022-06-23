document.querySelector('#loginButton').addEventListener("click",function(){
    var popUp = document.getElementById('popupSection');
    popUp.classList.add('active');
});

document.querySelector('#closeButton').addEventListener("click",function(){
    var popUp = document.getElementById('popupSection');
    popUp.classList.remove('active');
});

document.querySelector(".popup__signup a").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
    document.querySelector(".popup__signup-form").style.display = "block";
});

document.querySelector(".popup__reset a").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
});

document.querySelector(".popup__signup-form i").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});

document.querySelector(".popup__reset-form i").addEventListener("click",function(){
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});




