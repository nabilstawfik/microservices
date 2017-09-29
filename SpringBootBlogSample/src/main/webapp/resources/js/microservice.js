/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function submitForm() {
    try {
        var url = "/microservice/instanceService/createInstance/" + $("#uuid").val();
        $.ajax({
            type: "POST",
            url: url,
            contentType: "text/plain",
            headers: {"apiKey": $("#apiKey").val()},
            success: function (data)
            {
                alert("Instance Created successfully.");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("Failed to create the instance, Status: " + textStatus);
            }
        });
    } catch (e) {
        console.log("Error on instance creation: " + e);
    }
    return false;
}
