package com.cmr.hotshop.nosql.mongo.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author chenmengrui
 * @Description: 用户商品浏览历史记录
 * @date 2020/1/6 16:36
 */
@Data
@Document
public class MemberReadHistory {

    @Id
    private String id;

    @Indexed
    private Long memberId;

    private String memberNickname;

    private String memberIcon;

    @Indexed
    private Long productId;

    private String productName;

    private String productPic;

    private String productSubTitle;

    private String productPrice;

    private Date createTime;

}
