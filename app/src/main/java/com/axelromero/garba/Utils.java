package com.axelromero.garba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {


    public static String formatAsPrice(int value){
        String price= String.valueOf(value);
        if(price.length()>3){
            price= price.substring(0, price.length()-3) + "." + price.substring(price.length()-3);
        }
        price= "$"+price;
        return price;
    }

    public static String formatAsDiscountPercentage(int value){
        String discount= String.valueOf(value);
        discount= discount + "% OFF";
        return discount;
    }

    public static String fromatAsStringWithPoint(int value){
        String string= String.valueOf(value);
        if(string.length()>3){
            string= string.substring(0, string.length()-3) + "." + string.substring(string.length()-3);
        }
        return string;
    }

    public static String getFormatedDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        SimpleDateFormat outPutFormat = new SimpleDateFormat("dd' de 'MMMM' de 'YYYY");
        try {
            Date date1= format.parse(date);
            return outPutFormat.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();

            return "";
        }
    }
}
