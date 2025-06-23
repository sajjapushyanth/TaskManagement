package com.project.taskManagement.helper;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateRandomNumber {
    public static int generateOtp(int min,int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);

    }
    public static void main(String[] args){
        int randomOtp= generateOtp(100000, 999999);
        System.out.println("Random OTP: " + randomOtp);
    }
}
