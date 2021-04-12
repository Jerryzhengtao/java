// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int sum=0;
        int len = in.nextInt();
        int[] arr = new int[len];
        int index=0;
        while (index<len) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            arr[index++]=in.nextInt();
        }
        int target = in.nextInt();

        int[][] res = new int[len][len];
        for(int i=0;i<len;i++){
            res[i][i] = arr[i];
        }
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(j>i){
                    res[i][j] = res[i][j-1]|arr[j];
                }
            }
        }

        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(res[i][j]<=target){
                   sum++;
                }
            }
        }
        System.out.println(sum);
    }
}
//5
//7 6 6 5 15
//14
//10

// 4
// 70 15 21 96
//45
//3