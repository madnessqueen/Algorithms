package leetcode.hashing.tasks;

import java.util.HashMap;
import java.util.Map;

/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
*/
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> s1Count = new HashMap<>();
        Map<Character, Integer> s2Count = new HashMap<>();

        for (char c : s1.toCharArray()) {
            s1Count.put(c, s1Count.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < s2.length(); windowEnd++) {
            char endChar = s2.charAt(windowEnd);
            s2Count.put(endChar, s2Count.getOrDefault(endChar, 0) + 1);

            if (windowEnd >= s1.length()) {
                char startChar = s2.charAt(windowStart);
                if (s2Count.get(startChar) == 1) {
                    s2Count.remove(startChar);
                } else {
                    s2Count.put(startChar, s2Count.get(startChar) - 1);
                }
                windowStart++;
            }

            if (s1Count.equals(s2Count)) return true;
        }

        return false;
    }
}
