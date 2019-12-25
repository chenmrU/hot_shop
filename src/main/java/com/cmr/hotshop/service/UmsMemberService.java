package com.cmr.hotshop.service;

/**
 * 会员管理Service
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     * @param telephone
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 校验验证码
     * @param telephone
     * @param authcode
     * @return
     */
    boolean verifyAuthCode(String telephone, String authcode);

}
