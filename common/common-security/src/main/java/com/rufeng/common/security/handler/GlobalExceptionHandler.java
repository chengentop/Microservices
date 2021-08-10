package com.rufeng.common.security.handler;

import com.rufeng.common.core.constant.HttpStatus;
import com.rufeng.common.core.exception.BaseException;
import com.rufeng.common.core.exception.CustomException;
import com.rufeng.common.core.exception.PreAuthorizeException;
import com.rufeng.common.core.utils.StringUtils;
import com.rufeng.common.core.web.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author chengen
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 基础异常
     */
    @ExceptionHandler(BaseException.class)
    public R baseException(BaseException e) {
        return R.fail(HttpStatus.ERROR, e.getDefaultMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(CustomException.class)
    public R businessException(CustomException e) {
        if (StringUtils.isNull(e.getCode())) {
            return R.fail(HttpStatus.ERROR, e.getMessage());
        }
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.fail(HttpStatus.ERROR, e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public R validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.fail(HttpStatus.ERROR, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.fail(HttpStatus.ERROR, message);
    }

    /**
     * 权限异常
     */
    @ExceptionHandler(PreAuthorizeException.class)
    public R preAuthorizeException(PreAuthorizeException e) {
        return R.fail(HttpStatus.ERROR, "没有权限，请联系管理员授权");
    }

//    /**
//     * 演示模式异常
//     */
//    @ExceptionHandler(DemoModeException.class)
//    public AjaxResult demoModeException(DemoModeException e) {
//        return AjaxResult.error("演示模式，不允许操作");
//    }
}
