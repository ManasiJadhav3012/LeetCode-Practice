# Tries-1

## Problem1 
Implement Trie (Prefix Tree)(https://leetcode.com/problems/implement-trie-prefix-tree/)


Approached Algorithm: In this solution we will use Trie nested nodes to store characters from each inserted word. In this we will create a class TrieNode that contains an array children for 26 letters and a boolean isEnd to mark the end of the word. In insert function, each character of the word is added as a new node if it doesn't already exists, making the last node with isEnd as true. The search method will check if a word is fully present by traversing nodes and it will only return true if the last node matches and isEnd is true. The startsWith method will check if a prefix exists by traversing nodes without requiring final node to be an endpoint.

Time Complexity: For insert function it will be O(L) where L is the length of the word as it processes each character in the word once. For search function it will be O(L) since it checks each character in the word. For startsWith function it will be O(L) as it verifies each character in the prefix.

Space Complexity: O(N * M) where N is number of words inserted and M is the average length of the words. Each TrieNode requires space for 26 children pointers and a boolean but only populated nodes occupy the memory.

Approached Code:

class Trie {

    class TrieNode{
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            curr = curr.children[c - 'a'];
        }

        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);

            if(curr.children[c - 'a'] == null) {
                return false;
            }

            curr = curr.children[c - 'a'];
        }

        return curr.isEnd;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;

        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            if(curr.children[c - 'a'] == null) {
                return false;
            }

            curr = curr.children[c - 'a'];
        }

        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */



## Problem2
Longest Word in Dictionary(https://leetcode.com/problems/longest-word-in-dictionary/)


Approached Solution: In this approach we will use Trie data structure to store all words from the dictionary. Each word will be checked to see if it can be built one character at a time by ensuring all prefixes exist as separate word in the Trie. For each word that meets this criterion, it is considered as a potential result if it is the longest so far or, in case of a tie, lexicographically smaller. The final answer is the longest word that can be built lexicographically. 

Time Complexity: O(N * M) where N is the number of words and M is the maximum length of a word. This accounts for inserting words into the Trie and checking prefixes.

Space Complexity: O(N * M) for storing all words in the Trie. Each unique character in each word requires a node leading to a maximum of N x M nodes in the Trie.

Approached Code: 

class Solution {

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode [26];
        }

        public void insert(TrieNode root, String word) {
            TrieNode curr = root;

            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                if(curr.children[c - 'a'] == null) {
                    curr.children[c - 'a'] = new TrieNode();
                }

                curr = curr.children[c - 'a'];
            }

            curr.isEnd = true;
        }

        public boolean canBuild(TrieNode root, String word) {
            TrieNode curr = root;

            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);

                curr = curr.children[c - 'a'];

                if(curr == null || !curr.isEnd) {
                    return false;
                }

            }

            return true;
        }
    }

    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();

        for(String word: words) {
            root.insert(root, word);
        }

        String longest = "";

        for(String word: words) {
            if(root.canBuild(root, word)) {
                if(word.length() > longest.length() || (word.length() == longest.length() && word.compareTo(longest) < 0)) {
                    longest = word;
                }
            }
        }

        return longest;
    }
}



## Problem3
Replace Words (https://leetcode.com/problems/replace-words/)


Approached Solution: In this solution we are using a Trie Data Structure to efficiently replace words in a sentence with their shortest root from a dictionary. First we will insert all dictionary words into the Trie and mark the end of each root word. For each word in the sentence, we will traverse the Trie to find the shortest root that matches the beginning of the word. If the root is found, then we will replace the word with this root otherwise we will retain the original word. The final transformed sentence we will built by appending each replaced or original word.

Time Complexity: To construct a Trie we will need O(K) time complexity where K is the total number of characters across all dictionary words. For sentence transformation we will need O(N * M) where N is number of words in the sentence and M is the average length of each word.

Space Complexity: To store Trie we will need O(K) where K is the total number of characters in the dictionary and O(N * M) to store the modified sentence.

Approached Code:

class Solution {

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private void insert(String word) {
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }

            curr = curr.children[c - 'a'];
        }

        curr.isEnd = true;
    }

    private TrieNode root;

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();

        for(String word: dictionary) {
            insert(word);
        }

        StringBuilder result = new StringBuilder();
        String[] arr = sentence.split(" ");

        for(String word: arr) {
            StringBuilder replacement = new StringBuilder();

            TrieNode curr = root;

            for(int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                if(curr.children[c - 'a'] == null || curr.isEnd) {
                    break;
                }

                curr = curr.children[c - 'a'];
                replacement.append(c);
            }

            if(curr.isEnd) {
                result.append(replacement);
            } else {
                result.append(word);
            }

            result.append(" ");
        }

        return result.toString().trim();


        // HashSet<String> set = new HashSet<>(dictionary);

        // for(int i = 0; i < arr.length; i++) {
        //     if(i != 0) {
        //         result.append(" ");
        //     }

        //     String word = arr[i];
        //     boolean flag = false;

        //     for(int j = 0; j < word.length(); j++) {
        //         String subStr = word.substring(0, j+1);

        //         if(set.contains(subStr)){
        //             result.append(subStr);
        //             flag = true;
        //         } 

        //         if(flag) {
        //             break;
        //         }
        //     }

        //     if(!flag) {
        //         result.append(word);
        //     }
        // }

        // return result.toString().trim();
    }
}


