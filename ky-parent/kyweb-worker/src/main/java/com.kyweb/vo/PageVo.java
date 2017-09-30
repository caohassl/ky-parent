package com.kyweb.vo;

import lombok.Data;

/**
 * Created by Caomr on 2017/9/29.
 */
@Data
public class PageVo {

    private int limit=5;

    private int currPage;

    private int totalPage;
}
