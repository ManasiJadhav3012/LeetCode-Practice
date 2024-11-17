// Time Complexity : For the BFS solution time complexity will be O(2^n * n^2) where n is length of the string input where we are generating all possible substrings
// and checking their validity.
// Space Complexity : For the BFS solution space complexity will be O(2^n * n) due to storage of all possible substrings in the queue and set whereas for DFS 
// solution space complexity will be O(2^n * n) due to the recursion stack and the set storing the substrings.
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : followed the code discussed in the class


// Your code here along with comments explaining your approach
// In this approach we are using BFS to explore all possible states by removing one parenthesis at a time to find the valid strig. Once a valid string is found we 
// are stopping the search for the shorter strings. Optionally we can use DFS to explore all possibilities and track the longest valid string. To check the 
// correctness of string, we are calculating number of opening brackets and number of closing brackets and if the count becomes 0 we are stating that the given 
// string is valid.

class Solution {

    List<String> result;
    HashSet<String> set;

   public List<String> removeInvalidParentheses(String s) {

       // BFS Solution

       Queue<String> queue = new LinkedList<>();
       List<String> result = new ArrayList<>();
       HashSet<String> set = new HashSet<>();

       queue.add(s);
       set.add(s);

       while(!queue.isEmpty()) {
           int size = queue.size();
           boolean flag = false;

           for(int i = 0; i < size; i++) {
               String curr = queue.poll();

               if(isValidString(curr)) {
                   flag = true;
                   result.add(curr);
               }

               if(!flag) {
                   for(int j = 0; j < curr.length(); j++) {
                       String subString = curr.substring(0, j) + curr.substring(j+1);
                       
                       if(!set.contains(subString)) {
                           queue.add(subString);
                           set.add(subString);
                       }
                   }
               }
           }

           if(flag) {
               break;
           }
       }




       // DFS Solution
       // this.result = new ArrayList<>();
       // this.set = new HashSet<>();
       // dfs(s);

       
       return result;
       
   }

   int max;

   private void dfs(String s) {
       // base 
       if(s.length() < max || set.contains(s)) {
           return;
       } 

       if(isValidString(s)) {
           if(s.length() > max) {
               max = Math.max(max, s.length());
               result = new ArrayList<>();
           }
           result.add(s);
       }

       set.add(s);

       //logic
       for(int i = 0; i < s.length(); i++) {
           String subString = s.substring(0, i) + s.substring(i+1);
           dfs(subString);
       }
   }

   private boolean isValidString(String s) {
       int n = s.length();
       int count = 0;

       for(int i = 0; i < n; i++) {
           char c = s.charAt(i);

           if(c == '(') {
               count++;
           } else if(c == ')') {
               count --;
           }

           if(count < 0) {
               return false;
           }
       }

       return count == 0;
   }
}


// Time Complexity : For BFS solution it will be O(V+E) where V is number of vertices and E is number of edges since each node and edge will be visited exactly
// once. In DFS solution it will be O(V+E) for same reason.
// Space Complexity : For BFS solution it will be O(V) for queue and O(V) for map which will be O(V) overall space complexity where V is number of nodes. For DFS
// solution O(V) for recursion stack and O(V) for the map which will be O(V) overall space complexity.
// Did this code successfully run on Leetcode : yes 
// Any problem you faced while coding this : followed code discussed during the class


// Your code here along with comments explaining your approach
// In BFS approach we will use a queue to perform leve-order traversal and clone each node as we will encounter it. At the same time we will maintain a map to
// track visited nodes and their corresponding clones. For each node we will clone its neighbour and link them to the cloned node. In DFS approach, we will use 
// recursive depth-first traversal to clone the graph. We will maintain a map to track visited nodes and their corresponding clones. For each node we will clone
// its neighbours recursively and link them to the cloned node.

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {

        if(node == null) {
            return null;
        }


        // BFS Solution

        // Queue<Node> queue = new LinkedList<>();
        // HashMap<Node, Node> map = new HashMap<>();

        // Node copyNode = new Node(node.val);

        // queue.add(node);
        // map.put(node, copyNode);

        // while(!queue.isEmpty()) {
        //     Node curr = queue.poll();

        //     for(Node neighbour: curr.neighbors) {
        //         if(!map.containsKey(neighbour)) {
        //             queue.add(neighbour);
        //             Node deepCopy = new Node(neighbour.val);
        //             map.put(neighbour, deepCopy);
        //         }

        //         map.get(curr).neighbors.add(map.get(neighbour));
        //     }
        // }

        // return copyNode;




        // DFS Solution

        HashMap<Node, Node> map = new HashMap<>();

        dfs(node, map);

        return map.get(node);

    }

    private void dfs(Node node, HashMap<Node, Node> map) {
        // base
        if(map.containsKey(node)) {
            return;
        }

        // logic
        Node copyNode = new Node(node.val);
        map.put(node, copyNode);

        for(Node neighbour: node.neighbors) {
            if(!map.containsKey(neighbour)) {
                dfs(neighbour, map);
            }

            map.get(node).neighbors.add(map.get(neighbour));
        }
    }
}