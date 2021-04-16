import request from '@/utils/request'

// 查询登录日志列表
export function list(query) {
  return request({
    url: '/system/logininfor/list-by-page',
    method: 'get',
    params: query
  })
}

// 删除登录日志
export function delLogininfor(infoid) {
  return request({
    url: '/system/logininfor/delete/' + infoid,
    method: 'delete'
  })
}

// 清空登录日志
export function cleanLogininfor() {
  return request({
    url: '/system/logininfor/clean',
    method: 'delete'
  })
}

// 导出登录日志
export function exportLogininfor(query) {
  return request({
    url: '/system/logininfor/export',
    method: 'get',
    params: query
  })
}