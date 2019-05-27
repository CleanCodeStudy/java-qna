var main = {
    init : function () {
        var _this = this;
        $('#questionSave').on('click', function () {
            console.log("click.");
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#contentTextArea').val()
        };

        $.ajax({
            type: 'POST',
            url: '/questions',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            success: function(){
                console.log("success");
                // location.
            },
            error: function() {
                console.log("error");
                // location.reload();
            }
        })

        //     .done(function() {
        //     console.log("success");
        //     alert('글이 등록되었습니다.');
        //     // location.reload();
        // }).error(function(e){
        //     console.log(e);
        //     alert("error");
        // })
    }

};

main.init();