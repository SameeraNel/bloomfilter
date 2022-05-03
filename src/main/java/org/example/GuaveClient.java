package org.example;

//import com.google.common.hash.BloomFilter;
//import com.google.common.hash.Funnels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class GuaveClient {

//    BloomFilter<String> filter;

//    private DataSetService dictionaryService = new WebFileDataSetServiceImpl();

//    public static void main(String[] args) throws IOException {
//        GuaveClient clientClass = new GuaveClient();
//        clientClass.init();
//
//        while(true) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String name = reader.readLine();
//            System.out.println("Word to check -> " + name);
//            clientClass.check(name);
//        }
//    }
//
//    private void check(String keyToCheck) {
//        if ( filter.mightContain(keyToCheck)){
//            System.out.println("It's a match");
//        } else {
//            System.out.println("Not Found");
//        }
//
//    }
//
//    private  void init() throws IOException {
//        filter = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 400000,0.01);
//        System.out.println("Loading dictionary data in to the filter ...");
//        HashSet<String> dictionaryDataSet = dictionaryService.getDataSet();
//        for (String word: dictionaryDataSet){
//            filter.put(word);
//        }
//        System.out.println("Dictionary loaded successfully.");
//        System.out.println("Content of the filter : ");
//        System.out.println("Size of the filter : " + filter.approximateElementCount());
//    }
}
