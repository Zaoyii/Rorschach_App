package com.zcyi.rorschach.Util.Request;

public class ApiResult<T> {
    public int resultCode;
    public T Data;
    public String Message;
    public boolean success;


    public ApiResult() {
    }

    public ApiResult(int resultCode, T data, String message, boolean success) {
        this.resultCode = resultCode;
        Data = data;
        Message = message;
        this.success = success;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "resultCode=" + resultCode +
                ", Data=" + Data +
                ", Message='" + Message + '\'' +
                ", success=" + success +
                '}';
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
