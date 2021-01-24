package nju.zt.sort;


import sun.awt.image.BufImgVolatileSurfaceManager;

/**
 * @ClassName Sort
 * @Description //将多个排序类整合到Sort类中
 * @Author zt
 * @Date 2021/1/20 21:56
 * @Version 1.0
 **/
public class Sort {
    /**
     * @return void
     * @MethodName bubbleSort
     * @Description //冒泡排序
     * 时间复杂度：最好O(n),最坏O(n^2),平均O(n^2)
     * 空间复杂度O(1) ，稳定排序
     * @Date 2021/1/20 19:22
     * @Param [arr]
     **/
    public static <T extends Comparable<? super T>> void bubbleSort(T[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    /** T temp = arr[j];
                     arr[j] = arr[j + 1];
                     arr[j + 1] = temp;
                     **/
                }
            }
        }
    }

    /**
     * @return void
     * @MethodName selectionSort
     * @Description //选择排序
     * 时间复杂度：最好O(n^2),最坏O(n^2),平均O(n^2)
     * 空间复杂度O(1) ，不稳定排序
     * @Date 2021/1/20 19:28
     * @Param [arr]
     **/
    public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j; //找到最小的数的索引
                }
            }
            swap(arr, i, minIndex);
            /** T temp = arr[minIndex];
             arr[minIndex] = arr[i];
             arr[i] = temp;**/
        }
    }

    /**
     * @return void
     * @MethodName insertionSort
     * @Description //插入排序
     * 时间复杂度：最好O(n),最坏O(n^2),平均O(n^2)
     * 空间复杂度O(1) ，稳定排序
     * @Date 2021/1/20 19:36
     * @Param [arr]
     **/
    public static <T extends Comparable<? super T>> void insertionSort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            T current = arr[i];
            int index = i;
            while (index > 0 && arr[index - 1].compareTo(current) > 0) {
                arr[index] = arr[index - 1];
                index--;
            }
            arr[index] = current;
        }
    }

    /**
     * @return void
     * @MethodName shellSort
     * @Description //希尔排序
     * 时间复杂度：o(n^2) 亚二次
     * 空间复杂度O(1) ，不稳定排序
     * @Date 2021/1/20 19:59
     * @Param [arr]
     **/
    public static <T extends Comparable<? super T>> void shellSort(T[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < arr.length; j++) {
                T current = arr[j];
                int index = j;
                while (index >= gap && arr[index - gap].compareTo(current) > 0) {
                    arr[index] = arr[index - gap];
                    index -= gap;
                }
                arr[index] = current;
            }
        }
    }

    /**
     * @return void
     * @MethodName mergeSort
     * @Description //归并排序
     * 时间复杂度：最好O(nlogn),最坏O(nlogn),平均O(nlogn)
     * 空间复杂度O(n) ，稳定排序
     * @Date 2021/1/20 20:03
     * @Param [arr]
     **/
    public static <T extends Comparable<? super T>> void mergeSort(T[] arr) {
        //创建与原数组相同长度的数组
        T[] temp = (T[]) new Comparable[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(T[] arr, T[] temp, int left, int right) {
        if (left < right) {
            //从中间将数组分成两半
            int mid = (left + right) >> 1;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            //将两个数组重新合并
            merge(arr, temp, left, mid + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> void merge(T[] arr, T[] temp,
                                                                int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        //对比左右两个数组并将较小的数先放到辅助数组
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos].compareTo(arr[rightPos]) < 0) {
                temp[tmpPos++] = arr[leftPos++];
            } else {
                temp[tmpPos++] = arr[rightPos++];
            }
        }
        //把左边数组剩下的原数放到辅助数组
        while (leftPos <= leftEnd) {
            temp[tmpPos++] = arr[leftPos++];
        }
        //把右边数组剩下的原数放到辅助数组
        while (rightPos <= rightEnd) {
            temp[tmpPos++] = arr[rightPos++];
        }
        //把辅助数组复制到原数组
        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = temp[rightEnd];
        }
    }

    /**
     * @return void
     * @MethodName quickSort
     * @Description //快速排序
     * @Date 2021/1/23 22:10
     * @Param [arr]
     **/
    public static <T extends Comparable<? super T>> void quickSort(T[] arr) {
        if (arr.length > 1) {
            quickSort(arr, 0, arr.length - 1);
        }
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partion(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static <T extends Comparable<? super T>> int partion(T[] arr, int left, int right) {
        T pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right].compareTo(pivot) >= 0) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left].compareTo(pivot) <= 0) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    private static <T> void swap(T[] arr, int aIndex, int bIndex) {
        T tmp = arr[aIndex];
        arr[aIndex] = arr[bIndex];
        arr[bIndex] = tmp;
    }

    /**
     * @return void
     * @MethodName countingSort
     * @Description //计数排序，只能对整数排序
     * @Date 2021/1/24 22:49
     * @Param [arr]
     **/
    public static void countingSort(int[] arr) {
        if (arr.length >= 2) {
            int min = arr[0];
            int max = arr[0];
            for (int i = 0; i < arr.length; i++) {
                min = Math.min(min, arr[i]);
                max = Math.max(max, arr[i]);
            }
            int center = -min;
            int[] bucket = new int[max - min + 1];
            for (int i = 0; i < arr.length; i++) {
                bucket[arr[i] + center]++;
            }
            int index = 0;
            for (int i = 0; i < bucket.length; i++) {
                while (bucket[i] != 0) {
                    arr[index++] = i - center;
                    bucket[i]--;
                }
            }
        }
    }
    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

}
