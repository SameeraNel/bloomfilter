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
    private static List<String> dataSet;

    @BeforeClass
    public static void init() throws IOException {
        final DataSetService dataSetService = new WebFileDataSetServiceImpl();
        dataSet = dataSetService.getDataSet();
    }

    @Test
    public void testForMissWithEmptyFilter() {
        final BloomFilter bloomFilter = BloomFilter.getBloomFilter();
        final int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        final String testWord = dataSet.get(indexForWord);
        assertFalse(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForHitAfterAddingToFilter() {
        final BloomFilter bloomFilter = BloomFilter.getBloomFilter(dataSet.size());
        final int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        final String testWord = dataSet.get(indexForWord);
        bloomFilter.add(testWord);
        assertTrue(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForHitAfterAddingASetToFilter() {
        final BloomFilter bloomFilter = BloomFilter.getBloomFilter(dataSet);
        final int indexForWord = (int) (Math.random() * (dataSet.size() + 1));
        final String testWord = dataSet.get(indexForWord);
        assertTrue(bloomFilter.containsPossibly(testWord));
    }

    @Test
    public void testForAMissAndVerifyHitAfterAddingToFilter() {
        final BloomFilter bloomFilter = BloomFilter.getBloomFilter();
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
