class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        // Both nodes are null
        if (left == null && right == null) {
            return true;
        }

        // Only one node is null
        if (left == null || right == null) {
            return false;
        }

        // Check values and opposite subtrees
        return (left.val == right.val)
                && isMirror(left.left, right.right)
                && isMirror(left.right, right.left);
    }
}