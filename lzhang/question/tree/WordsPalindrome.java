package lzhang.question.tree;

import java.util.ArrayList;
import java.util.List;

import lzhang.util.BaseUtil;

/**
 * https://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/
 * 
 * Given a list of words, find the pairs that each forms a palindrome.
 * 
 * Input: 
 * { "none", "xenon", "geekf", "geeks", "or", "keeg", "abc", "cb"}
 * 
 * Output:
 * "none", "xenon"
 * "geekf", "keeg"
 * "geeks", "keeg"
 * "abc", "cb" ->cbabc
 * 
 * An O(kn^2) solution is to try every pair and check where k is the max length of the pair.
 * 
 * Better solution in O(nk^2) using trie.
 * 
 * 
 * @author lzhang
 *
 */
public class WordsPalindrome extends BaseUtil {

    static class TrieNode {
        private static final int ALPHABET_SIZE = 26;
        
        // this is not really needed since the index in children already determines what character it is.
        public char c;
        
        // tells if this is the leaf node
        public boolean isLeaf;
        
        // all 26 characters
        public TrieNode[] children;
        
        // the original index of the word i the dictionary
        public int sourceListIndex = -1;
        
        // default constructor
        public TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
        }
        
        public TrieNode(char c, int sourceListIndex, boolean isLeaf) {
            this();
            
            this.c = c;
            this.sourceListIndex = sourceListIndex;
            this.isLeaf = isLeaf;
        }
        
        @Override
        public String toString() {
            return String.format("[%s, isLeaf: %s, sourceListIndex: %d]", c, isLeaf, sourceListIndex);
        }
    }
    
    public static void test() {
        WordsPalindrome p = new WordsPalindrome();
        
        // words
        String[] words = {"none", "xenon", "geekf", "geeks", "or", "keeg", "abc", "cb"};
        
        // add all of the dictionary words one at a time (inverse) to create a trie
        TrieNode root = p.createTrie(words);
        
        /*
         * Check each word against the trie and see if a palindrome can be formed. Note
         * that a word and its inverse can always form a palindrome and therefore shall
         * be excluded by checking their indexes in the original array.
         */
        List<String> ret = p.findPalindromePairs(root, words);
        print("Palindrome pairs: ");
        for(String s : ret) {
            print("\t" + s); 
        }
    }
    
    /**
     * Create a Trie out of the words
     * @param words
     * @return
     */
    private TrieNode createTrie(String[] words) {
        if(words == null) return null;
        
        TrieNode root = new TrieNode();
        
        for(int i = 0; i < words.length; i++) {
            addWord(root, words[i].toLowerCase(), i);
        }
        
        return root;
    }
    
    /**
     * Each word is inversely added to the Trie
     * @param root
     * @param word
     * @param arrayIndex
     */
    private void addWord(TrieNode root, String word, int arrayIndex) {
        if(root == null) {
            throw new IllegalArgumentException("Trie root must not be null");
        }
        
        TrieNode temp = root;
        for(int i = word.length() - 1; i >= 0; i--) {
            char c = word.charAt(i);
            int childIndex = c - 'a';
            
            if(temp.children[childIndex] == null) {
                temp.children[childIndex] = new TrieNode(c, arrayIndex, i == 0);
            }
            
            temp = temp.children[childIndex];
        }
    }
    
    private List<String> findPalindromePairs(TrieNode root, String[] words) {
        List<String> result = new ArrayList<String>();
        
        for(int i = 0; i < words.length; i++) {
            String pair = findPalindromePair(root, words, i);
            if(pair != null) {
                result.add(pair);
            }
        }
        
        return result;
    }
    
    private String findPalindromePair(TrieNode root, String[] words, int wordIndex) {
        TrieNode node = root;
        
        String word = words[wordIndex].toLowerCase();
        for(int i = 0; i < word.length(); i++) {
            int childIndex = word.charAt(i) - 'a';
            TrieNode temp = node.children[childIndex];
            if(temp == null) {
                return null;
            }
            
            // skip the same word (when they each is a perfect palindrome)
            if(temp.sourceListIndex == wordIndex) {
                return null;
            }
            
            /*
             * The path in trie has been matching till now. There are 2 situations where
             * we must check before recursing to the next character match:
             * 
             * 1. if the trie path already reaches its leaf;
             * 2. the word already reaches its last character while the trie path may have not reached its leaf yet.
             * 
             * In either case, we just need to verify if the remainder is a palindrome. 
             * 
             * Case 1: 
             *   "geeks" and "keeg" where the second word is inversely presented in the trie as "geek". While 
             *   searching word "geeks" in the trie, the first 4 characters are already matched and reached to
             *   the leaf but the word still has a remaining (the last character 's'). Then we just need to
             *   verify that the remaining is a palindrome which indeed is.
             *   
             * Case 2:
             *   In this case, the trie path has remainder.
             */
            if(temp.isLeaf) {
                /*
                 * The prefix up to i in current word matches a path in the trie. Let's
                 * see if the suffix is still a palindrome.
                 * 
                 * Note that multiple words could overlap partially along the same trie path. For example,
                 * "abc", "abcd", "abcdf". Should we consider this situation and match as many as possible?
                 */
                if(isPalindrome(word.substring(i+1))) {
                    // these 2 words form a palindrome, assuming there is only one leaf node ended here
                    return word + "-" + words[temp.sourceListIndex];
                }
            } else if(i == word.length() - 1) {
                String rest = getRest(temp);
                if(isPalindrome(rest)) {
                    // these 2 words form a palindrome, assuming there is only one leaf node ended here
                    return word + "-" + words[temp.sourceListIndex];
                }
            }
            
            node = temp;
        }
        
        return null;
    }
    
    private boolean isPalindrome(String str) {
        if(str == null || str.length() == 1) return true;
        
        int s = 0, e = str.length() - 1;
        while (s < e) {
            if(str.charAt(s++) != str.charAt(e--)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Get the remaining of this trie path
     * 
     * @param node
     * @return
     */
    private String getRest(TrieNode node) {
        StringBuilder sb = new StringBuilder();
        
        while(!node.isLeaf) {
            for(TrieNode n : node.children) {
                if(n != null && n.sourceListIndex == node.sourceListIndex) {
                    sb.append(n.c);
                    
                    node = n;
                }
            }
        }
        
        return sb.toString();
    }
}
