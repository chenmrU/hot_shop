package com.cmr.hotshop.service;

import com.cmr.hotshop.dto.OssCallbackResult;
import com.cmr.hotshop.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author chenmengrui
 * @Description: oss上传管理Service
 * @date 2020/1/11 10:17
 */
public interface OssService {

    /**
     * oss上传策略生成
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);

}
