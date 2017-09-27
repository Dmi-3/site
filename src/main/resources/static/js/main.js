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
    var bookId = $("#book-id-view").text();
    var bookName = $("#book-name-view").text();
    var bookSize = $("#book-size-view").text();

    var request = {
        type: "DELETE",
        url: "/bookDelete",
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            id: bookId,
            name: bookName,
            size: bookSize
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

function updateBook() {
    var bookId = $("#book-id-update").text();
    var bookName = $("#book-name-update").val();
    var bookSize = $("#book-size-update").val();

    var request = {
        type: "POST",
        url: "/bookUniqueUpdate",
        headers: {
            'Accept': 'application/json;charset=UTF-8',
            'Content-Type': 'application/json;charset=UTF-8'
        },
        data: JSON.stringify({
            id: bookId,
            name: bookName,
            size: bookSize
        })
    };
    $.ajax(request)
        .done(function () {
            alert("Book updated");
        })
        .fail(function (response) {
            alert("Error: " + response.responseJSON.message);
        });
}