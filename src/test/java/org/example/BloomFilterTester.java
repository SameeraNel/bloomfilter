package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterTester {
    private static DataSetService dataSetService;
    private static List<String> dataSet;
    private BloomFilter bloomFilter;

    @BeforeClass
    public static void init() {
        dataSetService = new WebFileDataSetServiceImpl();
        dataSet = dataSetService.getDataSet();
    }

    @Before
    public void DataSetup() {
        bloomFilter = BloomFilter.getBloomFilter();
    }

    @Test
    public void testForMissWithEmptyFilter() {
        int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        String testWord = dataSet.get(indexForWord);
        assertFalse(bloomFilter.containsPossibly(testWord));
        System.out.println(testWord);
    }

    @Test
    public void testForHitAfterAddingToFilter() {
        int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        String testWord = dataSet.get(indexForWord);
        bloomFilter.add(testWord);
        assertTrue(bloomFilter.containsPossibly(testWord));
        System.out.println(testWord);

    }

    @Test
    public void testForHitAfterAddingASetToFilter() {
        for (String word : dataSet) {
            bloomFilter.add(word);
        }
        int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        String testWord = dataSet.get(indexForWord);
        System.out.println(testWord);
        assertTrue(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForAMissAndVerifyHitAfterAddingToFilter() {
        final String testWord = UUID.randomUUID().toString();
        System.out.println(testWord);
        if (bloomFilter.containsPossibly(testWord)) {
            // False Positive, Retry.
            System.out.println("False Positive - " + testWord);
            testForAMissAndVerifyHitAfterAddingToFilter();
        } else {
            bloomFilter.add(testWord);
            assertTrue(bloomFilter.containsPossibly(testWord));
        }
    }
}
