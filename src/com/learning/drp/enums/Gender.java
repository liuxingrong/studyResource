package com.learning.drp.enums;

public enum Gender {
	MAN("男",1),WOMAN("女",2);
	private String name;  
    private int value;  
    
    private Gender(String name, int value) {  
        this.name = name;  
        this.value = value;  
    }  
    
    public static String getName(int value) {  
        for (Gender gender : Gender.values()) {  
            if (gender.getValue() == value) {  
                return gender.name;  
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
