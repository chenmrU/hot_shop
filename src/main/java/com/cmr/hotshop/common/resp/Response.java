package com.cmr.hotshop.common.resp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chenmengrui
 * @Description: 通用返回对象
 * @date 2019/12/24 15:09
 */
@Getter
@Setter
@ToString
public class Response<T> {

    private long code;
    private String message;
    private T data;

    protected Response() {
    }

    protected Response(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     * @param <T>
     * @return
     */
    public static <T> Response<T> success() {
        return new Response<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data, String message) {
        return new Response<>(ResponseCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Response<T> failed(IResponseCode resultCode) {
        return new Response<>(resultCode.getCode(), resultCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> failed(String message) {
        return new Response<>(ResponseCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static <T> Response<T> failed() {
        return new Response<>(ResponseCode.FAILED.getCode(), ResponseCode.FAILED.getMessage(), null);
    }

    /**
     * 参数校验失败返回结果
     * @return
     */
    public static <T> Response<T> validateFailed() {
        return new Response<>(ResponseCode.VALIDATE_FAILED.getCode(), ResponseCode.VALIDATE_FAILED.getMessage(), null);
    }

    /**
     * 参数校验失败返回结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> Response<T> validateFailed(String message) {
        return new Response<>(ResponseCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     * @param <T>
     * @return
     */
    public static <T> Response<T> unauthorized(T data) {
        return new Response<>(ResponseCode.UNAUTHORIZED.getCode(), ResponseCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> forbidden(T data) {
        return new Response<>(ResponseCode.FORBIDDEN.getCode(), ResponseCode.FORBIDDEN.getMessage(), data);
    }

}
