package com.rufeng.system.business.controller;

import com.rufeng.common.core.constant.CacheConstants;
import com.rufeng.common.core.db.Pager;
import com.rufeng.common.core.utils.CollectionPage;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.common.core.web.controller.BaseController;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.common.log.annotation.Log;
import com.rufeng.common.log.enums.BusinessType;
import com.rufeng.common.redis.service.RedisService;
import com.rufeng.common.security.annotation.PreAuthorize;
import com.rufeng.system.api.model.LoginUser;
import com.rufeng.system.business.domain.po.SysUserOnline;
import com.rufeng.system.business.service.ISysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 在线用户监控 控制器
 */
@RestController
@RequestMapping("/online")
public class SysUserOnlineController extends BaseController {
    @Autowired
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public R<Map<String, Object>> list(String ipaddr, String userName, Pager pager) {
        Collection<String> keys = redisService.keys(CacheConstants.LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys) {
            LoginUser user = redisService.getCacheObject(key);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName)) {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
                }
            } else if (StringUtils.isNotEmpty(ipaddr)) {
                if (StringUtils.equals(ipaddr, user.getIpaddr())) {
                    userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
                }
            } else if (StringUtils.isNotEmpty(userName)) {
                if (StringUtils.equals(userName, user.getUsername())) {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            } else {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return R.ok(CollectionPage.getPageList(userOnlineList,pager));
    }

    /**
     * 强退用户
     */
    @Log(title = "在线用户", businessType = BusinessType.FORCE)
    @RequestMapping(value = "/{tokenId}",method = RequestMethod.DELETE)
    public R<Boolean> forceLogout(@PathVariable String tokenId) {
        redisService.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + tokenId);
        return R.ok();
    }
}
