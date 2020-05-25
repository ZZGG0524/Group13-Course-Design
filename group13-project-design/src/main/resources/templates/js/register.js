function foLogin() {
    var oUname = document.getElementById("uname");
    var oError = document.getElementById("error_box");
    var oUpass = document.getElementById("upass");
    var oUpass1 = document.getElementById("upass1");
    var isError = true;
    oError.innerHTML = "<br>";

    if (oUname.value.length < 6 || oUname.value.length > 12) {
        oError.innerHTML = "用户名要6-12位";
        isError = false;
        return;
    }else if(oUname.value.charCodeAt(0)>=48 &&(oUname.value.charCodeAt(0)<=57)){
        oError.innerHTML="首位不能为数字";
        return;


    }else for (var i=0;i<oUname.value.length;i++){
       if((oUname.value.charCodeAt(i)<48)||(oUname.value.charCodeAt(i)>57)&&(oUname.value.charCodeAt(i)<58)&&(oUname.value.charCodeAt(i)>97)){
        oError.innerHTML="只能为数字和字母";
        return;

    }}
    if (oUpass.value.length < 6 || oUpass.value.length > 12) {
        oError.innerHTML = "密码要6-12位";
        isError = false;
        return;
    }else if(oUname.value!=oUpass1.value) {
        oError.innerHTML = "设置密码和验证密码不一致";
        return;
    }

        $("#submit").click(function(e){
            //阻止默认行为
            var e = e || window.event;
            e.preventDefault();
            //获取表单数据
            var username = $("#uname").val().trim();
            var password = $("#upass").val().trim();
            var password1 = $("#upass1").val().trim();
            var email = $("#youxiang").val().trim();

            //请求的参数
            var data = {'uname':username,'upass':password,'upass1':password1,'youxiang':email};

            $.ajax({
                 //请求地址
                url:'',
                //请求参数
                data,
                // 请求方式
                type:'POST',
                //成功回调
                success: function(kk){
                    console.log(kk);
                }
            });
        }