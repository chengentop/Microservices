package com.rufeng.system.business.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rufeng.common.core.db.Pager;
import com.rufeng.system.business.domain.po.SysDictData;
import com.rufeng.system.business.mapper.ISysDictDataDao;
import com.rufeng.system.business.service.ISysDictDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 字典数据 服务接口实现
 *
 * @version v1.0.0
 * @auther chengen
 * @date 2020/12/18 18:24
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<ISysDictDataDao, SysDictData> implements ISysDictDataService {
    private Logger log = LoggerFactory.getLogger(SysDictDataServiceImpl.class);

    @Autowired
    private ISysDictDataDao sysdictdataDao;

    @Override
    public SysDictData get(Integer dictcode) throws Exception {
        log.debug("###[服务] 开始查询字典数据, dictcode=[{}]. ", dictcode);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysDictData dictdata = sysdictdataDao.selectById(dictcode);
        log.info("###[服务] 完成查询字典数据 dictdata=[{}]", dictdata);
        return dictdata;
    }

    @Override
    public List<SysDictData> list(SysDictData dictData) throws Exception {
        log.debug("###[服务] 开始根据条件{}查询字典数据,dictData=[{}]", dictData);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        QueryWrapper<SysDictData> sysdictdataWrapper = new QueryWrapper<>();
        sysdictdataWrapper.setEntity(dictData);
        List<SysDictData> dictDatas = sysdictdataDao.selectList(sysdictdataWrapper);
        log.info("###[服务] 完成查询字典数据 dictDatas=[{}]", dictDatas);
        return dictDatas;
    }

    @Override
    public IPage<SysDictData> listPager(SysDictData dictData, Pager page) throws Exception {
        log.debug("###[服务] 开始根据条件{}分页查询字典数据 , dictData=[{}]", dictData);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        Page<SysDictData> pageParam = new Page<>(page.getCurrentPage(), page.getTotalSize()); // 当前页码，每页条数
        QueryWrapper<SysDictData> sysdictdataWrapper = new QueryWrapper<>();
        sysdictdataWrapper.setEntity(dictData);
        IPage<SysDictData> pu = sysdictdataDao.selectPage(pageParam, sysdictdataWrapper);
        log.info("###[服务] 完成分页查询字典数据 pu=[{}]", pu);
        return pu;
    }

    @Override
    public boolean add(SysDictData dictData) throws Exception {
        log.debug("###[服务] 开始添加新字典数据, dictData=[{}]", dictData);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = save(dictData);
        log.info("###[服务] 完成添加新字典数据，flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean update(SysDictData dictData) throws Exception {
        log.debug("###[服务] 开始根据条件{}修改字典数据,dictData=[{}]", dictData);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = updateById(dictData);
        log.info("###[服务] 完成修改字典数据，flag=[{}]", flag);
        return flag;
    }

    @Override
    public boolean delete(Integer dictcode) throws Exception {
        log.debug("###[服务] 开始根据条件{}删除字典数据, dictcode=[{}]", dictcode);
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = removeById(dictcode);
        log.info("###[服务] 完成删除字典数据，flag=[{}]", flag);
        return flag;
    }

}
