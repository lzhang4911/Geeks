package lzhang.question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://www.programcreek.com/2012/12/leetcode-word-ladder/
 * 
 * Like BFS using a queue
 * 
 * Note that we are not try to find the shortest path. It's just a path!
 * 
 * Starting from char[] startWord, try every possible words using brute force.
 * Once found one from dictionary, remove it from dictionary and repeat
 * the same process on the second word.
 * 
 * for(int i = 0; i < curWord.length; i++) {
 *     for(char c = 'a'; c <= 'z'; c++) {
 *         if(curWord[i] != c) {
 *             // 0. remember cur char
 *             char temp = curWord[i];
 *             
 *             // 1. choose c to replace it
 *             curWord[i] = c;
 *             
 *             // 2. search for the new word from dict
 *             String newWord = new String(arr);
 *             if(wordDict.contains(newWord)){
 *                 // add to the queue if found
 *                 queue.add(new WordNode(newWord, top.numSteps+1));
 *                 wordDict.remove(newWord);
 *             }
 *             
 *             // 3. unchoose this char
 *             arr[i]=temp;
 *         }
 *     }
 * }                   
 *             
 * 
 * @author lzhang
 *
 */
public class WordLadder {
    public static List<String> test() {
        // given a word dictionary with every word at the same length and all in lower case
        String[] dict = {"hot","dot","dog","lot","log"};
        Set<String> dictionary = new HashSet<String>();
        for(String w : dict) {
            dictionary.add(w);
        }
        
        // Note that both start and end words are NOT in the dictionary
        String startWord = "hit";
        String endWord   = "cog";
        //String endWord   = "coo";
        
        WordLadder p = new WordLadder();
        List<String> result;
//        result = p.getShortestChain(dictionary, startWord, endWord);
        
        result = new ArrayList<String>();
        int len = p.ladderLength(startWord, endWord, dictionary, result);
        
        return result;
    }
    
    /**
     * Iterative approach
     * 
     * Using a stack is not really required because each time we can at most
     * find one matching word in the dictionary.
     * 
     * So, there is not really like the typical BFS search where  there can be
     * many child nodes at the same level.
     * 
     * TODO: actually we should treat this one as a BFS using a queue for better
     * performance. The reason is that we may found multiple words and we do want
     * to save them into the queue. 
     * 
     * 
     * @param dictionary
     * @param startWord
     * @param endWord
     * @return
     */
    private List<String> getShortestChain(Set<String> dictionary, String startWord, String endWord) {
        List<String> chain = new ArrayList<String>();
        
        if(dictionary == null || dictionary.isEmpty() || startWord == endWord) {
            return chain;
        }
        
        // first place endWord into the dictionary
        dictionary.add(endWord);
        
        // place the startWord as the first one in the chain
        chain.add(startWord);
        
        int i = 0;
        boolean foundOne = false;
        while(i < startWord.length()) {
            if(startWord.equals(endWord)){
                // found the target!
                break;
            }
            
            char[] startChars = startWord.toCharArray();
            foundOne = false;
            
            for(i = 0; i < startChars.length; i++) {
                /*
                 * Replace the char at position i with every possible chars from
                 * 'a' to 'z', see if there is any match from the dictionary.
                 */
                for(char c = 'a'; c <= 'z'; c++) {
                    char temp = startChars[i];
                    
                    // choose current c
                    startChars[i] = c;
                    
                    // process: check if the new word is found in the dict
                    // form the new word
                    String newWord = new String(startChars);
                    if(dictionary.contains(newWord)) {
                        // save this new word
                        chain.add(newWord);
                        
                        // remove it from the dictionary
                        dictionary.remove(newWord);
                        
                        // move to the new word to find the next one in chain
                        startWord = newWord;
                        
                        foundOne = true;
                        break;
                    }
                    
                    // unchoose current c
                    startChars[i] = temp;
                }
                
                if(foundOne) {
                    break;
                }
            }
        }
        
        if(i == startWord.length()) {
            // could not find the target word
            return new ArrayList<String>();
        }
        
        return chain;
    }
    
    /**
     * This is an aka BFS approach
     * 
     * @param beginWord
     * @param endWord
     * @param wordDict
     * @param result
     * @return
     */
    int ladderLength(String beginWord, String endWord, Set<String> wordDict, List<String> result) {
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));
 
        result.add(beginWord);
        wordDict.add(endWord);
 
        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
 
            if(word.equals(endWord)){
                return top.numSteps;
            }
 
            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    // for backtracking
                    char temp = arr[i];
                    
                    // choose
                    if(arr[i]!=c){
                        arr[i]=c;
                    }
 
                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        result.add(newWord);
                        
                        queue.add(new WordNode(newWord, top.numSteps+1));
                        wordDict.remove(newWord);
                    }
 
                    // unchosen
                    arr[i]=temp;
                }
            }
        }
 
        return 0;
    }
    
    class WordNode{
        String word;
        int numSteps;
     
        public WordNode(String word, int numSteps){
            this.word = word;
            this.numSteps = numSteps;
        }
    }
}
