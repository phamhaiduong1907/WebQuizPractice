document.getElementById("openProfile").addEventListener("click",function(){
    var popUp = document.getElementById('popupSection');
    popUp.classList.add('active');
    document.querySelector('.form__user-profile').style.display = 'block';
    document.querySelector('.form__change-password').style.display = 'none';
});
document.querySelector(".close").addEventListener("click",function(){
    var popUp = document.getElementById('popupSection');
    popUp.classList.remove('active');
});

document.getElementById("openChangePassword").addEventListener("click",function(){
    var popUp = document.getElementById('popupSection');
    popUp.classList.add('active');
    document.querySelector('.form__user-profile').style.display = 'none';
    document.querySelector('.form__change-password').style.display = 'block';
});