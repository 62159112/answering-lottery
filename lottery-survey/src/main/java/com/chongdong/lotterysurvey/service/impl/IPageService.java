package com.chongdong.lotterysurvey.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chongdong.lotterysurvey.service.PageService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IPageService<T> implements PageService<T> {
    /**
     * 获取数据，返回数据及分页信息
     * */
    @Override
    public Map<String, Object> getModelMap(Page<T> iPage) {
        Map<String,Object> map = new HashMap<>();
        if (iPage.getRecords().size()>0){
            map.put("record",iPage.getRecords());
            map.put("pageCount",iPage.getPages());
            map.put("total",iPage.getTotal());
            map.put("pageNow",iPage.getCurrent());
            map.put("pageSize",iPage.getSize());
            return map;
        }else {
            return null;
        }
    }
    /**
     * 设置默认分页数据
     * */
    @Override
    public Page<T> getModelPage(Integer page, Integer size) {
        if (page==null){
            page = 1;
        }
        if (size == null){
            size = 5;
        }
        Page<T> modelPage = new Page<>(page,size);
        return modelPage;
    }

    @Override
    public Page<T> getPageList(BaseMapper<T> baseMapper, Page<T> page) {
        return new LambdaQueryChainWrapper<>(baseMapper).page(page);
    }
}
