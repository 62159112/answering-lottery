package com.chongdong.lotterysurvey.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public interface PageService<T> extends Mapper<T> {
    Map<String,Object> getModelMap(Page<T> iPage);

    Page<T> getModelPage(Integer page,Integer size);

    Page<T> getPageList(BaseMapper<T> baseMapper,Page<T> page);
}
