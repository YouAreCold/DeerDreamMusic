package com.xky.roll.music_service.util.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.xky.roll.music_service.pojo.SysExceptionLog;
import com.xky.roll.music_service.util.email.EmailUtil;

/**
 * @author wjx
 * @description:全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class CommonExceptionAdvice {

  private static Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public AjaxResult handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
    logger.error("缺少请求参数", e);
    //发送错误信息到邮件
    //EmailUtil.sendExceptionEmail("747903314@qq.com","缺少请求参数","400", ExceptionToString(e),null);
    return new AjaxResult().failure("required_parameter_is_not_present");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    logger.error("参数解析失败", e);
    //发送错误信息到邮件
    //EmailUtil.sendExceptionEmail("747903314@qq.com", "参数解析失败","400", "could_not_read_json \n"+ExceptionToString(e));
    return new AjaxResult().failure("could_not_read_json");
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public AjaxResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    logger.error("参数验证失败", e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    //发送错误信息到邮件
    //EmailUtil.sendExceptionEmail("747903314@qq.com", "参数验证失败","400", message+"\n"+ExceptionToString(e),null);
    return new AjaxResult().failure(message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public AjaxResult handleBindException(BindException e) {
    logger.error("参数绑定失败", e);
    BindingResult result = e.getBindingResult();
    FieldError error = result.getFieldError();
    String field = error.getField();
    String code = error.getDefaultMessage();
    String message = String.format("%s:%s", field, code);
    //发送错误信息到邮件
    //EmailUtil.sendExceptionEmail("747903314@qq.com","参数绑定失败", "400", message+"\n"+ExceptionToString(e),null);
    return new AjaxResult().failure(message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public AjaxResult handleServiceException(ConstraintViolationException e) {
    logger.error("参数验证失败", e);
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String message = violation.getMessage();
    //发送错误信息到邮件
    //EmailUtil.sendExceptionEmail("747903314@qq.com", "参数验证失败", "400",message+"\n"+ExceptionToString(e),null);
    //EmailUtil.sendExceptionEmail("332897611@qq.com","参数验证失败", "400", message+"\n"+ExceptionToString(e),null);
    return new AjaxResult().failure("parameter:" + message);
  }

  /**
   * 400 - Bad Request
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ValidationException.class)
  public AjaxResult handleValidationException(ValidationException e) {
    logger.error("参数验证失败", e);
    //发送错误信息到邮件
    EmailUtil.sendExceptionEmail("747903314@qq.com", "参数验证失败","400", "validation_exception \n"+ExceptionToString(e),null);
   // EmailUtil.sendExceptionEmail("332897611@qq.com", "参数验证失败","400", "validation_exception \n"+ExceptionToString(e),null);
    return new AjaxResult().failure("validation_exception");
  }

  /**
   * 405 - Method Not Allowed
   */
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public AjaxResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    logger.error("不支持当前请求方法", e);
    //发送错误信息到邮件
    EmailUtil.sendExceptionEmail("747903314@qq.com","不支持当前请求方法", "405", "request_method_not_supported \n"+ExceptionToString(e),null);
   // EmailUtil.sendExceptionEmail("332897611@qq.com","不支持当前请求方法","405", "request_method_not_supported \n"+ExceptionToString(e),null);
    return new AjaxResult().failure("request_method_not_supported");
  }

  /**
   * 415 - Unsupported Media Type
   */
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public AjaxResult handleHttpMediaTypeNotSupportedException(Exception e) {
    logger.error("不支持当前媒体类型", e);
    //发送错误信息到邮件
    EmailUtil.sendExceptionEmail("747903314@qq.com","不支持当前媒体类型","415", "content_type_not_supported \n"+ExceptionToString(e),null);
  //  EmailUtil.sendExceptionEmail("332897611@qq.com","不支持当前媒体类型", "415", "content_type_not_supported \n"+ExceptionToString(e),null);
    return new AjaxResult().failure("content_type_not_supported");
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(ServiceException.class)
  public AjaxResult handleServiceException(ServiceException e) {
    logger.error("业务逻辑异常", e);
    //发送错误信息到邮件	
    EmailUtil.sendExceptionEmail("747903314@qq.com","业务逻辑异常","500", ExceptionToString(e),null);
    //EmailUtil.sendExceptionEmail("332897611@qq.com","业务逻辑异常","500", ExceptionToString(e),null);
    return new AjaxResult().failure("业务逻辑异常：" + ExceptionToString(e));
  }

  /**
   * 500 - Internal Server Error
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public AjaxResult handleException(Exception e) {
    logger.error("通用异常", e);
    //发送错误信息到邮件
    List<SysExceptionLog> list = EmailUtil.sendExceptionEmail("747903314@qq.com","通用异常", "500",ExceptionToString(e),null);
    //EmailUtil.sendExceptionEmail("332897611@qq.com", "通用异常","500", ExceptionToString(e),null);
    for (SysExceptionLog sysExceptionLog : list) {
		System.out.println("异常====>\n"+sysExceptionLog);
	}
    return new AjaxResult().failure("通用异常：" + ExceptionToString(e));
  }

  /**
   * 操作数据库出现异常:名称重复，外键关联
   */
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(DataIntegrityViolationException.class)
  public AjaxResult handleException(DataIntegrityViolationException e) {
    logger.error("操作数据库出现异常:", e);
    //发送错误信息到邮件
    EmailUtil.sendExceptionEmail("747903314@qq.com","操作数据库出现异常", "DB", e.getMessage(),null);
    //EmailUtil.sendExceptionEmail("332897611@qq.com","操作数据库出现异常", "DB",e.getMessage(),null);
    return new AjaxResult().failure("操作数据库出现异常：字段重复、有外键关联等\n"+ExceptionToString(e));
  }
  
  /**
   * 将异常转换为字符串
   */
  public String ExceptionToString(Exception e){
	  StringWriter sw = new StringWriter(); 
	  PrintWriter pw = new PrintWriter(sw); 
	  e.printStackTrace(pw); 
	  return sw.toString();
  }
}
