import request from '@/utils/request'

// 查询文章列表
export function listArticle(query) {
  return request({
    url: '/article/list-by-page',
    method: 'get',
    params: query
  })
}

// 查询文章详细
export function getArticle(articleid) {
  return request({
    url: '/article/get/' + articleid,
    method: 'get'
  })
}

// 新增文章
export function addArticle(data) {
  return request({
    url: '/article/add',
    method: 'post',
    data: data
  })
}

// 修改文章
export function updateArticle(data) {
  return request({
    url: '/article/update',
    method: 'put',
    data: data
  })
}

// 删除文章
export function delArticle(articleid) {
  return request({
    url: '/article/delete/' + articleid,
    method: 'delete'
  })
}
