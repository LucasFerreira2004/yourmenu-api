package com.yourmenu.yourmenu_api.shared.utils;

public class NameDivider {
    public static String getFirstName(String fullName){
        String[] splitted = fullName.split(" ");
        return splitted[0];
    }

    public static String getLastname(String fullName){
        String[] splitted = fullName.split(" ");
        StringBuilder lastName = new StringBuilder();
        for(int i = 1; i < splitted.length; i++){
            lastName.append(splitted[i]);
            if (i != splitted.length - 1){
                lastName.append(" ");
            }
        }
        return lastName.toString();
    }
}
