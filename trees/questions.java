import java.util.*;

public class questions {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        if (key > root.val)
            root.right = deleteNode(root.right, key);
        else if (key < root.val)
            root.left = deleteNode(root.left, key);
        else {
            root = remove(root);
        }
        return root;
    }

    public TreeNode remove(TreeNode node) {
        if (node.left == null && node.right == null)
            return null;
        else if (node.left == null)
            return node.right;
        else if (node.right == null)
            return node.left;
        else {
            TreeNode rt = node.right;
            while (rt.left != null)
                rt = rt.left;
            rt.left = node.left;
            return node.right;
        }
    }

    class Node {
        int data;
        Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
    }

    static class ViewClass {
        Node node = null;
        int hlevel = 0;

        ViewClass(Node node, int hlevel) {
            this.node = node;
            this.hlevel = hlevel;
        }
    }

    static void topView(Node root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<ViewClass> que = new LinkedList<>();
        que.addLast(new ViewClass(root, 0));
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                ViewClass temp = que.removeFirst();
                // for toporder
                map.putIfAbsent(temp.hlevel, temp.node.data);
                // for bottomview
                // map.put(temp.hlevel, temp.node.data);
                if (temp.node.left != null)
                    que.addLast(new ViewClass(temp.node.left, temp.hlevel - 1));
                if (temp.node.right != null)
                    que.addLast(new ViewClass(temp.node.right, temp.hlevel + 1));
            }
        }
        int leftmost = Integer.MAX_VALUE;
        int rightmost = Integer.MIN_VALUE;
        for (Integer key : map.keySet()) {
            leftmost = Math.min(key, leftmost);
            rightmost = Math.max(key, rightmost);
        }
        for (int key = leftmost; key <= rightmost; key++)
            System.out.print(map.get(key) + " ");

    }

    // ==================leetcode 987=====================
    public class VerticalOrder {
        TreeNode node = null;
        int vlevel = 0;

        VerticalOrder(TreeNode ho, int lvl) {
            this.node = ho;
            this.vlevel = lvl;
        }
    }

    public class mapNode {
        int nval = 0;
        int hlevel = 0;

        mapNode(int nval, int hlevel) {
            this.nval = nval;
            this.hlevel = hlevel;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<Integer, ArrayList<mapNode>> map = new HashMap<>();
        LinkedList<VerticalOrder> que = new LinkedList<>();
        que.add(new VerticalOrder(root, 0));
        int level = 0;
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                VerticalOrder temp = que.removeFirst();
                map.putIfAbsent(temp.vlevel, new ArrayList<>());
                ArrayList<mapNode> lst = map.get(temp.vlevel);
                lst.add(new mapNode(temp.node.val, level));
                map.put(temp.vlevel, lst);
                if (temp.node.left != null)
                    que.addLast(new VerticalOrder(temp.node.left, temp.vlevel - 1));

                if (temp.node.right != null)
                    que.addLast(new VerticalOrder(temp.node.right, temp.vlevel + 1));

            }
            level++;
        }
        List<List<Integer>> fans = new ArrayList<>();
        int minval = 0;
        int maxval = 0;
        for (Integer key : map.keySet()) {
            minval = Math.min(minval, key);
            maxval = Math.max(maxval, key);
        }
        for (int key = minval; key <= maxval; key++) {
            ArrayList<mapNode> temp = map.get(key);
            Collections.sort(temp, (mapNode a, mapNode b) -> {
                if (a.hlevel == b.hlevel)
                    return a.nval - b.nval;
                return a.hlevel - b.hlevel;
            });
            ArrayList<Integer> lst = new ArrayList<>();
            for (mapNode e : temp)
                lst.add(e.nval);
            fans.add(lst);
        }
        return fans;
    }

    // ======================leetcode 968====================
    // 1-> leaf
    // 2-> non leaf with camera
    // 3->non leaf withot camera but watched
    public int minCameraCover(TreeNode root) {
        count = 0;
        int op = minCameraCover_(root);
        return op == 1 ? count + 1 : count;
    }

    int count = 0;

    public int minCameraCover_(TreeNode root) {
        if (root == null)
            return 3;
        int left = minCameraCover_(root.left);
        int right = minCameraCover_(root.right);
        if (left == 1 || right == 1) {
            count++;
            return 2;
        } else if (left == 2 || right == 2)
            return 3;
        return 1;
    }

    // boundary traversal

    ArrayList<Integer> printBoundary(Node node) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(node.data);
        leftside(node.left, ans);
        bottomView(node, ans);
        rightside(node.right, ans);
        return ans;
    }

    void leftside(Node node, ArrayList<Integer> ans) {
        if (node == null)
            return;
        if (node.left == null && node.right == null)
            return;
        ans.add(node.data);
        if (node.left != null)
            leftside(node.left, ans);
        else if (node.right != null)
            leftside(node.right, ans);
    }

    void rightside(Node node, ArrayList<Integer> ans) {
        if (node == null)
            return;
        if (node.left == null && node.right == null)
            return;
        if (node.right != null)
            rightside(node.right, ans);
        else if (node.left != null)
            rightside(node.left, ans);
        ans.add(node.data);
    }

    void bottomView(Node node, ArrayList<Integer> ans) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            ans.add(node.data);
            return;
        }
        if (node.left != null)
            bottomView(node.left, ans);
        if (node.right != null)
            bottomView(node.right, ans);

    }

    // lca of a tree

    Node lca(Node root, int n1, int n2) {
        if (root == null)
            return null;
        if (root.data == n1 || root.data == n2)
            return root;
        Node left = lca(root.left, n1, n2);
        Node right = lca(root.right, n1, n2);
        if (left == null && right == null)
            return null;
        else if (left == null && right != null) {
            if (root.data == n1 || root.data == n2)
                return root;
            return right;
        } else if (left != null && right == null) {
            if (root.data == n1 || root.data == n2)
                return root;
            return left;
        }
        return root;
    }


    
}