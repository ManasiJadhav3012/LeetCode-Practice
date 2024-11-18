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