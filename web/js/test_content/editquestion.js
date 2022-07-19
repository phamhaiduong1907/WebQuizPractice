/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
jQuery(document).ready(function () {
    var id = 2;
    jQuery('#add_item').click(function () {
        var button = $('#answer__details__2').clone();
        id++;
        button.find('input').val('');
        button.removeAttr('id');
        button.insertBefore('.new_item_details');
        button.attr('id', 'answer__details__' + id).attr('data-id', id);
        button.find('.remove').attr('data-id', id);
        button.find('button').show();
        button.find('input:checkbox').attr('value', id + '_istrue');
        button.find('input:checkbox').removeAttr('checked');
    });
    jQuery(document).on('click', '.remove', function (e) {
        var thisId = jQuery(this).data('id');
        jQuery('div[data-id="' + thisId + '"]').remove();
        e.preventDefault();
        if (id > 2) {
            id--;
        }
    });
});

var file__type = document.getElementById("file__select");

function changeInputFile() {
    const file = file__type.value;
    if (file === "2") {
        document.getElementById("video__preview").style.display = "block";
        document.getElementById("audio__preview").style.display = "none";
        document.getElementById("image__preview").style.display = "none";
        document.getElementById("file__input").style.display = "block";
    } else if (file === "3") {
        document.getElementById("video__preview").style.display = "none";
        document.getElementById("audio__preview").style.display = "block";
        document.getElementById("image__preview").style.display = "none";
        document.getElementById("file__input").style.display = "block";
    } else if (file === "1") {
        document.getElementById("video__preview").style.display = "none";
        document.getElementById("audio__preview").style.display = "none";
        document.getElementById("image__preview").style.display = "block";
        document.getElementById("file__input").style.display = "block";
    } else {
        document.getElementById("file__input").style.display = "none";
        document.getElementById("video__preview").style.display = "none";
        document.getElementById("audio__preview").style.display = "none";
        document.getElementById("image__preview").style.display = "none";
    }
}

function changeInputFileAccept() {
    const file = file__type.value;
    if (file === "2") {
        document.getElementById("file__input").setAttribute("accept", "video/*");
        document.getElementById("file__input").disabled = false;
    } else if (file === "3") {
        document.getElementById("file__input").disabled = false;
        document.getElementById("file__input").setAttribute("accept", "audio/*");
    } else if (file === "1") {
        document.getElementById("file__input").disabled = false;
        document.getElementById("file__input").setAttribute("accept", "image/*");
    }
}

function InputFile() {
    changeInputFile();
    changeInputFileAccept();
    document.getElementById("file__input").value = '';
    document.getElementById("image__preview").setAttribute('src', '#');
    document.getElementById("video__preview").setAttribute('src', '#');
    document.getElementById("audio__preview").setAttribute('src', '#');
}

file__input.onchange = function (e) {
    const fileType = file__type.value;
    var file;
    if (fileType === "2") {
        file = document.getElementById('video__preview');
    } else if (fileType === "3") {
        file = document.getElementById('audio__preview');
    } else if (fileType === "1") {
        file = document.getElementById('image__preview');
    }
    file.src = URL.createObjectURL(this.files[0]);
    file.onend = function (e) {
        URL.revokeObjectURL(this.src);
    }
}


