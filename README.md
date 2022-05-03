# BloomFilter Reference Implementation

## What is a BloomFilter?
BloomFilter is a Probabilistic Data Structure used to check the membership of an element in a specific dataset. The significant behaviour of bloom filter is such a way that it can return false positives but not false negatives making sure a false to be a definite non-existence of an element in a set.

https://en.wikipedia.org/wiki/Bloom_filter

## key distribution using hashes

Two approaches considered to increase the accuracy of the results,
1. Use different algorithms indipendantly
2. Use a base algorithm to generate a bash hash bits set and use bit manupulation to generate differnet hash bit sets

Out of the above, first approach was considered.

## Hash functions used

1.  128-bit murmur3 algorithm
2.  Adler-32 checksum algorithm (32 hash bits) in Google Guava implementations

## Maintain positive hash integers

Boh Math.abs() & unsigned right shift operators were considered and unsigned right shift operator is used in the implementation.

