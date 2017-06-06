package cn.cie.common.Enums;

/**
 * Created by RojerAlone on 2017/6/6.
 */
public enum ResultEnum {

    SUCCESS(1, "成功"),
    ERROR(1, "服务器内部错误");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
