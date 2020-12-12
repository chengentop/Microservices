package com.rufeng.common.security.handler;

import com.rufeng.common.core.constant.HttpStatus;
import com.rufeng.common.core.exception.BaseException;
import com.rufeng.common.core.exception.CustomException;
import com.rufeng.common.core.exception.PreAuthorizeException;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.common.core.web.domain.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultData baseException(BaseException e) {
        ResultData data = new ResultData(HttpStatus.ERROR, e.getDefaultMessage());
        return data;
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public ResultData businessException(CustomException e) {
        ResultData data = null;
        if (StringUtils.isNull(e.getCode())) {
            data = new ResultData(HttpStatus.ERROR, e.getMessage());
            return data;
        }
        data = new ResultData(e.getCode(), e.getMessage());
        return data;
    }

    @ExceptionHandler(Exception.class)
    public ResultData handleException(Exception e) {
        log.error(e.getMessage(), e);
        ResultData data = new ResultData(HttpStatus.ERROR, e.getMessage());
        return data;
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResultData validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        ResultData data = new ResultData(HttpStatus.ERROR, message);
        return data;
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        ResultData data = new ResultData(HttpStatus.ERROR, message);
        return data;
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(PreAuthorizeException.class)
    public ResultData preAuthorizeException(PreAuthorizeException e) {
        ResultData data = new ResultData(HttpStatus.ERROR, "没有权限，请联系管理员授权");
        return data;
    }

//    /**
//     * 演示模式异常
//     */
//    @ExceptionHandler(DemoModeException.class)
//    public AjaxResult demoModeException(DemoModeException e) {
//        return AjaxResult.error("演示模式，不允许操作");
//    }
}
