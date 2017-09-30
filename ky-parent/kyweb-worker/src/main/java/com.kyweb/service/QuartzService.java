package com.kyweb.service;

import com.kyweb.model.TbQuartz;
import com.kyweb.vo.PageVo;
import com.kyweb.vo.QuartzVo;

import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */
public interface QuartzService {
    /**
     * 插入一条记录
     * @param quartzVo
     * @return
     */
    int insertQuarzMessage(QuartzVo quartzVo);

    List<QuartzVo> showQuartzMessage(PageVo pageVo);

    PageVo selectQuartzPage(PageVo pageVo);

    List<TbQuartz> getNeedRunQuartz(String currTime);

    void updateByPrimaryKey(TbQuartz tbQuartz);
}
