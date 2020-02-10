package OA;

public class InterviewPrep4 extends OABaseClass {

    public InterviewPrep4() {
        
    }

    /*
    1. if p val is smaller than root, keep root as successor and then move left to find a closer val to p val

    2. if p val is larger than root, move right to get a larger value that would be larger than p val
    */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while(root != null) {
            if(p.val < root.val) {
                succ = root;
                root = root.left;
            }
            else {
                root = root.right;
            }
        }

        return succ;
    }

    /*
    1. If x.right exists, move to the right child and then keep searching for the left child iteratively.

    2. If x.right doesn't exist, keep moving up until seeing a node that has a bigger value than x.val
    */
    public TreeNode inorderSuccessor(TreeNode x) {
        if(x.right == null) {
            TreeNode result = x.parent;
            while( result != null && result.val < x.val) {
                result = result.parent;
            }
            return result;
        }
        TreeNode result = x.right;
        while(result.left != null) {
            result = result.left;
        }
        return result;
    }

    private class TreeNode{
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode parent;

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("%d", val);
        }
    }
}