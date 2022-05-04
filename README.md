# BloomFilter Reference Implementation

## What is a BloomFilter?
BloomFilter is a Probabilistic Data Structure used to check the membership of an element in a specific dataset. The significant behaviour of bloom filter is such a way that it can return false positives but not false negatives making sure a false to be a definite non-existence of an element in a set.
https://en.wikipedia.org/wiki/Bloom_filter

## key distribution using hashes

Two approaches considered to increase the accuracy of the results,
1. Use different algorithms indipendantly and distribute bit indices
2. Use a single algorithm to generate a bash hash bits set and use bit manupulation to generate differnet hash bit sets out of that

Out of the above, first approach was considered.

## Hash functions used

1.  128-bit murmur3 algorithm
2.  Adler-32 checksum algorithm (32 hash bits) in Google Guava implementations

## Maintain positive hash integers

Boh Math.abs() & unsigned right shift operators were considered and unsigned right shift operator is used in the implementation.

## Bit map size

Bit map size was calculated based on the input dataset size/ number of algorithms used & an accuracy increasing factor. With the above, the filter was implemented to maintain an accuracylarger that or equal to 0.5.

## Remarks
1. This is a specialized form of bloom filter supporting String based elements, but can be generalised to support object type with enhancements.
2. Optional method added to support a data set passed as a list.
3. Using the 2nd approach of hashing strategy, it is possible to go for predictable accuracy ratio. 

