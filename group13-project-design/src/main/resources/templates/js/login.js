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


        
        mini.parse();
        function submitForm() {
        	debugger;
            var form = new mini.Form("#login1");
            form.validate();

            if (form.isValid() == false) return;

            //提交数据
            var data = form.getData();
            var json = mini.encode(data);
            $.ajax({
            	url:"login",
            	type:"post",
            	data:data,
            	async:false,
            	success:function(msg){
            		if(msg=="cg"){
            			alert("登录成功");
            			window.location.href="index.jsp"
            		}else if(msg=="sb"){
            			alert("账号或密码错误");
            		}else if(msg=="null"){
            			alert("无当前用户");
            		}
            		
            	},
            	error:function(res){
            		alert("返回出错");
            	}
            })
        }

        //////////////////////////////////////////
        function updateError(e) {
            var id = e.sender.name + "_error";
            var el = document.getElementById(id);
            if (el) {
                el.innerHTML = e.errorText;
            }
        }
        function onUserNameValidation(e) {                  
            updateError(e);
        }
        function onPwdValidation(e) {        
            updateError(e);
        }

