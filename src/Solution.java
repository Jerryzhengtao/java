import nju.zt.designpattern.SingletonHungry;
import nju.zt.designpattern.SingletonLazy;
import nju.zt.sort.Sort;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        String[] s = {"ab", "a"};

        Sort.insertionSort(s);
        for (String ss : s)
            System.out.println(ss);


    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs.length; i++) {
            String current = strs[i];
            int index = i;
            while (index > 0 && strs[index - 1].compareTo(current) > 0) {
                strs[index] = strs[index - 1];
                index--;
            }
            strs[index] = current;
        }
        String ans = strs[0];
        for(int i =0;i<ans.length();i++){
            if(ans.charAt(i)!=strs[strs.length-1].charAt(i)){
                return ans.substring(0,i);
            }
        }
        return ans;


}


}