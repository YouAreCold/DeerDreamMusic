package com.xky.roll.music_service.util.exception;

/**
 * @author wjx
 * @description:自定义异常类
 */
public class ServiceException extends RuntimeException {
  public ServiceException(String msg) {
    super(msg);
  }
}
