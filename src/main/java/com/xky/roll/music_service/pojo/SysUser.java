package com.xky.roll.music_service.pojo;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

@Table(name = "sys_user")
public class SysUser implements HttpSessionBindingListener{
    /**
     * 用户标识
     */
    @Id
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 用户名称
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "PWD")
    private String pwd;

    /**
     * 上次登录时间
     */
    @Column(name = "LAST_LOGIN_TIME")
    private Date lastLoginTime;

    /**
     * 登录时间
     */
    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    /**
     * 退出时间
     */
    @Column(name = "LOGOUT_TIME")
    private Date logoutTime;

    /**
     * 创建时间
     */
    @Column(name = "CREATETIME")
    private Date createtime;

    /**
     * 是否可用（0：是；1：否）
     */
    @Column(name = "IS_ENABLE")
    private Integer isEnable;

    /**
     * 用户密匙
     */
    @Column(name = "USER_KEY")
    private String userKey;

    /**
     * 获取用户标识
     *
     * @return USER_ID - 用户标识
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户标识
     *
     * @param userId 用户标识
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return USER_NAME - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return PWD - 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * 获取上次登录时间
     *
     * @return LAST_LOGIN_TIME - 上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param lastLoginTime 上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取登录时间
     *
     * @return LOGIN_TIME - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取退出时间
     *
     * @return LOGOUT_TIME - 退出时间
     */
    public Date getLogoutTime() {
        return logoutTime;
    }

    /**
     * 设置退出时间
     *
     * @param logoutTime 退出时间
     */
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    /**
     * 获取创建时间
     *
     * @return CREATETIME - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取是否可用（0：是；1：否）
     *
     * @return IS_ENABLE - 是否可用（0：是；1：否）
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否可用（0：是；1：否）
     *
     * @param isEnable 是否可用（0：是；1：否）
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取用户密匙
     *
     * @return USER_KEY - 用户密匙
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * 设置用户密匙
     *
     * @param userKey 用户密匙
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
    
    
    /**
     * session检查是否过期
     */
    public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("进入了院 内 云 平 台........................");
		HttpSession session = event.getSession();

		Map<SysUser, HttpSession> userMap = (Map<SysUser, HttpSession>) session
				.getServletContext().getAttribute("userMap");

		userMap.put(this, session);

	}

	// 当session和对象解除绑定的时候
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("退出了院 内 云 平 台........................");
		HttpSession session = event.getSession();
		// 获得人员列表
		Map<SysUser, HttpSession> userMap = (Map<SysUser, HttpSession>) session
				.getServletContext().getAttribute("userMap");
		// 将用户移除了
		userMap.remove(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysUser other = (SysUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
}