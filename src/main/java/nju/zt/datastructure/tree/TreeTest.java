package nju.zt.datastructure.tree;

/**
 * @ClassName TreeTest
 * //TODO
 * @Author zt
 * @Date 2021/3/5 15:29
 **/
public class TreeTest {

    public static void main(String[] args) {
        int[] nums = {50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100};
        BSTree BSTree = new BSTree(nums);
        AVLTree avlTree = new AVLTree();
        for(int i=0;i<nums.length;i++) {
            avlTree.insert(nums[i]);
            avlTree.print();
            System.out.println();
        }
    }


}
