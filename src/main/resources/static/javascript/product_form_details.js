function addNextDetailSection() {
    allDivDetails = $("[id^='divDetail']")
    divDetailsCount = allDivDetails.length;

    htmlDetailSection = `
    <div class="form-inline" id="divDetail${divDetailsCount}">
        <label class="m-3">
            Name:
            <input type="text" class="form-control" name="detailNames" maxlength="64"/>
        </label>
        <label class="m-3">
            Value:
            <input type="text" class="form-control" name="detailValues" maxlength="64"/>
        </label>
    </div>
    `;

    $("#productDetails").append(htmlDetailSection);

    previousDivDetailSection = allDivDetails.last();
    previousDivDetailID = previousDivDetailSection.attr("id");

    htmlLinkRemove = `
    <a class="far fa-minus-square" style="color: #2460c2" 
       href="javascript:removeDetailSectionById('${previousDivDetailID}')"
       title="remove this item"></a>
    `;

    previousDivDetailSection.append(htmlLinkRemove);
}

function removeDetailSectionById(id) {
    $("#" + id).remove();
}