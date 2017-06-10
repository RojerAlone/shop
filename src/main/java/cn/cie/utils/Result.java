package cn.cie.utils;

/**
 * Created by RojerAlone on 2017/6/6.
 */
public class Result<T> {

    private boolean success;
    private String msg;
    private T data;

    private Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    private Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private Result(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public static Result success() {
        return new Result(true, MsgCenter.OK);
    }

    public static Result success(Object data) {
        return new Result(true, MsgCenter.OK, data);
    }

    public static Result fail(String msg) {
        return new Result(false, msg);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
