
public class l002BST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void predSuccInBST(TreeNode node, int data) {

        TreeNode curr = node;
        TreeNode pred = null;
        TreeNode succ = null;
        boolean isDataPresent = false;

        while (curr != null) {

            if (curr.val == data) {
                isDataPresent = true;
                if (curr.left != null) {
                    pred = curr.left;
                    while (pred.right != null)
                        pred = pred.right;
                }

                if (curr.right != null) {
                    succ = curr.right;
                    while (succ.left != null)
                        succ = succ.left;
                }

                break;
            } else if (curr.val < data) {
                pred = curr;
                curr = curr.right;
            } else {
                succ = curr;
                curr = curr.left;
            }
        }
    }


    public static void main(String[] args){

    }
}