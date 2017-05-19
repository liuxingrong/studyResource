package com.learning.drp.enums;

/**
 * 激活状态枚举类
 * @author lxr
 *
 */
public enum IsActive {
	ACTIVATION("激活",1),UNACTIVATED("未激活",2);
	private String name;  
    private int value;  
    
    private IsActive(String name, int value) {  
        this.name = name;  
        this.value = value;  
    }  
    
    public static String getName(int value) {  
        for (IsActive isActive : IsActive.values()) {  
            if (isActive.getValue() == value) {  
                return isActive.name;  
            }  
        }  
        return null;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public int getValue() {  
        return value;  
    }  
    public void setValue(int value) {  
        this.value = value;  
    }  

}
