# 2010-CSC3410-Assignment2
Recognizing & grouping anagrams (Homework for Data Structures)

Implemented by mapping letters to prime numbers, to map words to composite numbers.

`output.txt` was generated from `input.txt`, which was designed to cover cases described in the assignment.

`All Anagrams.sort *.txt` were generated from my `/usr/share/dict/words` at the time.

> To generate numbers, each of the 26 letters of our alphabet is assigned a unique prime number; the number representing a word or phrase is the product of those prime numbers raised to the power of the number of occurrences of the corresponding letter in the word or phrase.  If I were to take this further, generating phrases which are anagrams of a given phrase is reduced to a factoring problem, which is well understood and I suspect more efficient than string searching.

(from the _Algorithms_ section of the lengthy descriptive comments in [anagram.java](anagram.java))
