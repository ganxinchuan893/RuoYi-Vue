<template>
  <div class="dashboard-container">
    <!-- 统计卡片 -->
    <el-row :gutter="20" class="stats-row">
      <el-col :span="8">
        <div class="stat-card stat-avg">
          <div class="stat-label">今日平均</div>
          <div class="stat-value">{{ stats.avgSugar || '--' }} <span class="stat-unit">mmol/L</span></div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="stat-card stat-max">
          <div class="stat-label">今日最高</div>
          <div class="stat-value">{{ stats.maxSugar || '--' }} <span class="stat-unit">mmol/L</span></div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="stat-card stat-min">
          <div class="stat-label">今日最低</div>
          <div class="stat-value">{{ stats.minSugar || '--' }} <span class="stat-unit">mmol/L</span></div>
        </div>
      </el-col>
    </el-row>

    <!-- 今日血糖曲线 -->
    <el-card class="chart-card">
      <div slot="header" class="card-header">
        <span>今日血糖曲线</span>
      </div>
      <div ref="todayChart" style="height: 300px;"></div>
    </el-card>

    <!-- 7天趋势 + 记录列表 -->
    <el-row :gutter="20" class="bottom-row">
      <el-col :span="12">
        <el-card>
          <div slot="header" class="card-header">
            <span>近7天趋势</span>
          </div>
          <div ref="weekChart" style="height: 250px;"></div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div slot="header" class="card-header">
            <span>血糖记录</span>
            <el-button style="float: right; padding: 3px 10px" type="primary" size="mini" @click="handleAdd">添加记录</el-button>
          </div>
          <el-table :data="tableData" style="width: 100%" v-loading="loading" max-height="250">
            <el-table-column prop="measureTime" label="时间" width="160" formatter="formatDate"></el-table-column>
            <el-table-column prop="sugarValue" label="血糖值" width="100">
              <template slot-scope="scope">
                <span :class="getSugarClass(scope.row.sugarValue)">{{ scope.row.sugarValue }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="mealType" label="类型" width="90">
              <template slot-scope="scope">
                <el-tag :type="scope.row.mealType === '0' ? 'success' : scope.row.mealType === '1' ? 'warning' : 'info'" size="mini">
                  {{ formatMealType(scope.row.mealType) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <!-- 添加/编辑弹窗 -->
    <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="血糖值" prop="sugarValue">
          <el-input-number v-model="form.sugarValue" :min="1" :max="35" :precision="1" style="width:100%"></el-input-number>
          <span class="form-tip">单位：mmol/L</span>
        </el-form-item>
        <el-form-item label="测量时间" prop="measureTime">
          <el-date-picker v-model="form.measureTime" type="datetime" placeholder="选择时间" value-format="yyyy-MM-dd HH:mm:ss" style="width:100%"></el-date-picker>
        </el-form-item>
        <el-form-item label="类型" prop="mealType">
          <el-radio-group v-model="form.mealType">
            <el-radio label="0">空腹</el-radio>
            <el-radio label="1">餐前</el-radio>
            <el-radio label="2">餐后</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" placeholder="可选备注" maxlength="200"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBloodSugar, getBloodSugar, addBloodSugar, updateBloodSugar, delBloodSugar, getTodayStats, getSevenDaysTrend } from '@/api/system/bloodSugar'

export default {
  name: 'Index',
  data() {
    return {
      loading: false,
      tableData: [],
      stats: {},
      weekData: [],
      dialogVisible: false,
      dialogTitle: '',
      form: {},
      rules: {
        sugarValue: [{ required: true, message: '请输入血糖值', trigger: 'blur' }],
        measureTime: [{ required: true, message: '请选择时间', trigger: 'change' }],
        mealType: [{ required: true, message: '请选择类型', trigger: 'change' }]
      }
    }
  },
  created() {
    this.getList()
    this.getStats()
    this.getWeekData()
  },
  mounted() {
    this.initCharts()
  },
  methods: {
    getList() {
      this.loading = true
      listBloodSugar({ pageNum: 1, pageSize: 20 }).then(res => {
        this.tableData = res.rows || []
        this.loading = false
      }).catch(() => { this.loading = false })
    },
    getStats() {
      getTodayStats().then(res => {
        this.stats = res.data || {}
      })
    },
    getWeekData() {
      getSevenDaysTrend().then(res => {
        this.weekData = res.data || []
        this.initCharts()
      })
    },
    initCharts() {
      if (!this.$refs.todayChart) return
      // echarts is available via npm package in RuoYi-Vue
      const echarts = require('echarts')
      const todayChart = echarts.init(this.$refs.todayChart)
      todayChart.setOption({
        tooltip: { trigger: 'axis' },
        xAxis: { type: 'category', data: ['08:00', '08:30', '09:00', '12:00', '13:00', '18:00', '19:00'] },
        yAxis: { type: 'value', name: 'mmol/L', min: 3, max: 12 },
        series: [{
          data: [5.5, 7.2, 8.5, 6.8, 9.1, 5.9, 7.8],
          type: 'line', smooth: true,
          areaStyle: { color: 'rgba(64, 158, 255, 0.2)' },
          lineStyle: { color: '#409EFF' }
        }]
      })

      if (this.$refs.weekChart) {
        const weekChart = echarts.init(this.$refs.weekChart)
        weekChart.setOption({
          tooltip: { trigger: 'axis' },
          xAxis: { type: 'category', data: this.weekData.map(d => d.measureDate) },
          yAxis: { type: 'value', name: 'mmol/L' },
          series: [{
            data: this.weekData.map(d => d.avgSugar),
            type: 'bar',
            itemStyle: { color: '#67C23A' }
          }]
        })
      }
    },
    handleAdd() {
      this.form = { mealType: '0', measureTime: this.formatDate(new Date()) }
      this.dialogTitle = '添加记录'
      this.dialogVisible = true
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          const action = this.form.sugarId ? updateBloodSugar : addBloodSugar
          action(this.form).then(() => {
            this.msgSuccess('保存成功')
            this.dialogVisible = false
            this.getList()
            this.getStats()
          })
        }
      })
    },
    formatMealType(type) {
      return { '0': '空腹', '1': '餐前', '2': '餐后' }[type] || type
    },
    getSugarClass(value) {
      const v = parseFloat(value)
      if (v < 3.9) return 'sugar-low'
      if (v > 7.8) return 'sugar-high'
      return 'sugar-normal'
    },
    formatDate(date) {
      const pad = n => n < 10 ? '0' + n : n
      return `${date.getFullYear()}-${pad(date.getMonth()+1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-container { padding: 20px; }
.stats-row { margin-bottom: 20px; }
.stat-card {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 12px;
  padding: 24px;
  color: #fff;
  box-shadow: 0 4px 12px rgba(0,0,0,0.1);
}
.stat-card.stat-avg { background: linear-gradient(135deg, #409EFF, #66b1ff); }
.stat-card.stat-max { background: linear-gradient(135deg, #F56C6C, #f89f9f); }
.stat-card.stat-min { background: linear-gradient(135deg, #67C23A, #85ce61); }
.stat-label { font-size: 14px; opacity: 0.9; margin-bottom: 8px; }
.stat-value { font-size: 32px; font-weight: bold; }
.stat-unit { font-size: 14px; font-weight: normal; }
.chart-card, .bottom-row { margin-bottom: 20px; }
.card-header { font-weight: 600; font-size: 16px; }
.sugar-low { color: #F56C6C; font-weight: bold; }
.sugar-normal { color: #67C23A; font-weight: bold; }
.sugar-high { color: #E6A23C; font-weight: bold; }
.form-tip { font-size: 12px; color: #999; margin-left: 8px; }
</style>
