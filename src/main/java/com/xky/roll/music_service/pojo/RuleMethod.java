package com.xky.roll.music_service.pojo;

import javax.persistence.*;

@Table(name = "rule_method")
public class RuleMethod {
    @Id
    @Column(name = "rule_method_id")
    private Integer ruleMethodId;

    /**
     * 全限定名
     */
    @Column(name = "class_name")
    private String className;

    /**
     * 方法名
     */
    @Column(name = "method_name")
    private String methodName;

    /**
     * 方法功用
     */
    @Column(name = "method_influence")
    private String methodInfluence;

    /**
     * @return rule_method_id
     */
    public Integer getRuleMethodId() {
        return ruleMethodId;
    }

    /**
     * @param ruleMethodId
     */
    public void setRuleMethodId(Integer ruleMethodId) {
        this.ruleMethodId = ruleMethodId;
    }

    /**
     * 获取全限定名
     *
     * @return class_name - 全限定名
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置全限定名
     *
     * @param className 全限定名
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * 获取方法名
     *
     * @return method_name - 方法名
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 设置方法名
     *
     * @param methodName 方法名
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * 获取方法功用
     *
     * @return method_influence - 方法功用
     */
    public String getMethodInfluence() {
        return methodInfluence;
    }

    /**
     * 设置方法功用
     *
     * @param methodInfluence 方法功用
     */
    public void setMethodInfluence(String methodInfluence) {
        this.methodInfluence = methodInfluence;
    }
}