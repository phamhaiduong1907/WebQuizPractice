document.addEventListener("click", (e) => {
    var isClosest = e.target.closest('.popup__content');
    var popupSection = document.querySelector('#popupSection');

    if (!isClosest && popupSection.classList.contains("active")) {
        document.querySelector(".popup__login-form").style.display = "block";
        document.querySelector(".popup__reset-form").style.display = "none";
        document.querySelector(".popup__signup-form").style.display = "none";
        popupSection.classList.remove('active');
    }
});

document.getElementById("openProfile").addEventListener("click", function () {
    setTimeout(() => {
        var popUp = document.getElementById('popupSection');
        popUp.classList.add('active');
        document.querySelector('.form__user-profile').style.display = 'block';
        document.querySelector('.form__change-password').style.display = 'none';
    }, 100);
});

document.getElementById("openChangePassword").addEventListener("click", function () {
    setTimeout(() => {
        var popUp = document.getElementById('popupSection');
        popUp.classList.add('active');
        document.querySelector('.form__user-profile').style.display = 'none';
        document.querySelector('.form__change-password').style.display = 'block';
    }, 100);
});