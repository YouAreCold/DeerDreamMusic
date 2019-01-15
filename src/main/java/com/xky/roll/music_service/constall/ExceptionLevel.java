package com.xky.roll.music_service.constall;

/**
 * 
  * @ClassName: ExceptionLevel 
  * @Description: TODO(异常等级  严重程度) 
  * @author wujiaxin
  * @date 2017-9-30 下午4:23:54 
  *
 */
public enum ExceptionLevel {
	
	SERIOUS,NOTICE,REMIND;
	
	@Override
	public String toString(){
		String name = null;
		switch(this){
		case SERIOUS:
			name = "严重";
			break;
		case NOTICE:
			name = "警告";
			break;
		case REMIND:
			name = "提醒";
			break;
		}
		return name;
	}
}
