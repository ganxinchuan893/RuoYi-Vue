import request from '@/utils/request'

export function listBloodSugar(query) {
  return request({ url: '/system/bloodSugar/list', method: 'get', params: query })
}

export function getBloodSugar(sugarId) {
  return request({ url: '/system/bloodSugar/' + sugarId, method: 'get' })
}

export function addBloodSugar(data) {
  return request({ url: '/system/bloodSugar', method: 'post', data })
}

export function updateBloodSugar(data) {
  return request({ url: '/system/bloodSugar', method: 'put', data })
}

export function delBloodSugar(sugarId) {
  return request({ url: '/system/bloodSugar/' + sugarId, method: 'delete' })
}

export function getTodayStats() {
  return request({ url: '/system/bloodSugar/todayStats', method: 'get' })
}

export function getSevenDaysTrend() {
  return request({ url: '/system/bloodSugar/sevenDaysTrend', method: 'get' })
}
