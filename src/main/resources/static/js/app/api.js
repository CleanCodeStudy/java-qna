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
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/questions',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
        }).fail(function (error) {
            alert(error);
        });
    }

};

main.init();