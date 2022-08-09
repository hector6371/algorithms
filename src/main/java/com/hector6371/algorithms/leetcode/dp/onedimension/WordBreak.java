package com.hector6371.algorithms.leetcode.dp.onedimension;

import java.util.*;

public class WordBreak {

/*
* #139
* https://leetcode.com/problems/word-break/
* Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "com.hector6371.algorithms.leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "com.hector6371.algorithms.leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

* i                       m      n     o      p     q
* catsandog, wordDict = ["cats","dog","sand","and","cat"]
* #1 move firsts letters into a hashmap, with the word index
* #2 is s(i) is in the hashmap?
*    #2.a) none, return false
*    #2.b) once, move index until finishing word
*    #2.c) twice or more, check what happens in each case, solving the subproblem
* */


    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        //String s = "com.hector6371.algorithms.leetcode";
        //List<String> wordDict = Arrays.asList("leet", "code");
        //String s = "applepenapple";
        //List<String> wordDict = Arrays.asList("apple","pen");
        //String s = "cars";
        //List<String> wordDict = Arrays.asList("car","ca","rs");
        //String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        //List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        System.out.println(new WordBreak().isWordBreakable(s, wordDict));
    }

    public boolean isWordBreakable(String s, List<String> wordDict) {
        Map<Character, Set<Integer>> firstLetterToWordNumbersMap = mapFirstDictLetters(wordDict);
        Map<Integer, Boolean> memo = new HashMap<>();

        return isBreakableAfterIndex(s, wordDict, 0, firstLetterToWordNumbersMap, memo);
    }

    private static Map<Character, Set<Integer>> mapFirstDictLetters(List<String> wordDict) {
        Map<Character, Set<Integer>> firstLetterToWordNumbersMap = new HashMap<>();
        for (int i = 0; i < wordDict.size(); i++){
            Set<Integer> wordNumbers = firstLetterToWordNumbersMap.computeIfAbsent(wordDict.get(i).charAt(0), j -> new HashSet<>());
            wordNumbers.add(i);
            firstLetterToWordNumbersMap.put(wordDict.get(i).charAt(0), wordNumbers);
        }
        return firstLetterToWordNumbersMap;
    }

    private static boolean isBreakableAfterIndex(String s, List<String> wordDict, int sIndex, Map<Character, Set<Integer>> firstLetterToWordNumbersMap, Map<Integer, Boolean> memo) {
        boolean breakable = true;
        if (sIndex < s.length()){
            char sIndexedChar = s.charAt(sIndex);
            Optional<Boolean> isBreakableOptional = Optional.ofNullable(memo.get(sIndex));
            if (isBreakableOptional.isPresent()){
                return isBreakableOptional.get();
            }
            Set<Integer> wordNumbers = firstLetterToWordNumbersMap.get(sIndexedChar);

            if (wordNumbers == null){
                breakable = false;
            } else {
                boolean isAnyRecursiveBreakable = false;
                for(Integer wordNumber : wordNumbers){
                    //check each remaining letter of the dict word
                    String wordInDict = wordDict.get(wordNumber);
                    if (s.length() >= sIndex + wordInDict.length()) {
                        String substring = s.substring(sIndex, sIndex + wordInDict.length());
                        //System.out.println("substring is: " + substring);
                        if (substring.equals(wordInDict)) {
                            //System.out.println("substring matches: " + wordInDict);
                            if (isBreakableAfterIndex(s, wordDict,  sIndex + wordInDict.length(), firstLetterToWordNumbersMap, memo)) {
                                isAnyRecursiveBreakable = true;
                            }

                        }
                    }
                    // add wordLength to sIndex checking each letter, recursively do the same
                    // or different queues for each branch...
                }
                breakable = isAnyRecursiveBreakable;
            }
        }
        memo.put(sIndex, breakable);
        return breakable;
    }
}
