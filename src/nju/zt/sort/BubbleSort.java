package nju.zt.sort;

/**
 * @ClassName BubbleSort
 * @Description //普通冒泡排序
 * @Author zt
 * @Date 2021/1/17 23:40
 * @Version 1.0
 **/
public class BubbleSort {
    private int[] arr;

    public BubbleSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        int length = arr.length;
    for(int i = 0;i<length-1;i++){
        for(int j = 0;j<length-1-i;j++)
        {
            if(arr[j]>arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    }
}
