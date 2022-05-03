package org.example;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class TestApp {

    public static void main(String[] args) {
//        System.out.println(optimalNumOfBits(100, 0.1));
        int hash = Hashing.murmur3_128().newHasher().putString("Sam232eswrqreera", Charset.defaultCharset()).hash().asInt();
//        System.out.println(hash);
//        System.out.println(10 >> 1);
//        System.out.println(1010 >> 1);
//        System.out.println(hash >>> 1);
        System.out.println(0.5);
        double x = 1/0.6;
        System.out.println(x);


    }
    static long optimalNumOfBits(long n, double p) {
        if (p == 0) {
            p = Double.MIN_VALUE;
        }
        return (long) (-n * Math.log(p) / (Math.log(2) * Math.log(2)));
    }




}
