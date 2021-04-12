package nju.zt.datastructure.tree;

import sun.util.locale.provider.AvailableLanguageTags;

import javax.swing.*;

/**
 * @ClassName AVLTree
 * //TODO
 * @Author zt
 * @Date 2021/3/6 21:46
 **/
public class AVLTree {

    private TreeNode root;

    public AVLTree(){

    }
    public AVLTree(int val) {
        this.root = new TreeNode(val);
    }
    public AVLTree(int[] a){
        for(int i=0;i<a.length;i++){
            insert(a[i]);
        }
    }

    public void insert(int val) {
        root = insert(val, root);
    }

    private TreeNode insert(int val, TreeNode root) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insert(val, root.left);
            if (getHeight(root.left) - getHeight(root.right) == 2) {
                if (val < root.left.val) {
                    root = roateRight(root);
                } else {
                    root = roateLeftRight(root);
                }
            }
        } else if (val > root.val) {
            root.right = insert(val, root.right);
            if (getHeight(root.right) - getHeight(root.left) == 2) {
                if (val > root.right.val) {
                    root = roateLeft(root);
                } else {
                    root = roateRightLeft(root);
                }
            }
        }
        root.height = Math.max(getHeight(root.left),getHeight(root.right))+1;
        return root;
    }

    private TreeNode roateLeft(TreeNode root) {
        //左旋
        //  x               y
        //     y   =>   x      p
        //   z   p        z
        TreeNode tmp = root.right;
        root.right = tmp.left;
        tmp.left = root;
        root.height = Math.max(getHeight(root.left),getHeight(root.right))+1;
        tmp.height = Math.max(getHeight(tmp.right),getHeight(root))+1;
        return tmp;
    }

    private TreeNode roateRight(TreeNode root) {
        //右旋
        //         x          y
        //      y      =>  z      x
        //   z     p            p
        // 即:
        // tmp = x.left
        // x.left = tmp.right
        // tmp.right = x

        TreeNode tmp = root.left;
        root.left = tmp.right;
        tmp.right = root;
        root.height = Math.max(getHeight(root.left),getHeight(root.right))+1;
        tmp.height = Math.max(getHeight(tmp.left),getHeight(root))+1;
        return tmp;

    }

    private TreeNode roateRightLeft(TreeNode root) {
        // 右左旋
        root.right = roateRight(root.right);
        return roateLeft(root);

    }

    private TreeNode roateLeftRight(TreeNode root) {
        //左右旋
        root.left = roateLeft(root.left);
        return roateRight(root);

    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }

    public void print(){
        PrintUtils.print(root);
    }

}
