var index = {
    init :function () {
        var _this = this;
        $('#btn-save').on('click',function () {
            _this.save();
        });

    },
    save:function () {
        var data={
            title:$('#title').val(),
            content:$('#content').val(),
            author:$('#author').val()
        };

        $.ajax({
            type:'POST',
            url:'/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function () {
            alert('등록 완료');
            window.location.href='/';
        }).fail(function (error) {
            alert(error);
        })
    }
}
index.init();