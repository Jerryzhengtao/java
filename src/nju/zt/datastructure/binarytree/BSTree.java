package nju.zt.datastructure.binarytree;

import com.sun.deploy.security.WIExplorerUntrustedCertStore;

import javax.print.DocFlavor;
import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Parameter;

/**
 * @ClassName nju.zt.datastructure.binarytree.BSTree
 * @Description //TODO
 * @Author zt
 * @Date 2021/1/8 22:17
 * @Version 1.0
 **/
public class BSTree {
    private static int size = 0;
    private tnode root = null;
    private class tnode{
        private tnode right = null;
        private tnode left = null;
        int value ;
    }
    public BSTree(){
    }
    public void add(int i){
        tnode p = new tnode();//待插入的树节点
        p.value = i;
        if(root==null){
            //根节点不存在，则让p为根节点
            root = p;
            size++;
        }
        else
        {   //根节点存在，遍历树
            tnode current = root;
            while(true){
                //当前节点值和需要插入的值相同，则直接返回
                if(current.value==i) {
                    break;
                }
                //需要插入的值比当前节点小，向左子树找
                else if(i<current.value){
                    if(current.left==null) {
                        current.left = p;  //左子树为空则让p为左子树
                        size++;
                        break;   //插入完成 退出循环
                    }
                        current = current.left; //递归遍历

                }
                //需要插入的值比当前节点大，向右子树找
                else{
                    if(current.right==null){
                        current.right = p;  //右子树为空则让p为右子树
                        size++;
                        break;  //插入完成 退出循环
                    }
                        current = current.right; //递归遍历
                }
            }
        }
    }
    public String remove(int i){
        /**
         * 五种情况：
         * 删除节点无子节点
         * 删除节点只有左节点
         * 删除节点只有右节点
         * 被删除节点有两个节点且它自己是一个左节点
         * * 被删除节点有两个节点且它自己是一个右节点
         **/

        //先找到要被删除的节点
        tnode current = root;
        if(current==null){return "不存在此节点";}
        if(current.left==null&&current.right==null){

        }

        return null;

    }
    public tnode find(int i){
        tnode current = root;
        while(current!=null){
            if(i==current.value){
            return current;
            }
            else if(i<current.value)
            {
                current = current.left;
            }
            else{
                current = current.right;
            }
        }
        return null;
    }
    //前序遍历
    public String inOrder(){
        return getNode(root);
    }
    public String toString(){
        return "size:"+size()+" value:"+inOrder();
    }
    public int size(){
        return size;
    }
    private String getNode(tnode current){
       if(current == null){return null;}
       String[] s = new String[3];
       s[0] = getNode(current.left);
       s[1] = current.value+"";
       s[2] = getNode(current.right);
       String res = "";
       for(int i=0;i<3;i++){
           if(s[i]!=null){
               res = res + s[i]+" ";
           }
       }
       return res;
    }
}
