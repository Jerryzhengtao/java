package nju.zt.datastructure;

import java.security.PublicKey;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @ClassName DBS
 * //TODO
 * @Author zt
 * @Date 2021/3/4 15:20
 **/
public class DBS
{
    static class Node {
        int val;
        Node left;
        Node right;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);

        Node node4 = new Node(1);
        Node node5 = new Node(1);
        Node node6 = new Node(1);
        Node node7 = new Node(1);


        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        dbs(node1);

    }

    public static void dbs(Node root) {

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int len = 1;
        int maxWidth = 0;
        while (!nodeQueue.isEmpty()) {
            int tmp = 0;
            System.out.println(len);
            for (int i = 0; i < len; i++) {
                Node pre = nodeQueue.remove();
                if (pre.left != null) {
                    nodeQueue.add(pre.left);
                    tmp++;
                }
                if (pre.right != null) {
                    nodeQueue.add(pre.right);
                    tmp++;
                }
            }
            maxWidth = Math.max(maxWidth, tmp);
            len = tmp;
        }
    }
}
