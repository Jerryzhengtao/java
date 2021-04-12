package nju.zt.sort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @ClassName HeapSort
 * //TODO
 * @Author zt
 * @Date 2021/3/5 1:51
 **/
public class HeapSort {
    public static void main(String[] args) {
        int nums[] = {1, 4, 87, 23, 2, 65, 1, -34, 0};
        PriorityQueue<Integer> queue = new PriorityQueue<>();
       
        for (int i = 0; i <nums.length ; i++) {
            queue.add(nums[i]);

        }
      //  heapsort(nums);
        for (int x : nums) {
            System.out.println(queue.poll()+" ");
        }
    }

    private static void heapsort(int[] nums) {
        //创建堆
        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        //将头尾交换，然后重新调整堆
        for (int i = nums.length-1; i>0; i--) {
            //头尾交换
            int tmp = nums[i];
            nums[i] = nums[0];
            nums[0] = tmp;
            //调整堆
            adjustHeap(nums, 0, i);
        }
    }

    private static void adjustHeap(int[] nums, int parent, int length) {
        //从父节点开始，保存父节点的值
        int tmp = nums[parent];
        int leftChild = 2 * parent + 1;

        //这里注意，由于堆是完全二叉树，所以如果一个结点没有左孩子，
        // 那么就不可能有右孩子，于是只需要判断左孩子
        while (leftChild < length) {
            //找出左孩子和右孩子较大的一个
            //如果较大的一个比父节点大，那就交换
            int rightChild = leftChild + 1;
            //比较左右孩子的大小找出大的一个
            //leftChild指向了大的一个
            if (rightChild < length && nums[leftChild] < nums[rightChild]) {
                leftChild++;
            }
            //判断孩子是不是比父节点小
            if(tmp>=nums[leftChild])
            {
                break;
            }
            //走到这里说明孩子比父节点大，于是交换孩子和父节点的值，同时将孩子变成父节点，调整堆
            nums[parent] = nums[leftChild];
            parent = leftChild;
            leftChild = 2*leftChild+1;

        }
        //将保存的原始父节点值还原（还到最后没有孩子的结点）
        nums[parent] = tmp;
    }

}
