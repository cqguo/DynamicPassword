package com.example.dynamicpassword.PasswordGenerator;

import java.util.Calendar;

public class PasswordGenerator {

    String password;
    String factor;
    int precision;
    String encodedPassword;
    public int[] date;

    public void init(String password, String factor, int precision){
        this.password = password;
        this.factor = factor;
        this.precision = precision;
    }


    public String getPassword(){
        String[] splitedPassword = password.split("");
        encodedPassword = "";
        int[] date = getLimitedDate(precision);
        for(int i=1;i<splitedPassword.length;i++){
            encodedPassword += getNumber(Integer.parseInt(splitedPassword[i]) + date[(i-1)%precision]);
        }
        return encodedPassword;
    }

    public String getFactoredPassword(){
        String[] splitedPassword = encodedPassword.split("");
        String factoredPassword = "";
        for(int i=1;i<splitedPassword.length;i++){
            factoredPassword += getNumber(Math.round(Integer.parseInt(splitedPassword[i])*Float.parseFloat(factor)));
        }
        return factoredPassword;
    }


    public int[] getLimitedDate(int length){
        int[] limitedDate = new int[length];
        date = getDate();
        for(int i=0;i<length;i++){
            limitedDate[i] = date[i];
        }
        return limitedDate;
    }

    public int[] getDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return new int[]{year, month, day, hour, minute, second};
    }


    public int getNumber(int init){
        while(init>=10){
            String strInit = init + "";
            String[] seri = strInit.split("");
            int total = 0;
            for(int i=1;i<seri.length;i++){
                total += Integer.parseInt(seri[i]);
            }
            init = total;
        }
        return init;
    }
}
