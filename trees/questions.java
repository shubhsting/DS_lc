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

    // ====================leetcode 297==================

    public String serialize(TreeNode root) {
        if (root == null)
            return "#,";
        String str = root.val + ",";
        str += serialize(root.left);

        str += serialize(root.right);
        return str;
    }

    int idx = 0;

    public TreeNode deserialize(String data) {

        String[] arr = data.split(",");

        return dese(arr);
    }

    public TreeNode dese(String[] arr) {
        if (idx == arr.length) {
            return null;
        }
        String op = arr[idx];
        if (op.charAt(0) == '#') {
            idx++;
            return null;
        }
        int val = 0;
        boolean flag = false;
        for (int i = 0; i < op.length(); i++) {
            char ch = op.charAt(i);
            if (ch == '-')
                flag = true;
            else {
                val = (val * 10) + (ch - '0');
            }
        }
        if (flag)
            val = -val;

        TreeNode root = new TreeNode(val);
        idx++;
        root.left = dese(arr);
        root.right = dese(arr);
        return root;
    }

    public TreeNode flatten_(TreeNode root) {
        if (root == null)
            return null;
        TreeNode left = flatten_(root.left);
        TreeNode right = flatten_(root.right);
        if (left == null && right == null)
            return root;
        if (left == null && right != null)
            return right;
        if (left != null && right == null) {
            root.right = root.left;
            root.left = null;
            return left;
        }
        TreeNode temp = root.right;
        root.right = root.left;
        left.right = temp;
        root.left = null;
        return right;
    }
    // construct tree from post and inorder

    Node buildTree(int inorder[], int postorder[], int n) {
        map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        in = inorder;
        post = postorder;
        return built(0, post.length - 1, 0, in.length - 1);
    }

    static int[] in;
    static int[] post;
    static HashMap<Integer, Integer> map;

    public static Node built(int psi, int pei, int isi, int iei) {
        if (psi > pei || isi > iei)
            return null;
        Node root = new Node(post[pei]);
        int size = map.get(post[pei]) - isi;
        root.left = built(psi, psi + size - 1, isi, isi + size - 1);
        root.right = built(psi + size, pei - 1, isi + size + 1, iei);
        return root;
    }

    // remove half nodes
    public static Node RemoveHalfNodes(Node root) {
        if (root == null)
            return null;
        root.left = RemoveHalfNodes(root.left);
        root.right = RemoveHalfNodes(root.right);
        if (root.left == null && root.right == null)
            return root;
        if (root.left == null || root.right == null)
            return root.left == null ? root.right : root.left;
        return root;
    }

    static int idx = 0;

    Node constructTree(int n, int pre[], char preLN[]) {
        idx = 0;
        return constructTree_(n, pre, preLN);
    }

    Node constructTree_(int n, int pre[], char preLN[]) {
        if (idx >= pre.length) {
            return null;
        }
        if (preLN[idx] == 'L') {
            Node root = new Node(pre[idx]);
            idx++;
            return root;
        }
        Node root = new Node(pre[idx]);
        idx++;
        root.left = constructTree_(n, pre, preLN);
        root.right = constructTree_(n, pre, preLN);
        return root;
    }

    // morristree traversal

    public int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return 0;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                k--;
                if (k == 0)
                    return curr.val;
                curr = curr.right;
            } else {
                TreeNode prev = findrightmostmost(curr);
                if (prev.right != null) {
                    prev.right = null;
                    k--;
                    if (k == 0)
                        return curr.val;
                    curr = curr.right;
                } else {
                    prev.right = curr;
                    curr = curr.left;
                }
            }
        }
        return 0;
    }

    public TreeNode findrightmostmost(TreeNode root) {
        TreeNode curr = root.left;
        while (curr.right != null && curr.right != root)
            curr = curr.right;
        return curr;
    }

    // 239 leetcode
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % k == 0)
                pre[i] = nums[i];
            else
                pre[i] = Math.max(pre[i - 1], nums[i]);
        }
        int[] post = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int temp = i + 1;
            if ((temp) % k == 0)
                post[i] = nums[i];
            else {
                if (i + 1 < n)
                    post[i] = Math.max(post[i + 1], nums[i]);
                else
                    post[i] = nums[i];
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i % k == 0 && i + k - 1 < n)
                ans.add(post[i]);
            else {
                if (i + k - 1 < n)
                    ans.add(Math.max(post[i], pre[i + k - 1]));
            }
        }
        int[] ar = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++)
            ar[i] = ans.get(i);
        return ar;
    }

    // clone tree with random pointors

    static class Tree {
        int data;
        Tree left, right, random;

        Tree(int d) {
            data = d;
            left = null;
            right = null;
            random = null;
        }

        public static Tree cloneTree(Tree tree) {
            tree = add(tree);
            random(tree);
            return retans(tree);
        }

        public static Tree add(Tree node) {
            if (node == null)
                return null;
            Tree temp = new Tree(node.data);
            Tree lft = node.left;
            node.left = temp;
            temp.left = add(lft);
            node.right = add(node.right);
            return node;
        }

        public static void random(Tree root) {
            if (root == null)
                return;
            if (root.left != null && root.random != null)
                root.left.random = root.random.left;
            random(root.left);
            random(root.right);
        }

        public static Tree retans(Tree root) {
            if (root == null)
                return null;
            Tree temp = root.right;
            root = root.left;
            root.left = retans(root.left);
            root.right = retans(temp);
            return root;

        }
        // ======================16 august===================================

        // binary tree to doubly //linkedlist
        Node bTreeToClist(Node root) {
            Node ret = bTreeToClist_(root);
            return ret.right;
        }

        Node bTreeToClist_(Node root) {
            if (root == null)
                return null;
            Node lft = bTreeToClist_(root.left);
            Node rt = bTreeToClist_(root.right);
            if (lft == null && rt == null) {
                root.left = root;
                root.right = root;
                return root;
            } else if (lft != null && rt == null) {
                Node first = lft.right;
                lft.right = root;
                root.left = lft;
                root.right = first;
                first.left = root;
                return root;
            } else if (lft == null && rt != null) {
                Node first = rt.right;
                root.right = first;
                first.left = root;
                root.left = rt;
                rt.right = root;
                return rt;
            }

            else {

                Node lftfirst = lft.right;
                lft.right = root;
                root.left = lft;
                Node rtfirst = rt.right;
                root.right = rtfirst;
                rtfirst.left = root;
                lftfirst.left = rt;
                rt.right = lftfirst;
                return rt;

            }
        }
    }

    // ===========================dll to binary tree===============

    // isBstpreorder
    public boolean isValidBST(TreeNode root) {
        prev = null;
        return isValidBST_(root);// && isValidBST(root.right);
    }

    TreeNode prev = null;

    public boolean isValidBST_(TreeNode root) {
        if (root == null)
            return true;
        boolean res = true;

        res = res && isValidBST_(root.left);
        if (prev != null && prev.val >= root.val)
            return false;
        prev = root;
        System.out.println(prev.val);
        res = res && isValidBST_(root.right);
        return res;
    }

    // leetcode 99
    // recover bst
    // morris traversal
    public void recoverTree(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null;
        TreeNode first = null;
        TreeNode second = null;
        while (curr != null) {
            if (curr.left == null) {
                if (prev == null)
                    prev = curr;
                else {
                    if (prev.val > curr.val) {
                        if (first == null) {
                            first = prev;
                            second = curr;
                        } else
                            second = curr;
                    }
                    prev = curr;
                }
                curr = curr.right;
            } else {
                TreeNode leftkaright = findleftkarightmost(curr);
                if (leftkaright.right == curr) {
                    leftkaright.right = null;
                    if (prev == null)
                        prev = curr;
                    else {
                        if (prev.val >= curr.val) {
                            if (first == null) {
                                first = prev;
                                second = curr;
                            } else
                                second = curr;
                        }
                        prev = curr;
                    }
                    curr = curr.right;
                } else {
                    leftkaright.right = curr;
                    curr = curr.left;
                }
            }
        }
        if (first != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    public TreeNode findleftkarightmost(TreeNode root) {
        TreeNode curr = root.left;
        while (curr.right != null && curr.right != root)
            curr = curr.right;
        return curr;
    }

    
    // construct bst from postorder

    public static Node constructTree(int[] post, int n) {
        idx_ = n - 1;
        return construct(post, null, (int) -1e8, (int) 1e8);
    }

    static int idx_ = 0;

    public static Node construct(int[] post, Node curr, int leftlimit, int rightlimit) {
        if (idx_ < 0)
            return null;
        if (post[idx_] >= leftlimit && post[idx_] <= rightlimit) {
            Node node = new Node(post[idx_]);
            idx_--;
            node.right = construct(post, node, node.data + 1, rightlimit);
            node.left = construct(post, node, leftlimit, node.data - 1);
            return node;
        }
        return null;
    }

}