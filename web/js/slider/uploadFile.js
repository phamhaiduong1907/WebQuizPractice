function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        var blah = document.getElementById('previewImage');

        reader.onload = function (e) {
            blah.src = e.target.result;
            blah.style.display = 'block';
            blah.style.width = '80%';
            blah.style.height = '150px';
        };

        reader.readAsDataURL(input.files[0]);
    }
}

function checkValue(id, targetId) {
    var input = document.getElementById(id).value;
    var image = document.getElementById(targetId);
    if (input === "") {
        image.src = "";
        image.style.width = '0';
        image.style.height = '0';
    }
}