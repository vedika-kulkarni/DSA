
import java.util.*;

class Solution {
    List<Integer>[] tree;
    List<Integer>[] newTree;
    int[] answer;
    int[] last;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        tree = new ArrayList[n];
        newTree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            newTree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[parent[i]].add(i);
        }

        last = new int[26];
        Arrays.fill(last, -1);

        int[] newParent = parent.clone();

        dfs1(0, parent, newParent, s);

        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        answer = new int[n];

        dfs2(0);

        return answer;
    }

    private void dfs1(int node, int[] parent,
                      int[] newParent, String s) {

        int c = s.charAt(node) - 'a';

        int previous = last[c];

        if (node != 0 && previous != -1) {
            newParent[node] = previous;
        }

        last[c] = node;

        for (int child : tree[node]) {
            dfs1(child, parent, newParent, s);
        }

        last[c] = previous;
    }

    private int dfs2(int node) {
        int size = 1;

        for (int child : newTree[node]) {
            size += dfs2(child);
        }

        answer[node] = size;

        return size;
    }
}
