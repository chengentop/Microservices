package com.rufeng.common.core.exception.file;

import com.rufeng.common.core.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author chengen
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
