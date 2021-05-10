import java.util.Random;
public class avl {
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        int bal=0;
        int height=0;
        
        TreeNode(int val){
            this.val=val;
        }
    }

    public static void updateBalanceAndHeight(TreeNode node){       //O(1)
        if(node==null) return;
        int lh=-1;
        int rh=-1;

        if(node.left!=null) lh=node.left.height;
        if(node.right!=null) rh=node.right.height;

        node.bal=lh-rh;
        node.height=Math.max(lh,rh)+1;
    }

    public static TreeNode rightRotation(TreeNode A){       //O(1)
        TreeNode B=A.left;
        TreeNode bkaright=B.right;

        A.left=bkaright;
        B.right=A;

        updateBalanceAndHeight(A);
        updateBalanceAndHeight(B);

        return B;
    }

    public static TreeNode leftRotation(TreeNode A){        //O(1)
        TreeNode B=A.right;
        TreeNode bkaleft=B.left;

        A.right=bkaleft;
        B.left=A;

        updateBalanceAndHeight(A);
        updateBalanceAndHeight(B);

        return B;
    }

    
    public static TreeNode getRotation(TreeNode node){      //O(1)
        updateBalanceAndHeight(node);

        if(node.bal==2){    //ll    lr

            if(node.left.bal==1){   //ll
                return rightRotation(node);

            }else{      //lr
                node.left=leftRotation(node.left);
                return rightRotation(node);
            }

        }else if(node.bal==-2){ //rr    rl
            
            if(node.left.bal==-1){   //rr
                return leftRotation(node);

            }else{      //rl
                node.right=rightRotation(node.right);
                return leftRotation(node);

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

    public static void display(TreeNode root){
        if(root==null) return;
        StringBuilder sb=new StringBuilder();
        sb.append(root.left==null ? "." : root.left.val);
        sb.append(" -> " + root.val + "(" + root.bal + ")" + " <- ");
        sb.append(root.right==null ? "." : root.right.val);
        System.out.println(sb.toString());

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args){
        TreeNode root=null;
        for(int i=1;i<=15;i++){
            root=insertIntoBST(root,i*10);
            display(root);
            System.out.println("===================================");
        }
    }
}