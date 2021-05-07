public class avl {
    
    public static class TreeNode {
        int val;
        TreeNode left=null;
        TreeNode right=null;
        
        int bal=0;
        int height=-1;
        
        TreeNode(int val){
            this.val=val;
        }
    }

    public static void updateBalanceAndHeight(TreeNode node){
        if(node==null) return;
        int lh=-1;
        int rh=-1;

        if(node.left!=null) lh=node.left.height;
        if(node.right!=null) rh=node.right.height;

        node.bal=lh-rh;
        node.height=Math.max(lh,rh)+1;
    }

    
    public static TreeNode getRotation(TreeNode node){
        updateBalanceAndHeight(node);

        if(node.bal==2){    //ll    lr

            if(node.left.bal==1){   //ll

            }else{      //lr

            }

        }else if(node.bal==-2){ //rr    rl
            
            if(node.left.bal==-1){   //rr

            }else{      //rl

            }
        
        }

        return node;
    }

    //Basic BST====================================================

    public static TreeNode insertIntoBST(TreeNode root, int val){
        if(root==null) return new TreeNode(val);

        if(root.val<val){
            root.right=insertIntoBST(root.right,val);
        }else{
            root.left=insertIntoBST(root.left,val);
        }

        return getRotation(root);
    }
    
    public static int maximumEle(TreeNode root){
        TreeNode cur=root;
        while(cur.right!=null) cur=cur.right;
        return cur.val;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;

        if(root.val>key){
            root.left=deleteNode(root.left,key);
        }else if(root.val<key){
            root.right=deleteNode(root.right,key);
        }else{
            if (root.left == null || root.right == null)
                return root.left != null ? root.left : root.right;

            int maxValue = maximumEle(root.left);
            root.val = maxValue;

            root.left = deleteNode(root.left, maxValue);
        }

        return getRotation(root);
    }

    public static void main(String[] args){

    }
}