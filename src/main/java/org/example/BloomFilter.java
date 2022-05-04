package org.example;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.List;
import java.util.Objects;

public class BloomFilter {

    public static final HashFunction HASH_FUNCTION_ONE = Hashing.murmur3_128();
    public static final HashFunction HASH_FUNCTION_TWO = Hashing.adler32();
    private static final int NO_OF_HASH_FUNC = 2;
    private static final int DEFAULT_DATASET_SIZE = 400000;
    private static final int DEFAULT_ACCURACY_ENHANCEMENT_FACTOR = 2;
    private static final String INVALID_DATASET_SIZE_SHOULD_BE_LARGER_THAN_0 = "Invalid Dataset size. Should be a positive number.";
    private static final String INVALID_DATASET_CAN_NOT_BE_NULL_OR_EMPTY = "Invalid Dataset. Can not be Null or empty.";
    private int filterSize;
    private final BitSet bloomFilter = new BitSet(filterSize);

    private BloomFilter(int datasetSize) {
        this.filterSize = datasetSize * NO_OF_HASH_FUNC * DEFAULT_ACCURACY_ENHANCEMENT_FACTOR;
    }

    /**
     * Get a bloom filter instance compatible for default Dataset size of 400,000 with an accuracy larger than 0.5
     *
     * @return BloomFilter Instance
     */
    public static BloomFilter getBloomFilter() {
        return new BloomFilter(DEFAULT_DATASET_SIZE);
    }

    /**
     * Get a bloom filter instance compatible for given dataset size with an accuracy factor of larger than 0.5
     *
     * @param datasetSize - Size of the expected Dataset
     * @return BloomFilter Instance
     */
    public static BloomFilter getBloomFilter(int datasetSize) {
        if (datasetSize > 0 && datasetSize < Integer.MAX_VALUE) {
            return new BloomFilter(datasetSize);
        }
        throw new IllegalArgumentException(INVALID_DATASET_SIZE_SHOULD_BE_LARGER_THAN_0);
    }

    /**
     * Get a bloom filter instance compatible for given dataset with an accuracy factor of larger than 0.5
     *
     * @param dataAsASet - Dataset as a HashSet
     * @return BloomFilter Instance
     */
    public static BloomFilter getBloomFilter(List<String> dataAsASet) {
        if (dataAsASet != null && !dataAsASet.isEmpty()) {
            BloomFilter bloomFilter = new BloomFilter(dataAsASet.size());
            for (String key : dataAsASet) {
                bloomFilter.add(key);
            }
            return bloomFilter;
        } else {
            throw new IllegalArgumentException(INVALID_DATASET_CAN_NOT_BE_NULL_OR_EMPTY);
        }
    }

    private static int generateHashForKey(HashFunction hashFunction, String keyToHash) {
        int hash = hashFunction.newHasher().putString(keyToHash, Charset.defaultCharset()).hash().asInt();
        int positiveHash = hash >>> 1;
        return positiveHash;
    }

    private static int CalcFilterPositionForHash(int intHash, int filterSize) {
        return intHash % filterSize;
    }

    /**
     * Insert a new key to the bloom filter
     *
     * @param key - String to add
     */
    public void add(String key) {
        bloomFilter.set(getBitIndex(HASH_FUNCTION_ONE, key));
        bloomFilter.set(getBitIndex(HASH_FUNCTION_TWO, key));
    }

    /**
     * Check the existence of the key in the filter. If the filter contains the key,
     * there is a possibility to contain the key in the dataset.
     * Otherwise it is not available in the dataset.
     *
     * @param key - String to check
     * @return
     */
    public boolean containsPossibly(String key) {
        return (bloomFilter.get(getBitIndex(HASH_FUNCTION_ONE, key)) && bloomFilter.get(getBitIndex(HASH_FUNCTION_TWO, key)));
    }

    private int getBitIndex(HashFunction hashFunction, String key) {
        return CalcFilterPositionForHash(generateHashForKey(hashFunction, key), filterSize);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloomFilter that = (BloomFilter) o;
        return filterSize == that.filterSize && bloomFilter.equals(that.bloomFilter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterSize, bloomFilter);
    }
}
