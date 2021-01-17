package nju.zt.sort;

/**
 * @ClassName SortTest
 * @Description //TODO
 * @Author zt
 * @Date 2021/1/17 23:41
 * @Version 1.0
 **/
public class SortTest {
    public static void main(String[] args) {
        int [] testArray = new int[]{12,2,3,531,34,12,2,1,-2,0,-3,4,3,6,9};
        BubbleSort bubbleSort = new BubbleSort(testArray);
        bubbleSort.sort();
        for(int i : testArray) {
            System.out.print(i+" ");
        }
    }
}
