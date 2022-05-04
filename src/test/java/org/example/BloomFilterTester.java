package org.example;

import org.example.helper.DataSetService;
import org.example.helper.WebFileDataSetServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterTester {
    private static DataSetService dataSetService;
    private static List<String> dataSet;

    @BeforeClass
    public static void init() throws IOException {
        dataSetService = new WebFileDataSetServiceImpl();
        dataSet = dataSetService.getDataSet();
    }

    @Test
    public void testForMissWithEmptyFilter() {
        BloomFilter bloomFilter = BloomFilter.getBloomFilter();
        int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        String testWord = dataSet.get(indexForWord);
        assertFalse(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForHitAfterAddingToFilter() {
        BloomFilter bloomFilter = BloomFilter.getBloomFilter(dataSet.size());
        int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        String testWord = dataSet.get(indexForWord);
        bloomFilter.add(testWord);
        assertTrue(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForHitAfterAddingASetToFilter() {
        BloomFilter bloomFilter = BloomFilter.getBloomFilter(dataSet);
        int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        String testWord = dataSet.get(indexForWord);
        assertTrue(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForAMissAndVerifyHitAfterAddingToFilter() {
        BloomFilter bloomFilter = BloomFilter.getBloomFilter();
        final String testWord = UUID.randomUUID().toString();
        if (bloomFilter.containsPossibly(testWord)) {
            // False Positive, Retry.
            testForAMissAndVerifyHitAfterAddingToFilter();
        } else {
            bloomFilter.add(testWord);
            assertTrue(bloomFilter.containsPossibly(testWord));
        }
    }
}
