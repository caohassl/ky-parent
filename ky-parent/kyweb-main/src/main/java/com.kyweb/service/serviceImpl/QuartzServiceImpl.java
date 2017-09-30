package com.kyweb.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.kyweb.mapper.TbQuartzMapper;
import com.kyweb.model.TbQuartz;
import com.kyweb.service.QuartzService;
import com.kyweb.utils.DateUtil;
import com.kyweb.vo.PageVo;
import com.kyweb.vo.QuartzVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/25.
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    @Autowired
    TbQuartzMapper tbQuartzMapper;

    @Override
    public int insertQuarzMessage(QuartzVo quartzVo) {
        quartzVo.setInsertTime(DateUtil.getYMDHMSNow());
        quartzVo.setUpdateTime(DateUtil.getYMDHMSNow());
        TbQuartz tbQuartz=new TbQuartz();
        if(quartzVo!=null){
            BeanUtils.copyProperties(quartzVo,tbQuartz);
        }
        int i=tbQuartzMapper.insert(tbQuartz);
        log.info("成功插入数据{}条,msg为{}",i, JSON.toJSONString(quartzVo));
        return i;
    }

    /**
     * 展示qaurtzMessage
     * @param pageVo
     * @return
     */
    @Override
    public List<QuartzVo> showQuartzMessage(PageVo pageVo) {
        pageVo.setCurrPage(pageVo.getCurrPage()*pageVo.getLimit());
        List<TbQuartz> quartzList=tbQuartzMapper.selectShowMessage(pageVo);
        List<QuartzVo> quartzVoList=new ArrayList<QuartzVo>();
        if(quartzList!=null&&quartzList.size()>0){
            for(TbQuartz tbQuartz:quartzList){
                QuartzVo quartzVo=new QuartzVo();
                BeanUtils.copyProperties(tbQuartz,quartzVo);
                quartzVoList.add(quartzVo);
            }
        }
        pageVo.setCurrPage(pageVo.getCurrPage()/pageVo.getLimit());
        return quartzVoList;
    }

    @Override
    public PageVo selectQuartzPage(PageVo pageVo) {
        int i=tbQuartzMapper.selectQuartzPage();
        int size=(i+1)/pageVo.getLimit();
        pageVo.setTotalPage(size);
        log.info("查询到{}条,总共有{}页",i,size);
        return pageVo;
    }
}
