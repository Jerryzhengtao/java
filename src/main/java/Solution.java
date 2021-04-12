import javax.print.attribute.standard.Finishings;
import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import java.util.Scanner;

public class Solution {
   static ArrayList<String> res = new ArrayList();
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generateParenthesis(1);
        for(String s:res){
            System.out.println(s);
        }
    }
    public List<String> generateParenthesis(int n) {
        backtrace(new StringBuilder(),0,0,n);
        return res;
    }
    public void backtrace(StringBuilder s,int left,int right,int n){
        if(left+right==n*2){
            res.add(s.toString());
        }
        else{
            if(left<n){
                s.append("(");
                backtrace(s,left+1,right,n);
                s.deleteCharAt(s.length()-1);
            }
            if(left!=0&&right<left){
                s.append(")");
                backtrace(s,left,right+1,n);
                s.deleteCharAt(s.length()-1);
            }
        }
    }
}

