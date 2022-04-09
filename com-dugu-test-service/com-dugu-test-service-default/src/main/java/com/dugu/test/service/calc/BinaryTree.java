package com.dugu.test.service.calc;


import java.util.ArrayList;
import java.util.List;

/**
 * @Author cihun
 * @Date 2022-04-05 9:25 下午
 */
public class BinaryTree {


    public List<Integer> preOrder(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        dfs(preOrderList, root);
        return preOrderList;
    }

    public void dfs(List<Integer> list, TreeNode root) {
        if (root != null) {
            list.add(root.val);
            dfs(list, root.left);
            dfs(list, root.right);
        }
    }

    public List<Integer> zx(TreeNode root) {
        List<Integer> preOrderList = new ArrayList<>();
        zx(preOrderList, root);
        return preOrderList;
    }


    public void zx(List<Integer> list, TreeNode root) {
        if (root != null) {
            dfs(list, root.left);
            list.add(root.val);
            dfs(list, root.right);
        }
    }


    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }


}
