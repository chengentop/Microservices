package com.rufeng.common.core.exception.user;


import com.rufeng.common.core.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author chengen
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}