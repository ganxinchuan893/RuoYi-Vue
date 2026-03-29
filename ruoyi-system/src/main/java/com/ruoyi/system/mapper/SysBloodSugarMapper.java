package com.ruoyi.system.mapper;

import com.ruoyi.common.core.mapper.BaseMapper;
import com.ruoyi.system.domain.SysBloodSugar;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 血糖记录Mapper接口
 */
public interface SysBloodSugarMapper extends BaseMapper<SysBloodSugar> {

    /**
     * 查询血糖记录列表
     */
    List<SysBloodSugar> selectBloodSugarList(SysBloodSugar bloodSugar);

    /**
     * 根据ID查询血糖记录
     */
    SysBloodSugar selectBloodSugarById(Long sugarId);

    /**
     * 新增血糖记录
     */
    int insertBloodSugar(SysBloodSugar bloodSugar);

    /**
     * 修改血糖记录
     */
    int updateBloodSugar(SysBloodSugar bloodSugar);

    /**
     * 删除血糖记录
     */
    int deleteBloodSugarById(Long sugarId);

    /**
     * 批量删除血糖记录
     */
    int deleteBloodSugarByIds(Long[] sugarIds);

    /**
     * 今日统计
     */
    Map<String, Object> selectTodayStats(@Param("userId") Long userId);

    /**
     * 近7天趋势
     */
    List<Map<String, Object>> selectSevenDaysTrend(@Param("userId") Long userId);
}
