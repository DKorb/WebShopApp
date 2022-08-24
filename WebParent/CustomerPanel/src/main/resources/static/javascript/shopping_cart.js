$(document).ready(function() {

    $(".linkMinus").on("click", function(evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    });

    $(".linkPlus").on("click", function(evt) {
        evt.preventDefault();
        increaseQuantity($(this));
    });

    $(".linkRemove").on("click", function(evt) {
        evt.preventDefault();
        removeProduct($(this));
    });

});

function decreaseQuantity(link) {
    productId = link.attr("pid");
    quantityInput = $("#quantity" + productId);
    newQuantity = parseInt(quantityInput.val()) - 1;

    if (newQuantity > 0) {
        quantityInput.val(newQuantity);
        updateQuantity(productId, newQuantity)
    }
}

function increaseQuantity(link) {
    productId = link.attr("pid");
    quantityInput = $("#quantity" + productId);
    newQuantity = parseInt(quantityInput.val()) + 1;

    if (newQuantity <= 10) {
        quantityInput.val(newQuantity);
        updateQuantity(productId, newQuantity);
    }
}

function updateQuantity(productId, quantity) {
    url = contextPath + "cart/update/" + productId + "/" + quantity;

    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
    }).done(function (response) {
        updateSubTotalPrice(response, productId)
        updateTotalPrice();
    }).fail(function () {
        showModal("Shopping cart", "Error while updating product to shopping cart.")
    });
}

function updateSubTotalPrice(updatedSubTotal, productId) {
    $("#subTotalPrice" + productId).text(updatedSubTotal);
}


function updateTotalPrice() {
    total = 0.0;
    shipping = 0.0;
    productQuantity = 0;

    $("#shippingPrice").each(function (index, element) {
        shipping = parseFloat(element.innerHTML);
    })


    $(".subTotalPrice").each(function (index, element) {
        total += parseFloat(element.innerHTML);
        productQuantity++;
    });

    if (productQuantity < 1) {
        showEmptyShoppingCart();
    } else {
        total += shipping;
        $("#totalPrice").text(total);
    }
}

function showEmptyShoppingCart() {
    $("#sectionTotal").hide();
    $("#sectionEmptyCartMessage").removeClass("d-none");
}

function removeProduct(link) {
    url = link.attr("href");

    $.ajax({
        type: "DELETE",
        url: url,
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
    }).done(function(response) {
        rowNumber = link.attr("rowNumber");
        removeProductHTML(rowNumber);
        updateTotalPrice();
        showModal("Shopping cart", response)

    }).fail(function(response) {
        showModal("Shopping cart", response)
    });
}

function removeProductHTML(rowNumber) {
    $("#row" + rowNumber).remove();
}