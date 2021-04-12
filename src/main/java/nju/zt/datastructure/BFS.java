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
public class BFS {
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

        dfs(node1);

    }

    public static int dfs(Node root) {
        if (root == null) {
            return 0;
        }
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int width = 1;
        int maxWidth = 0;
        while (!nodeQueue.isEmpty()) {
            int tmpWidth = 0;
            for (int i = 0; i < width; i++) {
                Node pre = nodeQueue.remove();
                if (pre.left != null) {
                    nodeQueue.add(pre.left);
                    tmpWidth++;
                }
                if (pre.right != null) {
                    nodeQueue.add(pre.right);
                    tmpWidth++;
                }
            }
            maxWidth = Math.max(maxWidth, tmpWidth);
            width = tmpWidth;
        }
        return maxWidth;
    }
}

