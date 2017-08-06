package cn.cnr.admin.base.model;

import lombok.Getter;

import java.io.Serializable;
import java.util.function.Function;

/**
 * Created by luyufeng on 2016/6/27.
 */
@Getter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -4060845259179349523L;
    
    public static final int SUCCESS_CODE = 0;
    public static final int ERROR_CODE = 1;

    private int ret;

    private String msg;

    private T data;

    public Result() {
        this.setRet(0);
    }

    public Result(int ret, T data) {
        this.setRet(ret).setData(data);
    }

    public Result(int ret, String msg, T data) {
        this.setRet(ret).setMsg(msg).setData(data);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setRet(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String errorMessage) {
        return Result.<T>error(errorMessage, ERROR_CODE);
    }

    public static <T> Result<T> error(String errorMessage, int errorCode) {
        Result<T> result = new Result<>();
        result.setRet(errorCode);
        result.setMsg(errorMessage);
        return result;
    }

    public static <T> Result<T> asProcess(Procedure<T> procedure) {
        return asProcess(procedure, Exception::toString);
    }

    public static <T> Result<T> asProcess(Procedure<T> procedure, Function<Exception, String> exceptionHandler) {
        try {
            return success(procedure.apply());
        } catch (Exception e) {
            return error(exceptionHandler.apply(e));
        }
    }

    public Result<T> setRet(int ret) {
        this.ret = ret;
        return this;
    }

    public Result<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    @FunctionalInterface
    public interface Procedure<T> {
        T apply() throws Exception;
    }
}
