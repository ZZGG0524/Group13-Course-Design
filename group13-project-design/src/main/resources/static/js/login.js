function foLogin() {
    var oUname = document.getElementById("uname");
    var oError = document.getElementById("error_box");
    var oUpass = document.getElementById("upass");

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
    }
    }
    if (oUpass.value.length < 6 || oUpass.value.length > 12) {
        oError.innerHTML = "密码要6-12位";
        isError = false;
        return;
    }
}