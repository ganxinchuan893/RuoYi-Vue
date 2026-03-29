package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysBloodSugar;
import com.ruoyi.system.mapper.SysBloodSugarMapper;
import com.ruoyi.system.service.ISysBloodSugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 血糖记录Service实现
 */
@Service
public class SysBloodSugarServiceImpl implements ISysBloodSugarService {

    @Autowired
    private SysBloodSugarMapper bloodSugarMapper;

    @Override
    public List<SysBloodSugar> selectBloodSugarList(SysBloodSugar bloodSugar) {
        return bloodSugarMapper.selectBloodSugarList(bloodSugar);
    }

    @Override
    public SysBloodSugar selectBloodSugarById(Long sugarId) {
        return bloodSugarMapper.selectBloodSugarById(sugarId);
    }

    @Override
    public int insertBloodSugar(SysBloodSugar bloodSugar) {
        return bloodSugarMapper.insertBloodSugar(bloodSugar);
    }

    @Override
    public int updateBloodSugar(SysBloodSugar bloodSugar) {
        return bloodSugarMapper.updateBloodSugar(bloodSugar);
    }

    @Override
    public int deleteBloodSugarById(Long sugarId) {
        return bloodSugarMapper.deleteBloodSugarById(sugarId);
    }

    @Override
    public int deleteBloodSugarByIds(Long[] sugarIds) {
        return bloodSugarMapper.deleteBloodSugarByIds(sugarIds);
    }

    @Override
    public Map<String, Object> selectTodayStats(Long userId) {
        return bloodSugarMapper.selectTodayStats(userId);
    }

    @Override
    public List<Map<String, Object>> selectSevenDaysTrend(Long userId) {
        return bloodSugarMapper.selectSevenDaysTrend(userId);
    }
}
