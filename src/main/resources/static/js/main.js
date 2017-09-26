'use strict';

function createBook() {
    var bookName = $("#book-name-add").val();
    var bookSize = $("#book-size-add").val();

    var request = {
        type: "POST",
        url: "/booksUniqueCreate",
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            name: bookName,
            size: bookSize
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

function deleteBook() {
    var bookId = $(this).closest('span').html().trim();
    var request = {
        type: "DELETE",
        url: "/books/delete/",
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            id: bookId
        })
    };
    $.ajax(request)
        .done(function () {
            alert("Book deleted");
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });


}