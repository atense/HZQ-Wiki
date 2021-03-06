package com.hzqing.admin.common;

/**
 * @author hzqing
 * @date 2019-06-29 14:25
 */
public class ConstantConfig {
    private ConstantConfig(){
        throw new RuntimeException("常量类，允许实例化");
    }

    /**
     * 修改标题的key
     */
    public static final String CONFIG_TITLE = "CONFIG_TITLE";

    /**
     * 修改备案号
     */
    public static final String CONFIG_RECORD = "CONFIG_RECORD";
}
