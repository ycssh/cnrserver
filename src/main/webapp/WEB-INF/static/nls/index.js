
$(function(){
    $("#contentIframe").attr("height",$(window).height()-45+"px");
    $(".menu_menu").on("click",function(){
        $(".submenu").find("li").removeClass("active");
        $(this).parent("li").addClass("active");
        $("#contentIframe").attr("src",$(this).attr("url"));
    });
    $(document).on("click","#changepwd",function(){
        $('#modifyPwdModal').modal('show');
        $("#modifyPwdForm")[0].reset();
    })
    $(document).on("click","#changepwd",function(){
        $("#modifyPwdForm")[0].reset();
        $('#modifyPwdModal').modal('show');
    })

    $(".fa-desktop").eq(1).removeClass("fa-desktop").addClass("fa-pencil-square-o")


    $(document).on("click","#btnModifyPwd",function(){
        $("#modifyPwdForm").trigger("validate");
    })

    $('#modifyPwdForm').validator({
        valid: function(form) {
            $.ajax({
                type: "POST",
                url: basePath+"login/aes",
                timeout: 600000,
                success: function (data) {
                    aesKey=data;
                },
                async:false
            });
            $("#oldpassword").val(Encrypt($("#password").val()));
            $("#newpassword").val(Encrypt($("#passworda").val()));
            $("#modifyPwdForm").ajaxSubmit({
                type : "post",
                success : function(data) {
                    if(data.success){
                        $.alert(data.msg);
                        $('#modifyPwdModal').modal('hide');
                    }else{
                        $.alert(data.msg);
                    }
                },
                error:function(data){
                    $.alert(data.responseText)
                }
            })
        }
    });
    initData()
    setInterval(initData,60000);// 注意函数名没有引号和括弧
})
function initData(){
    $.post(basePath+"homepage/initData",{},function (data) {
        $("#ptTotal").html(data[0]);
        $("#nlsTotal").html(data[1]);
        $("#gameTotal").html(data[2]);
        $("#zycTotal").html(data[3]);
        $("#bxcTotal").html(data[4]);
    },"json");
}
var aesKey;
function Encrypt(word){
    var key = CryptoJS.enc.Utf8.parse(aesKey);
    var iv  = CryptoJS.enc.Utf8.parse(aesKey);
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, { iv: iv,mode:CryptoJS.mode.CBC});
    return encrypted.toString();
}