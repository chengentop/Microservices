package com.rufeng.system.api;

import com.rufeng.system.api.domain.SysOperLog;
import com.rufeng.system.api.factory.RemoteLogFallbackFactory;
import com.rufeng.common.core.constant.ServiceNameConstants;
import com.rufeng.common.core.web.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 日志服务
 *
 * @author chengen
 */
@FeignClient(contextId = "remoteLogService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory =
        RemoteLogFallbackFactory.class)
public interface RemoteLogService {
    /**
     * 保存系统日志
     *
     * @param sysOperLog 日志实体
     * @return 结果
     */
    @RequestMapping(value = "/operlog", method = RequestMethod.POST)
    R<Boolean> saveLog(@RequestBody SysOperLog sysOperLog);

    /**
     * 保存访问记录
     *
     * @param username 用户名称
     * @param status   状态
     * @param message  消息
     * @return 结果
     */
    @RequestMapping(value = "/logininfor/save", method = RequestMethod.POST)
    R<Boolean> saveLogininfor(@RequestParam("username") String username, @RequestParam("status") String status,
                              @RequestParam("message") String message);
}
