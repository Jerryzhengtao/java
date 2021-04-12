package nju.zt.datastructure.tree;

/**
 * @ClassName TreeNode
 * //TODO
 * @Author zt
 * @Date 2021/3/6 23:13
 **/
public class TreeNode {
    public int val;
    public TreeNode parent;
    public TreeNode left;
    public TreeNode right;
    public int height = 0;
    TreeNode(int val) {
        this.val = val;
    }
}