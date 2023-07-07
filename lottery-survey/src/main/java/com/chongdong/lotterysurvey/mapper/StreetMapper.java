package com.chongdong.lotterysurvey.mapper;

import com.chongdong.lotterysurvey.model.Street;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author cd
* @description 针对表【tcd_street(镇/街道表)】的数据库操作Mapper
* @createDate 2023-07-07 16:42:22
* @Entity com.chongdong.lotterysurvey.model.Street
*/
public interface StreetMapper extends BaseMapper<Street> {
    /**
     * 根据选择的街道id查询街道全名
     * @param streetId 1 老城街道
     * @return 红花岗区老城街道
     */
    String searchStreetFullNameByStreetId(Integer streetId);
}




