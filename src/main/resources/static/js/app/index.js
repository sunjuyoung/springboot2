var index = {
    init :function () {
        var _this = this;
        //저장 버튼
        $('#btn-save').on('click',function () {
            _this.save();
        });
        //수정 버튼
        $('#btn-update').on('click',function () {
            _this.update();
        });
        //삭제 버튼
        $('#btn-delete').on('click',function () {
            _this.delete();
        })

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
    },
    update:function () {
        var data={
            title:$('#title').val(),
            content:$('#content').val()
        }
        var id = $('#id').val();
        $.ajax({
            type: 'PUT',
            url:'/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function () {
            alert('수정완료');
            window.location.href='/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
    delete:function () {
        var id = $('#id').val();
        $.ajax({
            Type:'DELETE',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function () {
            alert(id+'번 삭제 완료');
            window.location.href='/';

        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}
index.init();