# Hashing-1
Explain your approach in **three sentences only** at top of your code


## Problem 1:
Given an array of strings, group anagrams together.

Example:
Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:
All inputs will be in lowercase.
The order of your output does not matter.

We can store 26 prime numbers for each letter a-z. Then we can multiply each prime number associated with letter and store the result for each word. Then we can store this sum as key for hashmap and add all the words associated with that sum as values. 

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<Double, List<String>> map = new HashMap<>();
        // HashMap<String, List<String>> map = new HashMap<>();

        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        for( String s: strs ) {
            // char[] charArr = s.toCharArray();
            // Arrays.sort(charArr);
            // String sortedStr = String.valueOf(charArr);

            double primeProduct = primeProduct(s);

            // if(!map.containsKey(sortedStr)) {
            if (!map.containsKey(primeProduct)) {
                // map.put(sortedStr, new ArrayList<>());
                map.put(primeProduct, new ArrayList<>());
            }

            // map.get(sortedStr).add(s);
            map.get(primeProduct).add(s);
        }

        return new ArrayList<>(map.values());

    }

    private double primeProduct(String s) {
        int[] prime = new int[] {2, 3, 5, 7, 11, 13, 17,19, 23, 29, 31, 
        37, 41, 43, 47, 53, 59, 61, 63, 71, 73, 79, 83, 89, 97, 101, 103};
        double result = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            result = result * prime[c - 'a'];
        }
        return result;
    }
}





## Problem 2:
Given two strings s and t, determine if they are isomorphic.
Two strings are isomorphic if the characters in s can be replaced to get t.
All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

Example 1:
Input: s = "egg", t = "add"
Output: true

Example 2:
Input: s = "foo", t = "bar"
Output: false

Example 3:
Input: s = "paper", t = "title"
Output: true
Note:
You may assume both s and t have the same length.

We can implement this solution using one HashMap and one HashSet. We will iterate through both strings simultaneously. So, first we will check if the given character in s string is already present in the map or not. If it is already present then we will check if it's associated character is the one in the current t string. If it is not then is not isomorphic string. If the character not in the map, then we will check first if given character in t string is present in set. If it is already in set then we will return false. At the same time we will update map and set in else condition. If both these cases are not true then we will return false at the end. 

class Solution {
    public boolean isIsomorphic(String s, String t) {
        
        if(s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> sMap = new HashMap<>();
        // HashMap<Character, Character> tMap = new HashMap<>();
        HashSet<Character> tSet = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            // if(sMap.containsKey(sChar)) {
            //     if(sMap.get(sChar) != tChar) {
            //         return false;
            //     }
            // } else {
            //     sMap.put(sChar, tChar);
            // }

            // if(tMap.containsKey(tChar)) {
            //     if(tMap.get(tChar) != sChar) {
            //         return false;
            //     }
            // } else {
            //     tMap.put(tChar, sChar);
            // }

            if(sMap.containsKey(sChar)) {
                if(sMap.get(sChar) != tChar) {
                    return false;
                }
            } else {
                if(tSet.contains(tChar)) {
                    return false;
                }
                sMap.put(sChar, tChar);
                tSet.add(tChar);
            }
        }

        return true;

    }
}





## Problem 3:
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Example 1:
Input: pattern = "abba", str = "dog cat cat dog"
Output: true

Example 2:
Input:pattern = "abba", str = "dog cat cat fish"
Output: false

Example 3:
Input: pattern = "aaaa", str = "dog cat cat dog"
Output: false

Example 4:
Input: pattern = "abba", str = "dog dog dog dog"
Output: false
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.

We will use same logic we used in Problem 2. We will use 2 HashMap where in the first hashmap we will store each character and associated string in it. First we will check if the current character is present in the map or not. If it is present we will check associated string matches or not. If it does not then we will return false. In the else block if it is not present we will put it into map. We will keep track ofother way around as well. We will keep map of string to character as well. At the end will return true.

class Solution {
    public boolean wordPattern(String pattern, String s) {
        
        if (pattern == null || pattern.length() == 0 || s == null || s.length() == 0) {
            return false;
        }

        HashMap<Character, String> charMap = new HashMap<>();
        HashMap<String, Character> strMap = new HashMap<>();

        String[] strArr = s.split(" ");

        if(strArr.length != pattern.length()) {
            return false;
        }
        
        for(int i = 0; i < pattern.length(); i++) {
            char pChar = pattern.charAt(i);
            String currStr = strArr[i];

            if(charMap.containsKey(pChar)) {
                if(!charMap.get(pChar).equals(currStr)) {
                    return false;
                }
            } else {
                charMap.put(pChar, currStr);
            }

            if(strMap.containsKey(currStr)) {
                if(strMap.get(currStr) != pChar) {
                    return false;
                }
            } else {
                strMap.put(currStr, pChar);
            }
        }

        return true;

    }
}