package com.glassdoor.base;

import java.util.Arrays;

public class cdgbhdfghf {
    public static void main(String[] args) {

        timeInWords(4, 40);
    }

    public static void timeInWords(int hour, int minute) {
        if (hour < 1 || hour > 12 || minute < 0 || minute > 60) {
            System.out.println("invalid data");
        } else if (minute == 0) {
            System.out.println(hour(hour) + " o'clock");
        } else if (minute == 30) {
            System.out.println("half past " + hour(hour));
        } else if (minute < 30) {
            System.out.println(minute(minute) + " past " + hour(hour));
        } else if (minute > 30) {
            System.out.println(minute(minute) + " to " + hour(hour + 1));
        }
    }

    public static String hour(int hour) {
        String hourStr="";
        switch (hour) {
            case 1:
                hourStr = "one";
                break;
            case 2:
                hourStr = "two";
                break;
            case 3:
                hourStr = "three";
                break;
            case 4:
                hourStr = "four";
                break;
            case 5:
                hourStr = "five";
                break;
            case 6:
                hourStr = "six";
                break;
            case 7:
                hourStr = "seven";
                break;
            case 8:
                hourStr = "eight";
                break;
            case 9:
                hourStr = "nine";
                break;
            case 10:
                hourStr = "ten";
                break;
            case 11:
                hourStr = "eleven";
                break;
            case 12:
                hourStr = "twelve";
                break;
        }
        return hourStr;
    }

    public static String minute(int minute) {
        String minuteStr="";
        switch (minute) {
            case 5:
                minuteStr = "five";
                break;
            case 15:
                minuteStr = "quarter";
                break;
            case 30:
                minuteStr = "half";
                break;
            case 40:
                minuteStr = "twenty";
                break;
            case 50:
                minuteStr = "ten";
                break;
            case 55:
                minuteStr = "five";
                break;
            //the approach will be the same for all numbers up to 60
        }
        return minuteStr;
    }
}