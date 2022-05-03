package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class ClientClass {

//    private DataSetService dictionaryService = new WebFileDataSetServiceImpl();
    private static BloomFilter bloomFilter;

//    public static void main(String[] args) throws IOException {
//        ClientClass clientClass = new ClientClass();
//        clientClass.init();
//        while(true) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String name = reader.readLine();
//            System.out.println("Word to check -> " + name);
//            clientClass.check(name);
//        }
//    }
//
//    private void check(String keyToCheck) {
//        if (bloomFilter.containsPossibly(keyToCheck)){
//            System.out.println("Key has a possibility to be in the filter");
//        } else {
//            System.out.println("Not Found, Key is not in the filter.");
//        }
//    }
//
//    private  void init() throws IOException {
//        System.out.println("Loading dictionary data in to the filter ...");
//        HashSet<String> dictionaryDataSet = dictionaryService.getDataSet();
//        bloomFilter = BloomFilter.getBloomFilter(dictionaryDataSet);
//        System.out.println("Dictionary loaded successfully.");
//        System.out.println("Content of the filter : ");
//        System.out.println(bloomFilter.getContent());
//        System.out.println("Size of the filter : " + bloomFilter.size());
//        System.out.println("Number of bits set : " + bloomFilter.cardinality());
//    }
}
