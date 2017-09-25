'use strict';

function createBook() {
    var heroName = $("#book-name").val();

    var request = {
        type: "POST",
        url: "/booksUniqueCreate",
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            name: bookName
        })
    };
    $.ajax(request)
        .done(function () {
            alert("Book created");
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}