# Hash Table

* The most commonly used method for hashing integers is called `modular hashing`

## 3 primary requirements in implementing a good hash function

* If `a.equals(b)` true, then `a.hashCode() == b.hashCode()` must be true.
* Efficient to compute
* Spread keys uniformly among the values (0 ~ M-1)

## Hash codes for primitive types

    * 11 % 4 = 3, which is the same as 01011 (11) & 00011 ((1 << 2) - 1)
    * byte, char, short, int: r = prime * r + (int) f
    * boolean:                r = prime * r + (f ? 1 : 0)
    * long:                   r = prime * r + (int)(f ^ (f >>> 32))
    * float:                  r = prime * r + Float.floatToIntBits(f)
    * double:                 r = prime * r + (int)(f2 ^ (f2 >>> 32))
                              f2 = Double.doubleToLongBits(f)
    * String:                 for (int i = 0; i < f.length; i++)
                                  r = prime * r + f.charAt(i);

## Convert a hash code to an array index

    int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    
## Load Factor, Rehashing and Efficiency

* Double The size of a table when rehashing (64, 128, 256, 512 ...).
  Power-of-2 sizes: bit masking is faster than integer division.  
* It is important that keeping `Load Factor(hash size / table size) < 1` 
  As Load Factor gets close to 1, the probability of a collision greatly 
  increases.
* Maintain `Load Factor < 5` for an open addressing (Linear Probing)
* Maintain `Load Factor < 0.75` for a separate chaining   
    
## Collision resolution

### Linear Probing
![LinearProbing](/images/HashTableLinearProbing.png)

### Separate Chaining
![SeparateChaining](/images/HashTableSeparateChaining.png)