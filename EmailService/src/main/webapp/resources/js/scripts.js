/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function submitForm() {
    try {
        var url = "/microservices/emailer/send";
        var emailMessage = {to: $("#to").val(), subject: $("#subject").val(), body: $("#body").val()};
        $.ajax({
            type: "POST",
            url: url,
            contentType: "application/json",
            data: JSON.stringify(emailMessage),
            headers: {"apiKey": $("#apiKey").val()},
            success: function (data)
            {
                if (data.success == true) {
                    alert("Email sent successfully.");
                } else {
                    alert("Failed to send the email.");
                }
            }
        });
    } catch (e) {
        console.log("Error sending the mail: "+e);
    }
    return false;
}
