function bindSave() {
    let postData = {};

    let username = $('#add-uUsername').val();
    let password = $('#add-uPassword').val();
    if(username === "" || password === ""){
        alert("用户名或者密码不能为空，插入失败！");
        return ;
    }

    // 查找radio
    let uRoleValue = $('input:radio[name="uRole"]:checked').val();
    postData['uRole'] = uRoleValue;
    <!--#找input标签-->
    $('#addUser').find('input').each(function () {
        if ($(this).attr('type') != 'radio') {
            let n = $(this).attr('name');
            let v = $(this).val();
            postData[n] = v;
        }
    });
    console.log(postData)
    $.ajax({
        url: "/insertUserInfo",
        type: 'POST',
        data: postData,
        success: function (arg) {
            console.log(arg);
            alert(arg)
            window.location.reload();
        }
    })
    $('#addUser').modal('hide');
}

/*绑定编辑事件*/
function bindEdit() {
    let postData = {};
    $('#edUser').find('input').each(function () {
        <!--#找input标签-->
        let n = $(this).attr('name');
        let v = $(this).val();
        postData[n] = v;
    });

    console.log(postData)
    $.ajax({
        url: "/edUserInfo",
        type: 'POST',
        data: postData,
        success: function (arg) {
            console.log(arg);
            alert(arg)
            window.location.reload();
        }
    })
    $('#addUser').modal('hide');
}

/*被绑定的按钮，先执行onclick，获得表格当前行的值并设置为模态框的值*/
function getData() {
    $("#userInfoTable tbody").on("click", "tr", function () {
        let data = new Array();
        let td = $(this).find("td");
        for (let i = 0; i < td.length; i++) {
            data.push(td.eq(i).text());
        }
        $('#ed-uId').attr('value',data[0]);
        console.log(data[0]);
        $('#ed-uUsername').attr('value',data[2]);
        $('#ed-uPassword').attr('value',data[3]);
        $('#ed-uName').attr('value',data[4]);
        $('#ed-uPhone').attr('value',data[5]);
        $('#ed-uAddress').attr('value',data[6]);
    });
}