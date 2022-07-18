document.querySelector('#loginButton').addEventListener("click", () => {
    setTimeout(() => {
        var popUp = document.getElementById('popupSection');
        popUp.classList.add('active');
    }, 100);
});

document.addEventListener("dblclick", (e) => {
    var isClosest = e.target.closest('.popup__content');
    var popupSection = document.querySelector('#popupSection');

    if (!isClosest && popupSection.classList.contains("active")) {
        document.querySelector(".popup__login-form").style.display = "block";
        document.querySelector(".popup__reset-form").style.display = "none";
        document.querySelector(".popup__signup-form").style.display = "none";
        popupSection.classList.remove('active');
    }
});

document.querySelector(".popup__signup a").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
    document.querySelector(".popup__signup-form").style.display = "block";
});

document.querySelector(".popup__reset a").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
});

document.querySelector(".popup__signup-form i").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});

document.querySelector(".popup__reset-form i").addEventListener("click", function () {
    document.querySelector(".popup__login-form").style.display = "block";
    document.querySelector(".popup__signup-form").style.display = "none";
    document.querySelector(".popup__reset-form").style.display = "none";
});




