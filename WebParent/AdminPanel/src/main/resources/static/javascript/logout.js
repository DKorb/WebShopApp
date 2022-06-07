$(document).ready(function () {
    $("#logoutItem").on("click", function (e) {
        e.preventDefault();
        document.logoutAction.submit();
    })
});