package range_sum_of_bst;

public class Solution {
    /*给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

        二叉搜索树保证具有唯一的值。



        示例 1：

        输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
        输出：32
        示例 2：

        输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
        输出：23
*/


    public static int rangeSumBST(TreeNode root, int L, int R) {
        int num = 0;
        if(root == null){
            return 0;
        }else if(root.right == null && root.left == null){
            if(root.val>=L && root.val<= R){
                return root.val;
            }else{
                return 0;
            }
        }
        if(root.val>=L && root.val<= R){
            num+=root.val;
        }
        num+=rangeSumBST(root.left,L,R);
        num+=rangeSumBST(root.right,L,R);
        return num;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(18,null,null);
        TreeNode root2 = new TreeNode(15,null,root1);
        TreeNode root3 = new TreeNode(7,null,null);
        TreeNode root4 = new TreeNode(3,null,null);
        TreeNode root5 = new TreeNode(5,root4,root3);
        TreeNode root = new TreeNode(10,root5,root2);
        System.out.println(rangeSumBST(root,7,15));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
