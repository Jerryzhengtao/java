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
        Integer[] testArray = new Integer[]{1, 213, 12, 5234, 652, 3, 2, 31, 232, 57, 32, 23, 0, 2, 3, -4, 5};
        int[] testArray1 = new int[]{1, 213, 12, 5234, 652, 3, 2, 31, 232, 57, 32, 23, 0, 2, 3, -4, 5};
        System.out.println("-4 0 1 2 2 3 3 5 12 23 31 32 57 213 232 652 5234 ");
        //bubbleSort(testArray);
        //selectionSort(testArray);
        //insertionSort(testArray);
        //shellSort(testArray);
        Sort.mergeSort(testArray);
        for (Integer i : testArray) {
            System.out.print(i + " ");
        }
    }


}
