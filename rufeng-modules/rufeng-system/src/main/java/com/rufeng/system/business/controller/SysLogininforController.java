package com.rufeng.system.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rufeng.common.core.constant.Constants;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.utils.ServletUtils;
import com.rufeng.common.core.utils.ip.IpUtils;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.system.business.domain.po.SysLogininfor;
import com.rufeng.system.business.domain.po.SysPost;
import com.rufeng.system.business.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 系统访问记录 控制器
 *
 * @version v1.0.0
 * @since jdk1.8+
 */
@RestController
@RequestMapping("logininfor")
public class SysLogininforController {

    @Autowired
    private ISysLogininforService sysLogininforService;

    /**
     * 获取系统访问记录
     *
     * @param infoid 访问ID
     * @return ResultData
     */
    @RequestMapping(value = "/get/{infoid}", method = {RequestMethod.GET})
    public R<SysLogininfor> get(@PathVariable Integer infoid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        SysLogininfor logininfor = sysLogininforService.get(infoid);
        return R.ok(logininfor);
    }

    /**
     * 查询系统访问记录
     *
     * @param syslogininfor 系统访问记录对象
     * @return ResultData
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public R<List<SysLogininfor>> list(SysLogininfor syslogininfor) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        List<SysLogininfor> list = sysLogininforService.list(syslogininfor);
        return R.ok(list);
    }


    /**
     * 分页查询系统访问记录
     *
     * @param page       分页对象
     * @param logininfor 系统访问记录对象
     * @return ResultData
     */
    @RequestMapping(value = "/list-by-page", method = {RequestMethod.GET})
    public R<IPage<SysLogininfor>> listPager(SysLogininfor logininfor, Pager page) throws Exception {
        IPage<SysLogininfor> sysLogininforIPage = sysLogininforService.listPager(logininfor, page);
        return R.ok(sysLogininforIPage);
    }

    /**
     * 添加系统访问记录信息
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public R<Boolean> saveSysUser(@RequestParam("username") String username, @RequestParam("status") String status,
                                  @RequestParam("message") String message) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        // IP地址
        String ipAddr = IpUtils.getIpAddr(ServletUtils.getRequest());
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setIpaddr(ipAddr);
        logininfor.setUsername(username);
        logininfor.setMsg(message);
        // 日志状态
        if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
            logininfor.setStatus("0");
        } else if (Constants.LOGIN_FAIL.equals(status)) {
            logininfor.setStatus("1");
        }
        boolean flag = sysLogininforService.saveLogininfor(logininfor);
        return R.ok(flag);
    }

    /**
     * 修改系统访问记录信息
     */
    @RequestMapping(value = "/update", method = {RequestMethod.PUT})
    public R<Boolean> updateSysUser(@RequestBody SysLogininfor logininfor) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysLogininforService.updateLogininfor(logininfor);
        return R.ok(flag);
    }

    /**
     * 删除系统访问记录
     *
     * @param infoid 访问ID
     * @return ResultData
     */
    @RequestMapping(value = "/delete/{infoid}", method = {RequestMethod.DELETE})
    public R<Boolean> delete(@PathVariable Integer infoid) throws Exception {
        //These code is generated by machine, if you want to modify the code, suggest you to remove this line
        boolean flag = sysLogininforService.deleteLogininfor(infoid);
        return R.ok(flag);
    }

    /**
     * 清空系统访问记录对象
     *
     * @return
     */
    @RequestMapping(value = "/clean", method = {RequestMethod.DELETE})
    public R<Boolean> clean() {
        boolean flag = sysLogininforService.cleanLogininfor();
        return R.ok(flag);
    }
}