package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysBloodSugar;
import com.ruoyi.system.service.ISysBloodSugarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 血糖记录Controller
 */
@RestController
@RequestMapping("/system/bloodSugar")
public class SysBloodSugarController extends BaseController {

    @Autowired
    private ISysBloodSugarService bloodSugarService;

    /**
     * 获取血糖记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:bloodSugar:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBloodSugar bloodSugar) {
        startPage();
        List<SysBloodSugar> list = bloodSugarService.selectBloodSugarList(bloodSugar);
        return getDataTable(list);
    }

    /**
     * 导出血糖记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:bloodSugar:export')")
    @Log(title = "血糖记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysBloodSugar bloodSugar) {
        List<SysBloodSugar> list = bloodSugarService.selectBloodSugarList(bloodSugar);
        ExcelUtil<SysBloodSugar> util = new ExcelUtil<>(SysBloodSugar.class);
        util.exportExcel(response, list, "血糖记录数据");
    }

    /**
     * 今日统计
     */
    @GetMapping("/todayStats")
    public AjaxResult todayStats() {
        Long userId = getUserId();
        Map<String, Object> result = bloodSugarService.selectTodayStats(userId);
        return success(result);
    }

    /**
     * 近7天趋势
     */
    @GetMapping("/sevenDaysTrend")
    public AjaxResult sevenDaysTrend() {
        Long userId = getUserId();
        List<Map<String, Object>> result = bloodSugarService.selectSevenDaysTrend(userId);
        return success(result);
    }

    /**
     * 根据ID获取血糖记录详情
     */
    @PreAuthorize("@ss.hasPermi('system:bloodSugar:query')")
    @GetMapping("/{sugarId}")
    public AjaxResult getInfo(@PathVariable("sugarId") Long sugarId) {
        return success(bloodSugarService.selectBloodSugarById(sugarId));
    }

    /**
     * 新增血糖记录
     */
    @PreAuthorize("@ss.hasPermi('system:bloodSugar:add')")
    @Log(title = "血糖记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysBloodSugar bloodSugar) {
        bloodSugar.setCreateBy(getUsername());
        return toAjax(bloodSugarService.insertBloodSugar(bloodSugar));
    }

    /**
     * 修改血糖记录
     */
    @PreAuthorize("@ss.hasPermi('system:bloodSugar:edit')")
    @Log(title = "血糖记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysBloodSugar bloodSugar) {
        bloodSugar.setUpdateBy(getUsername());
        return toAjax(bloodSugarService.updateBloodSugar(bloodSugar));
    }

    /**
     * 删除血糖记录
     */
    @PreAuthorize("@ss.hasPermi('system:bloodSugar:remove')")
    @Log(title = "血糖记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sugarIds}")
    public AjaxResult remove(@PathVariable Long[] sugarIds) {
        return toAjax(bloodSugarService.deleteBloodSugarByIds(sugarIds));
    }
}
