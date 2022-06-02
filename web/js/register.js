
function matchPassword() {
    var pw1 = document.getElementById("passwordReg");
    var pw2 = document.getElementById("confirmPassword");
    pw2.title = "Must matches password";
    pw2.pattern = pw1.value;
}

