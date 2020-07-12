//-----------------------------------------------------------------------------编辑菜单------------------------------

/*被绑定的按钮，先执行onclick，获得表格当前行的值并设置为模态框的值*/
function getTableData() {
    $("#menuTable tbody").on("click", "tr", function () {
        let data = new Array();
        let td = $(this).find("td");
        for (let i = 0; i < td.length; i++) {
            data.push(td.eq(i).text());
        }
        $('#ed-mdId').attr('value', data[0]);
        $('#ed-mdName').attr('value', data[1]);
        $('#ed-mdPrice').attr('value', data[2]);
        $('#ed-mdAmount').attr('value', data[3]);
        // $("input[type=radio][name=mdNew][value=mdNew]").attr('checked','checked'); 走默认
        // $("input[type=radio][name=mdStar][value='5']").attr('checked','checked'); 走默认
    });
}

function bindEditMenu() {
    let postData = {};
    let mdName = $('#ed-mdName').val();
    let mdPrice = $('#ed-mdPrice').val();
    let mdAmount = $('#ed-mdAmount').val();
    if (mdName === "" || mdPrice === "" || mdAmount === "") {
        alert("菜名、价格、数量不能为空，新添失败！");
        return;
    }
    // 查找radio
    let mdNewValue = $('input:radio[name="ed-mdNew"]:checked').val();
    postData['mdNew'] = mdNewValue;
    let mdStarValue = $('input:radio[name="ed-mdStar"]:checked').val();
    postData['mdStar'] = mdStarValue;
    let mtIdValue = $('input:radio[name="ed-mtId"]:checked').val();
    postData['mtId'] = mtIdValue;
    <!--#找input标签-->
    $('#edMenu').find('input').each(function () {
        if ($(this).attr('type') != 'radio') {
            let n = $(this).attr('name');
            let v = $(this).val();
            postData[n] = v;
        }
    });
    console.log(postData)
    $.ajax({
        url: "/updateMenu",
        type: 'POST',
        data: postData,
        success: function (arg) {
            console.log(arg);
            alert(arg)
            window.location.reload();
        }
    })
    $('#addMenu').modal('hide');
}

//-----------------------------------------------------------------------------动态预览并提交 上传图片-------------------
/*获得已有的图片 并动态设置表单提交路径*/
function getMdUrl(temp) {
    let mdUrl = $(temp).next().attr('value');
    let mdId = $(temp).next().attr('id').split('_')[1];      //分割获取id
    let mtId = $(temp).attr("name");
    if (mdUrl.length == 0) {
        mdUrl = "/images/no_Image_default.jpg";             //如果为空则默认
    }
    let actionUrl = "/upLoadImg?mdId=" + mdId + "&mtId=" + mtId;
    $('#myForm').attr("action", actionUrl);
    $("#myImg").html("<img src='" + mdUrl + "' class='img-circle'/>");
}

//  JQ点击提交表单
$("#btn").on("click", function () {
    $('#myForm').submit();
});

//  JQ 预览
$("#myFile").change(function () { //上传文件表单
    var file = this.files[0];    // 获取input上传的图片数据;
    console.log(file);
    var url = window.URL.createObjectURL(file);  // 得到file对象路径，可当成普通的文件路径一样使用，赋值给src;
    $("#myImg").html("<img src='" + url + "' class='img-circle'/>");
});

//-----------------------------------------------------------------------------添加菜品--------------------------------
function bindAdd() {
    let postData = {};
    let mdName = $('#add-mdName').val();
    let mdPrice = $('#add-mdPrice').val();
    let mdAmount = $('#add-mdAmount').val();
    if (mdName === "" || mdPrice === "" || mdAmount == "") {
        alert("菜名、价格、数量不能为空，新添失败！");
        return;
    }
    // 查找radio
    let mdNewValue = $('input:radio[name="mdNew"]:checked').val();
    postData['mdNew'] = mdNewValue;
    let mdStarValue = $('input:radio[name="mdStar"]:checked').val();
    postData['mdStar'] = mdStarValue;
    let mtIdValue = $('input:radio[name="mtId"]:checked').val();
    postData['mtId'] = mtIdValue;
    <!--#找input标签-->
    $('#addMenu').find('input').each(function () {
        if ($(this).attr('type') != 'radio') {
            let n = $(this).attr('name');
            let v = $(this).val();
            postData[n] = v;
        }
    });
    console.log(postData)
    $.ajax({
        url: "/insertMenu",
        type: 'POST',
        data: postData,
        success: function (arg) {
            console.log(arg);
            alert(arg)
            window.location.reload();
        }
    })
    $('#addMenu').modal('hide');
};

//-----------------------------------------------------------------------------新添分类--------------------------------
function addType() {
    let postData = {};
    let mtName = $('#add-mtName').val();
    if (mtName === "") {
        alert("类名不能为空");
        return;
    }
    <!--#找input标签-->
    postData['mtName'] = mtName;
    console.log(postData)
    $.ajax({
        url: "/insertType",
        type: 'POST',
        data: postData,
        success: function (arg) {
            console.log(arg);
            alert(arg)
            window.location.reload();
        }
    })
};

//-----------------------------------------------------------------------------编辑分类--------------------------------
//如果被选中了则触发 开启文本输入框
$(document).ready(function () {
    $('input[type=radio][name="edit-mtId"]').change(function () {
        $('#edit-mtName').attr("disabled", false);
        $('#edit-mtName').attr("placeholder", "请输入新的类名~");
    });
});

function editType() {
    let postData = {};
    let mtNameValue = $('#edit-mtName').val();
    if (mtNameValue === "") {
        alert("请先选择分类并填写新类名！");
        return;
    }
    postData['mtName'] = mtNameValue;

    let mtIdValue = $('input:radio[name="edit-mtId"]:checked').val();
    postData['mtId'] = mtIdValue;

    $.ajax({
        url: "/updateType",
        type: 'POST',
        data: postData,
        success: function (arg) {
            alert(arg);
            window.location.reload();
        }
    });
};

//-----------------------------------------------------------------------------删除分类--------------------------------


function deleteType() {
    //先监测是否被选中
    let postData = {};
    let mtIdValue = $('input:radio[name="delete-mtId"]:checked').val();
    if (mtIdValue === undefined) {
        alert("请先选择选择分类！");
        return;
    }
    postData['mtId'] = mtIdValue;
    $.ajax({
        url: "/deleteType",
        type: 'POST',
        data: postData,
        success: function (arg) {
            alert(arg);
            window.location.reload();
        }
    });
};

