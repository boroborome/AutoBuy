/**
 * Created by ysgao on 5/15/16.
 */

function startBuy() {
    $.ajax("/svc/autobuy/start", {
        "async": true,
        "crossDomain": true,
        method : 'post',
        data : '', //JSON.stringify(idea),
        headers: {"Content-Type" : "application/json;charset=UTF-8"},
        success: function (data, status) {
            if (status != "success") {
                alert(status);
                return;
            }
            if (data.code != 0) {
                alert(data.message);
                $("#lblStatus").text(data.message);
                return;
            }

            var ideaItem = createIdeaRecord(data.data);
            // $("#tblIdea").append(ideaItem);
            $("#lblStatus").text("Success in adding record:" + data.data.name);
            // $("#devContent").hide();
        }
    });
}

function initUI() {
    $("#btnStart").click(startBuy);
}