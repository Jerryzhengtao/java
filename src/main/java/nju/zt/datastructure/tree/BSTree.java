package nju.zt.datastructure.tree;

import java.util.Stack;

/**
 * @ClassName BTree
 * //TODO
 * @Author zt
 * @Date 2021/3/5 0:39
 **/
public class BSTree {
    private TreeNode root;
    //
    public BSTree(int i) {
        this.root = new TreeNode(i);
    }

    public BSTree(int[] a) {
        createTree(a);
    }

    private void createTree(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    public boolean insert(int i) {
        //true 表示插入成功，false表示 已经存在
        TreeNode p = new TreeNode(i);//待插入的结点

        if (root == null) {
            root = p;
            return true;
        } else {
            TreeNode parent;
            TreeNode current = root;
            while (true) {
                parent = current;
                if (i < current.val) {
                    current = current.left; //小于当前节点那么从左子树找
                    if (current == null) {
                        parent.left = p; //如果左子树为空，那么插入节点成为左子树
                        p.parent = parent;
                        return true;
                    }
                } else if (i > current.val) {
                    current = current.right;
                    if (current == null) {
                        parent.right = p; //右子树为空那么当前节点为右子树
                        p.parent = parent;
                        return true;
                    }

                } else {
                    return false; //相等时不用插入
                }
            }
        }
    }

    public boolean find(int key) {
        TreeNode current = root;
        while (current != null) {
            if (key < current.val) {
                current = current.left;
            } else if (key > current.val) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    private TreeNode findNode(int key) {

        TreeNode current = root;
        while (current != null) {
            if (key < current.val) {
                current = current.left;
            } else if (key > current.val) {
                current = current.right;
            } else {
                return current;
            }
        }


        return null;
    }

    private TreeNode findSuccessor(TreeNode node) {
        if (node.right != null) {
            TreeNode tmp = node.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp;
        }

        TreeNode t = node.parent;
        while ((t != null) && (node == t.right)) {
            node = t;
            t = t.parent;
        }
        return t;
    }

    private void delete(TreeNode node) {
        if (node.left != null && node.right != null) {
            TreeNode successor = findSuccessor(node);
            node.val = successor.val;
            node = successor;
        }
        TreeNode child;
        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }

        if (child != null) {
            child.parent = node.parent;
        }

        if (node.parent == null) {
            root = child;
        } else if (node == node.parent.left) {
            node.parent.left = child;
        } else {
            node.parent.right = child;
        }

    }

    public boolean delete(int key) {
        TreeNode node = findNode(key);
        if (node != null) {
            delete(node);
            return true;
        } else {
            return false;
        }
    }


    public void preOrder() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                System.out.print(current.val + " ");
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop().right;
            }

        }
        System.out.println();

    }

    private void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    public void postOrder() {
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                output.push(current);
                stack.push(current);
                current = current.right;
            } else {
                current = stack.pop().left;
            }
        }
        while (!output.isEmpty()) {
            System.out.print(output.pop().val + " ");
        }
    }

    public void print(){
        PrintUtils.print(root);
    }

}
