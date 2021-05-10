public class question {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static class allSolPair {
        TreeNode prev=null;
        TreeNode pred=null;
        TreeNode succ=null;

        int ceil=(int)1e8;
        int floor=-(int)1e8;
    }

    public static void allSolution(TreeNode node, int data, allSolPair pair){
        if(node==null) return;
        allSolution(node.left,data,pair);
        if(node.val!=key){
            if(node.val>key) ceil=Math.min(ceil,node.val);
            if(node.val<key) floor=Math.max(floor,node.val);
        }
        
        if(node.val==key && pair.pred==null) 
            pair.pred=pair.prev;
        
        if(pair.prev!=null && pair.prev.val==data && pair.succ==null)
            pair.succ=node;
        
        pair.prev=node;
        allSolution(node.right,data,pair);
    }

    public TreeNode inorderSuccessor(TreeNode node){
        if(node==null) return;
        if(node.right!=null){
            TreeNode cur=node.right;
            while(cur.left!=null) cur=cur.left;
            return cur;
        }
        else{
            while(node.par!=null){
                if(node.par.left==node) return node.par;
                node=node.parent;
            }
        }
    }

    public static void main(String[] args){
        return 0;
    }
}