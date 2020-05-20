package com.zhuang.group13projectdesign.enums;





public enum ResultStatus {

    PARAMETERS_MISTASK(2 ,"参数错误"),
    DELETE_SUCCESS(3,"账号密码错误"),
    UPDATE_SUCCESS(4,"修改成功"),
    REQUEST_ERROR(5,"请求异常"),
    REQUEST_SUCCESS(6,"请求成功"),
    AUTH_NOT_ALLOWED(7,"权限受限"),
    REQUEST_FAIL(8,"请求失败"),
    DATA_IS_EMPTY(9,"暂无数据")

    ;


    private  int  code;

    private  String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public  String  getValue(int code){
        for (ResultStatus c: ResultStatus.values()){
            if(this.code==c.code){
                return  c.msg;
            }
        }
        return  null;
    };

}
