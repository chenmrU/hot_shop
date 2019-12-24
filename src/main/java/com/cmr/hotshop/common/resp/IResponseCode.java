package com.cmr.hotshop.common.resp;

/**
 * @author chenmengrui
 * @date 2019/12/24 15:23
 */
public interface IResponseCode {

    /**
     * 状态码
     * @return
     */
    long getCode();

    /**
     * 信息
     * @return
     */
    String getMessage();

}
