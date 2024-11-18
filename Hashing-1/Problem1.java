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
