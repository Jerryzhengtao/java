package nju.zt.datastructure.tree;

import nju.zt.file.Test;
import sun.util.locale.provider.AvailableLanguageTags;

/**
 * @ClassName TreeTest
 * //TODO
 * @Author zt
 * @Date 2021/3/5 15:29
 **/
public class TreeTest {

    public static void main(String[] args) {
        int[] nums = {50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100};
        BinarySearchTree binarySearchTree = new BinarySearchTree(nums);
        AVLTree avlTree = new AVLTree();
        for(int i=0;i<nums.length;i++) {
            avlTree.insert(nums[i]);
            avlTree.print();
            System.out.println();
        }
    }


}
