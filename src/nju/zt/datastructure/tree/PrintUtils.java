package nju.zt.datastructure.tree;

/**
 * @ClassName PrintUtils
 * //TODO
 * @Author zt
 * @Date 2021/3/5 18:32
 **/
public class PrintUtils {
    public static final String  PREFIX_BRANCH = "├";//树枝
    public static final String  PREFIX_TRUNK  = "│ ";//树干
    public static final String  PREFIX_LEAF   = "└";//叶子
    public static final String  PREFIX_EMP    = "  ";//空
    public static final String  PREFIX_LEFT   = "─L─";//左
    public static final String  PREFIX_RIGHT  = "─R─";//右
    private static  String res="";



    private static boolean hasChild(TreeNode node){
        return node.left != null || node.right != null;
    }

    public static void print(TreeNode root){
        if(root != null){
            System.out.println(root.val);
            res+= root.val+"\n";
            print(root, "");
        }
    }

    private   static void print(TreeNode node, String prefix){
        if(prefix == null){
            prefix = "";
        } else {
            prefix = prefix.replace(PREFIX_BRANCH, PREFIX_TRUNK);
            prefix = prefix.replace(PREFIX_LEAF, PREFIX_EMP);
        }
        if(hasChild(node)){
            if(node.right != null){
                System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGHT + node.right.val);
                res+= prefix + PREFIX_BRANCH + PREFIX_RIGHT + node.right.val+"\n";
                if(hasChild(node.right)){
                    print(node.right, prefix + PREFIX_BRANCH);
                }
            } else {
                System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGHT);
                res+=prefix + PREFIX_BRANCH + PREFIX_RIGHT+"\n";
            }

            if(node.left != null){
                System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT + node.left.val);
                res+=prefix + PREFIX_LEAF + PREFIX_LEFT + node.left.val+"\n";
                if(hasChild(node.left)){
                    print(node.left, prefix + PREFIX_LEAF);
                }
            } else {
                System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT);
                res+=prefix + PREFIX_LEAF + PREFIX_LEFT+"\n";
            }
        }
    }
}
