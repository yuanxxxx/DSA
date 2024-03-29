package com.yuan.solution;

import com.yuan.entity.ListNode;
import com.yuan.entity.RandomListNode;
import com.yuan.entity.TreeLinkNode;
import com.yuan.entity.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.*;

/**
 * @author yuan
 */
public class OfferSolution {

    /**
     * 剑指offer-01 对每一行进行二分查找，后续查找的行大小逐渐递减
     * @param target 目标值
     * @param array 搜索的二维数组
     */
    public boolean find(int target, int [][] array) {
        if(array.length == 0 || array[0].length == 0){
            return false;
        }
        int rowId = 0, inRowCnt = array[0].length;
        while(rowId < array.length){
            int lo = 0;
            while(lo < inRowCnt){
                int mid = (lo + inRowCnt) / 2;
                if(target < array[rowId][mid]){
                    inRowCnt = mid;
                }else if(target == array[rowId][mid]){
                    return true;
                }else{
                    lo = mid + 1;
                }
            }
            rowId++;
        }
        return false;
    }

    /**
     * 剑指offer-02，遍历StringBuffer即可，注意遍历的方式
     * @param str 给定的string
     */
    public String replaceSpace(StringBuffer str){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                sb.append("%20");
            }else{
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    /**
     * 剑指offer-03，翻转链表
     * @param listNode 头结点
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<>();
        }
        ListNode guard = new ListNode(0);
        guard.next = listNode;
        while (listNode.next != null) {
            ListNode cur = listNode.next;
            listNode.next = listNode.next.next;
            cur.next = guard.next;
            guard.next = cur;
        }
        ArrayList<Integer> result = new ArrayList<>();
        while(guard.next != null){
            guard = guard.next;
            result.add(guard.val);
        }
        return result;
    }

    /**
     * 剑指offer-04 重建二叉树,写一个辅助函数或者用一个函数进行递归
     * @param pre 前序遍历
     * @param in 中序遍历
     */
    public TreeNode reConstructBinaryTree(int [] pre, int [] in) {
        if (pre.length == 0) {
            return null;
        }
        if (pre.length == 1) {
            return new TreeNode(pre[0]);
        }
        TreeNode result = new TreeNode(pre[0]);
        int idxIn = 0;
        while (idxIn < pre.length) {
            if (in[idxIn] == result.val) {
                break;
            }else {
                idxIn++;
            }
        }
        result.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, idxIn + 1),
                                            Arrays.copyOfRange(in, 0, idxIn));
        result.right = reConstructBinaryTree(Arrays.copyOfRange(pre, idxIn+1, pre.length),
                                             Arrays.copyOfRange(in, idxIn+1, in.length));
        return result;
    }

    /**
     * 剑指offer-05 用两个栈来实现一个队列
     */
    class queueToStack {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }

    /**
     * 剑指offer-06 旋转数组的最小数字，二分,比较中间的值与lo值的大小，注意边界情况
     * @param array 旋转数组
     */
    public int minNumberInRotateArray(int [] array) {
        int lo = 0, hi = array.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (mid + 1 == array.length) {
                return array[mid];
            } else if (array[mid + 1] < array[mid]) {
                return array[mid + 1];
            } else if (array[lo] <= array[mid]){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return array[lo];
    }

    /**
     * 剑指offer-07 斐波那契数列第n项，动归
     * @param n 第n项
     */
    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 2) {
            return 1;
        }
        int[] fiboArr = new int[n];
        fiboArr[0] = fiboArr[1] = 1;
        for (int i = 2; i < fiboArr.length; i++) {
            fiboArr[i] = fiboArr[i - 1] + fiboArr[i - 2];
        }
        return fiboArr[n - 1];
    }

    /**
     * 剑指offer-08 跳台阶，跳第n级台阶共多少种方法，动归
     * @param target 第n级台阶
     */
    public int jumpFloor(int target) {
        if (target == 0) {
            return 0;
        }
        if (target <= 2) {
            return target == 2? 2: 1;
        }
        int[] jumpNums = new int[target];
        jumpNums[0] = 1;
        jumpNums[1] = 2;
        for (int i = 2; i < jumpNums.length; i++) {
            jumpNums[i] = jumpNums[i - 1] + jumpNums[i - 2];
        }
        return jumpNums[target - 1];
    }

    /**
     * 剑指offer-09
     */
    public int jumpFloorII(int target) {
        if (target == 0) {
            return 0;
        }
        return (int)Math.pow(2, target - 1);
    }

    /**
     * 剑指offer-10 同斐波那契数列
     */
    public int rectCover(int target) {
        if (target == 0) {
            return 0;
        }
        if (target <= 2) {
            return target == 2? 2: 1;
        }
        int[] methods = new int[target];
        methods[0] = 1;
        methods[1] = 2;
        for (int i = 2; i < target; i++) {
            methods[i] = methods[i - 2] + methods[i - 1];
        }
        return methods[target - 1];
    }

    /**
     * 剑指offer-11 输出二进制中1的个数
     */
    public int numberOf1(int n) {
        int cnts = 0;
        while (n != 0) {
            cnts++;
            n = n & (n - 1);
        }
        return cnts;
    }


    /**
     * 剑指offer-12 注意负指数
     * @param base 基数
     * @param exponent 指数
     */
    public double power(double base, int exponent) {
        boolean negaFlag = false;
        if (exponent < 0) {
            exponent = -1 * exponent;
            negaFlag = true;

        }
        if (exponent == 0) {
            return 1;
        } else if (exponent == 1) {
            if (negaFlag) {
                return 1 / base;
            }
            return base;
        } else if (exponent % 2 == 1) {
            if (negaFlag) {
                return 1 / (power(base, (exponent - 1) / 2 ) * power(base, (exponent - 1) / 2 ) * base);
            }
            return power(base, (exponent - 1) / 2 ) * power(base, (exponent - 1) / 2 ) * base;
        } else {
            if (negaFlag) {
                return 1 / (power(base, exponent / 2) * power(base, exponent / 2));
            }
            return power(base, exponent / 2) * power(base, exponent / 2);
        }
    }

    /**
     * 剑指offer-13 改变数组中的顺序，奇数在前 偶数在后 注意算法的稳定性
     */
    public void reOrderArray(int [] array) {
        if (array.length == 0) {
            return;
        }
        int odd = 0, even = 0;
        while (even < array.length && array[even] % 2 == 1) {
            even++;
        }
        odd = even;
        while (odd < array.length) {
            while (odd < array.length && array[odd] % 2 == 0) {
                odd++;
            }
            if (odd < array.length) {
                int evenLen = odd - even;
                int oddIdx = odd;
                while (odd < array.length && array[odd] % 2 == 1) {
                    odd++;
                }
                int oddLen = odd - oddIdx;
                changeLocalOrder(array, even, evenLen, oddIdx, oddLen);

                even = even + oddLen;
            }
        }
    }

    private void changeLocalOrder(int [] array, int evenIdx, int evenLen, int oddIdx, int oddLen) {
        int [] tempArr = new int[oddLen];
        System.arraycopy(array, oddIdx, tempArr, 0, oddLen);
        System.arraycopy(array, evenIdx, array, evenIdx + oddLen, evenLen);
        System.arraycopy(tempArr, 0, array, evenIdx, oddLen);
    }

    /**
     * 剑指offer-14 倒数第k个节点 两个指针即可
     * @param head 头结点
     * @param k 倒数第k个
     */
    public ListNode findKthToTail(ListNode head,int k) {
        if (head == null) {
            return null;
        }
        ListNode pre = head;
        ListNode fol = head;
        for (int i = 0; i < k; i++) {
            if (fol == null) {
                return null;
            } else {
                fol = fol.next;
            }
        }
        while (fol != null) {
            pre = pre.next;
            fol = fol.next;
        }
        return pre;
    }

    /**
     * 剑指offer-15 反转链表
     * @param head 头结点
     * @return 新链表的表头
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        while (head.next != null) {
            ListNode cur = head.next;
            head.next = cur.next;
            cur.next = sentinel.next;
            sentinel.next = cur;
        }
        return sentinel.next;
    }

    /**
     * 剑指offer-16 合成单调链表
     * @param list1 单调递增链表1
     * @param list2 单调递增链表2
     * @return 新链表表头
     */
    public ListNode merge(ListNode list1,ListNode list2) {
        ListNode sentinel = new ListNode(0);
        ListNode cur = sentinel;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        return sentinel.next;
    }

    /**
     * 剑指offer-17 判断root2是不是root1的子结构，规定空树不是任何树的子结构
     * 递归判断
     */
    public boolean hasSubtree(TreeNode root1,TreeNode root2) {
        if (root2 == null || root1 == null) {
            return false;
        }
        return hasSubTreeDFS(root1, root2) || hasSubTreeDFS(root1.left, root2)
                || hasSubTreeDFS(root1.right, root2);
    }

    public boolean hasSubTreeDFS(TreeNode root1,TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val == root2.val && hasSubTreeDFS(root1.left, root2.left) && hasSubTreeDFS(root1.right, root2.right)) {
            return true;
        } else {
            return hasSubTreeDFS(root1.left, root2) || hasSubTreeDFS(root1.right, root2);
        }
    }

    /**
     * 剑指offer-18 镜像反转二叉树
     * 递归反转
     */
    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 剑指offer-19 顺时针打印矩阵
     * 注意处理特殊情况
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rowSt = 0, rowEnd = matrix.length - 1, colSt = 0, colEnd = matrix[0].length - 1;
        ArrayList<Integer> result = new ArrayList<>();
        while (rowSt < rowEnd && colSt < colEnd) {
            int colId = colSt;
            while (colId < colEnd) {
                result.add(matrix[rowSt][colId]);
                colId++;
            }
            int rowId = rowSt;
            while (rowId < rowEnd) {
                result.add(matrix[rowId][colEnd]);
                rowId++;
            }
            while (colSt < colId) {
                result.add(matrix[rowEnd][colId]);
                colId--;
            }
            while (rowSt < rowId) {
                result.add(matrix[rowId][colSt]);
                rowId--;
            }

            rowSt++;
            colSt++;
            rowEnd--;
            colEnd--;
        }
        if (rowSt == rowEnd && colSt == colEnd) {
            result.add(matrix[rowSt][colSt]);
        } else if (rowSt == rowEnd && colSt < colEnd) {
            while (colSt <= colEnd) {
                result.add(matrix[rowSt][colSt]);
                colSt++;
            }
        } else if (rowSt < rowEnd && colSt == colEnd) {
            while (rowSt <= rowEnd) {
                result.add(matrix[rowSt][colSt]);
                rowSt++;
            }
        }
        return result;
    }

    /**
     * 剑指offer-20 最小元素栈
     * 只用一个栈实现，注意最小值
     */
    class minStack{

        private Stack<Integer> stack = new Stack<>();
        private int minVal = 0;

        public void push(int node) {
            if (stack.isEmpty()) {
                stack.push(node);
                minVal = node;
            } else {
                if (node < minVal) {
                    stack.push(minVal);
                    stack.push(node);
                    minVal = node;
                } else {
                    stack.push(node);
                }
            }
        }

        public void pop() {
            int popVal = stack.pop();
            if (popVal == minVal) {
                minVal = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return minVal;
        }
    }

    /**
     * 剑指offer-21 栈的压入序列
     * 辅助栈
     */
    public boolean isPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int popIdx = 0;
        for (int i = 0; i < pushA.length; i++) {
            if (pushA[i] != popA[popIdx]) {
                stack.push(pushA[i]);
            } else {
                popIdx++;
            }
        }
        while (popIdx != popA.length) {
            if (popA[popIdx] != stack.pop()) {
                return false;
            } else {
                popIdx++;
            }
        }
        return true;
    }

    /**
     * 剑指offer-22 从上到下打印二叉树
     */
    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode curNode = nodes.poll();
            if (curNode.left != null) {
                nodes.add(curNode.left);
            }
            if (curNode.right != null) {
                nodes.add(curNode.right);
            }
            result.add(curNode.val);
        }
        return result;
    }

    /**
     * 剑指offer-23 判断是否是二叉搜索树的后序遍历
     * 后序遍历的最后一个节点为根节点
     */
    public boolean verifySquenceOfBST(int [] sequence) {
        if (sequence == null || sequence.length <= 1) {
            return true;
        }
        int rootVal = sequence[sequence.length - 1];
        int leftSubTreeIdx = 0;
        while (leftSubTreeIdx < sequence.length - 1) {
            if (rootVal < sequence[leftSubTreeIdx]) {
                break;
            } else {
                leftSubTreeIdx++;
            }
        }
        int rightSubTreeIdx = leftSubTreeIdx;
        while (rightSubTreeIdx < sequence.length - 1) {
            if (sequence[rightSubTreeIdx] < rootVal) {
                return false;
            } else {
                rightSubTreeIdx++;
            }
        }
        rightSubTreeIdx--;
        if (leftSubTreeIdx == sequence.length - 1 || leftSubTreeIdx == 0) {
            int [] subTree = new int[leftSubTreeIdx + 1];
            System.arraycopy(sequence, 0, subTree, 0, subTree.length);
            return verifySquenceOfBST(subTree);
        } else {
            int [] leftSubTree = new int[leftSubTreeIdx];
            int [] rightSubTree = new int[rightSubTreeIdx - leftSubTreeIdx + 1];
            System.arraycopy(sequence, 0, leftSubTree, 0, leftSubTree.length);
            System.arraycopy(sequence, leftSubTreeIdx, rightSubTree, 0, rightSubTree.length);
            return verifySquenceOfBST(leftSubTree) && verifySquenceOfBST(rightSubTree);
        }
    }

    /**
     * 剑指offer-24 打印出满足条件的所有路径
     * 广度优先，下面的方法能通过测试，但是是错的
     */
    private ArrayList<ArrayList<Integer>> fpResult = new ArrayList<>();
    private ArrayList<Integer> fpPath = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> findPath(TreeNode root,int target) {
        if (root == null) {
            return fpResult;
        }
        fpPath.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            fpResult.add(new ArrayList<>(fpPath));
        } else {
            findPath(root.left, target);
            findPath(root.right, target);
        }
        fpPath.remove(fpPath.size() - 1);
        return fpResult;
    }

    /**
     * 剑指offer-25 复杂链表的复制
     * 维护一个新旧节点的映射关系，两轮遍历
     */
    public RandomListNode clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        Map<RandomListNode, RandomListNode> oldToNew = new HashMap<>();
        RandomListNode oldNode = pHead;
        RandomListNode newHead = new RandomListNode(oldNode.label);
        RandomListNode newNode = newHead;
        oldToNew.put(oldNode, newNode);
        oldNode = oldNode.next;
        while (oldNode != null) {
            newNode.next = new RandomListNode(oldNode.label);
            newNode = newNode.next;
            oldToNew.put(oldNode, newNode);
            oldNode = oldNode.next;
        }
        oldNode = pHead;
        newNode = newHead;
        while (oldNode != null) {
            newNode.random = oldToNew.getOrDefault(oldNode.random, null);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return newHead;
    }

    /**
     * 剑指offer-26 二叉树转双向链表
     * 递归
     */
    public TreeNode convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        return convertHelper(pRootOfTree)[0];
    }

    private TreeNode[] convertHelper(TreeNode node) {
        if (node.left == null && node.right == null) {
            return new TreeNode[]{node, node};
        } else if (node.left != null && node.right == null) {
            TreeNode[] left = convertHelper(node.left);
            left[1].right = node;
            node.left = left[1];
            return new TreeNode[]{left[0], node};
        } else if (node.left == null) {
            TreeNode[] right = convertHelper(node.right);
            right[0].left = node;
            node.right = right[0];
            return new TreeNode[]{node, right[1]};
        } else {
            TreeNode[] left = convertHelper(node.left);
            TreeNode[] right = convertHelper(node.right);
            left[1].right = node;
            node.left = left[1];
            right[0].left = node;
            node.right = right[0];
            return new TreeNode[]{left[0], right[1]};
        }
    }

    /**
     * 剑指offer-27 按字典序打印所有排列
     */
    public ArrayList<String> permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            permutationHelper(str.toCharArray(), i, result);
            Collections.sort(result);
        }
        return result;
    }

    private void permutationHelper(char[] cs, int idx , ArrayList<String> result) {
        if (idx == cs.length - 1) {
            String curRes = String.valueOf(cs);
            if (!result.contains(curRes)) {
                result.add(curRes);
            }
        } else {
            for (int j = idx; j < cs.length; ++j) {
                swap(cs, j, idx);
                permutationHelper(cs, idx+1, result);
                swap(cs, j, idx);
            }
        }
    }

    private void swap(char [] arr, int i ,int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 剑指offer-28 出现次数超过一半的数字
     * 投票算法
     */
    public int moreThanHalfNum(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        Map<Integer, Integer> numToCnts = new HashMap<>();
        int size = array.length;
        for (int num: array) {
            numToCnts.put(num, numToCnts.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry: numToCnts.entrySet()) {
            if (entry.getValue() > size / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    /**
     * 剑指offer-29 最小的k个数
     * 堆，快排改版
     */
    public ArrayList<Integer> getLeastNumbers(int [] input, int k) {
        if (input == null || input.length < k || k == 0) {
            return new ArrayList<>();
        }
        int curPivotIdx = -1, lo = 0, hi = input.length - 1;
        while (curPivotIdx != k - 1) {
            curPivotIdx = quickSort(input, lo, hi);
            if (curPivotIdx < k - 1) {
                lo = curPivotIdx + 1;
            } else {
                hi = curPivotIdx - 1;
            }
        }
        return Arrays.stream(Arrays.copyOfRange(input, 0, curPivotIdx + 1)).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    private int quickSort(int[] input, int lo, int hi) {
        int pivotIdx = (int)(Math.random() * 100) % (hi - lo + 1);
        swap(input, lo, pivotIdx);
        int pivot = input[lo];
        while (lo < hi) {
            while (lo < hi && pivot <= input[hi]) {
                hi--;
            }
            input[lo] = input[hi];
            while (lo < hi && input[lo] <= pivot) {
                lo++;
            }
            input[hi] = input[lo];
        }
        input[lo] = pivot;
        return lo;
    }

    private void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    /**
     * 剑指offer-30 连续子数组最大和
     * 滑动窗口
     */
    public int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return MIN_VALUE;
        }
        int max = MIN_VALUE;
        int curSum = MIN_VALUE;
        for (int num: array) {
            if (curSum < 0) {
                curSum = num;
            } else {
                curSum += num;
            }
            max = Math.max(curSum, max);
        }
        return max;
    }

    /**
     * 剑指offer-31 整数中1出现次数
     * 暴力解法
     */
    public int numberOf1Between1AndN(int n) {
        int result = 0;
        for (int i = 0; i <= n; i++) {
            int cur = i;
            while (cur != 0) {
                if (cur % 10 == 1) {
                    result++;
                }
                cur /= 10;
            }
        }
        return result;
    }

    /**
     * 剑指offer-32 数组排成最小的数
     */
    public String printMinNumber(int [] numbers) {
        Integer[] arr = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, (o1, o2) -> {
            String s1 = o1 + String.valueOf(o2);
            String s2 = o2 + String.valueOf(o1);
            return s1.compareTo(s2);
        });
        StringBuilder builder = new StringBuilder();
        Arrays.stream(arr).forEach(builder::append);
        return builder.toString();
    }

    /**
     * 剑指offer-33 丑数
     * 有点ugly
     */
    public int getUglyNumber(int index) {
        if (index == 0) {
            return 0;
        }
        List<Integer> uglyNums = Arrays.asList(1, 2, 3, 4, 5);
        uglyNums = new ArrayList<>(uglyNums);
        int idx2 = 2, idx3 = 2, idx5 = 4;
        while (index > uglyNums.size()) {
            int num2 = 2 * uglyNums.get(idx2);
            while (uglyNums.contains(num2)) {
                idx2++;
                num2 = 2 * uglyNums.get(idx2);
            }
            int num3 = 3 * uglyNums.get(idx3);
            while (uglyNums.contains(num3)) {
                idx3++;
                num3 = 3 * uglyNums.get(idx2);
            }
            int num5 = 5 * uglyNums.get(idx5);
            while (uglyNums.contains(num5)) {
                idx5++;
                num5 = 5 * uglyNums.get(idx2);
            }
            if (num2 < num3) {
                if (num2 < num5) {
                    uglyNums.add(num2);
                    idx2++;
                } else {
                    uglyNums.add(num5);
                    idx5++;
                }
            } else {
                if (num3 < num5) {
                    uglyNums.add(num3);
                    idx3++;
                } else {
                    uglyNums.add(num5);
                    idx5++;
                }
            }
        }
        return uglyNums.get(index - 1);
    }

    /**
     * 剑指offer-34 第一个只出现一次的字符
     */
    public int firstNotRepeatingChar(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        Map<Character, Integer> charToOccIndex = new HashMap<>();
        Set<Character> occChar = new HashSet<>();
        int minIndex = MAX_VALUE;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (occChar.contains(c)) {
                charToOccIndex.remove(c);
            } else {
                occChar.add(c);
                charToOccIndex.put(c, i);
            }
        }
        for (Integer idx: charToOccIndex.values()) {
            minIndex = Math.min(minIndex, idx);
        }
        return minIndex;
    }

    /**
     * 剑指offer-35 数组中的逆序对,对1000000007取模
     * 维护一个有序数组，每次将新的数字插入到不大于它的最后一个数字
     */
    public int inversePairs(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        ArrayList<Integer> orderedNums = new ArrayList<>();
        int result = 0;
        for (int num: array) {
            int idx = binarySearchBack(orderedNums, num);
            if (idx <= orderedNums.size() - 1 && orderedNums.get(idx) == num) {
                idx++;
            }
            orderedNums.add(idx, num);
            result += orderedNums.size() - idx - 1;
            if (result >= 1000000007) {
                result %= 1000000007;
            }
        }
        return result;
    }

    private int binarySearchBack(ArrayList<Integer> ordered, int num) {
        int lo = 0, hi = ordered.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (ordered.get(mid) <= num) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * 剑指offer-36 两个链表的第一个公共节点
     * 两个指针，本地能通过，剑指offer不知道有什么问题
     */
    public ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        ListNode traverse1 = pHead1;
        ListNode traverse2 = pHead2;
        while (traverse1 != traverse2) {
            traverse1 = traverse1.next == null? pHead2: traverse1.next;
            traverse2 = traverse2.next == null? pHead1: traverse2.next;
        }
        return traverse1;
    }

    /**
     * 剑指offer-37 排序数组中出现的次数
     * 两个二分
     */
    public int getNumberOfK(int [] array , int k) {
        ArrayList<Integer> arrs = Arrays.stream(array).boxed().collect(Collectors.toCollection(ArrayList::new));
        int lo = binarySearchForward(arrs, k);
        int hi = binarySearchBack(arrs, k);
        if (lo < array.length && array[lo] == k) {
            return hi - lo;
        } else {
            return 0;
        }
    }

    private int binarySearchForward(ArrayList<Integer> ordered, int num) {
        int lo = 0, hi = ordered.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (ordered.get(mid) < num) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /**
     * 剑指offer-38 二叉树的深度
     * 递归
     */
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }

    /**
     * 剑指offer-39 判断是不是平衡二叉树
     * 递归 判断树的深度
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null){
            return true;
        } else {
            // 左子树与右子树都为平衡二叉树，高度差小于1
            return isBalanced(root.left) && isBalanced(root.right) && -1 <= treeDepth(root.left) - treeDepth(root.right)
                    && treeDepth(root.left) - treeDepth(root.right) <= 1;
        }
    }

    /**
     * 剑指offer-40 数组中只出现一次的数字
     * 第一次做异或，找出结果二进制为1的最右边一位
     */
    public void findNumsAppearOnce(int[] array, int[] num1, int[] num2) {
        OptionalInt optionalInt = Arrays.stream(array).reduce((a, b) -> a ^ b);
        int xor;
        if (optionalInt.isPresent()) {
            xor = optionalInt.getAsInt();
        } else {
            return;
        }
        int times = 0;
        while ((xor & 1) == 0) {
            xor >>= 1;
            times++;
        }
        int [] result = new int[]{0, 0};
        for (int num: array) {
            int temp = num >> times;
            if ((temp & 1) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        num1[0] = result[0];
        num2[0] = result[1];
    }

    /**
     * 剑指offer-41 和为S的连续正数序列
     * 算平均数，注意Math.floor函数，ugly。。。。。。
     */
    public ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        int totalNums = 1 + sum / 2;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        while (totalNums >= 2) {
            if (totalNums % 2 == 0) {
                float avg = (float)sum / totalNums;
                if (avg - Math.floor(avg) == 0.5 && (int)avg - totalNums / 2 >= 0) {
                    ArrayList<Integer> curNums = new ArrayList<>();
                    for (int num = (int)avg + 1 - totalNums / 2; num <= (int)avg + totalNums / 2; num++) {
                        curNums.add(num);
                    }
                    result.add(curNums);
                }
            } else {
                if (sum % totalNums == 0) {
                    int avg = sum / totalNums;
                    if (avg - totalNums / 2 > 0) {
                        ArrayList<Integer> curNums = new ArrayList<>();
                        for (int num = avg - totalNums / 2; num <= avg + totalNums / 2; num++) {
                            curNums.add(num);
                        }
                        result.add(curNums);
                    }
                }
            }
            totalNums--;
        }
        return result;
    }

    /**
     * 剑指offer-42 和为S的两个数字
     * 两个数字越相近，积越大
     */
    public ArrayList<Integer> findNumbersWithSum(int [] array,int sum) {
        int fakeAvg = sum / 2;
        int idx = 0;
        while (idx < array.length - 1) {
            if (array[idx] <= fakeAvg && fakeAvg < array[idx + 1]) {
                break;
            }
            idx++;
        }
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] + array[right] == sum) {
                return Arrays.stream(new int[]{array[left],
                        array[right]}).boxed().collect(Collectors.toCollection(ArrayList::new));
            } else if (array[left] + array[right] < sum) {
                left++;
            } else {
                right--;
            }
        }
        return new ArrayList<>();
    }

    /**
     * 剑指offer-43 左旋转字符串
     */
    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        n = n % str.length();
        return str.substring(n) + str.substring(0, n);
    }

    /**
     * 剑指offer-44 翻转字符串
     */
    public String reverseSentence(String str) {
        if (str == null || "".equals(str.trim())) {
            return str;
        }
        String[] strs = str.split(" ");
        Optional<String> result = Arrays.stream(strs).reduce((a, b) -> (b + " " + a));
        return result.orElse("");
    }

    /**
     * 剑指offer-45 扑克牌顺子
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        Arrays.sort(numbers);
        int idx = 0;
        while (idx < numbers.length) {
            if (numbers[idx] != 0) {
                break;
            }
            idx++;
        }
        int replacedNums = idx;
        while (idx < numbers.length - 1) {
            if (numbers[idx + 1] == numbers[idx]) {
                return false;
            }
            replacedNums -= numbers[idx + 1] - numbers[idx] - 1;
            if (replacedNums < 0) {
                return false;
            }
            idx++;
        }
        return true;
    }

    /**
     * 剑指offer-46 圆圈中最后剩下的数
     * 用LinkedList
     */
    public int lastRemaining(int n, int m) {
        LinkedList<Integer> nodes = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            nodes.add(i);
        }
        int rmIdx = 0;
        while (nodes.size() > 1) {
            rmIdx = (rmIdx + m - 1) % nodes.size();
            nodes.remove(rmIdx);
        }
        return nodes.size() == 1 ? nodes.get(0) : -1;
    }

    /**
     * 剑指offer-47 求1到n的和，不能用乘除法 循环 条件判断
     */
    public int sumN(int n) {
        int result = n;
        boolean c = n > 0 && ((result += sumN(n - 1)) > 0);
        return result;
    }

    /**
     * 剑指offer-48 两个整数之和，不能+ - * /
     * 按位与 左移一位，是进位，按位异或是不带进位的加法
     */
    public int add(int num1,int num2) {
        int carry = (num1 & num2) << 1;
        int xor = num1 ^ num2;
        while (carry != 0) {
            int num = xor ^ carry;
            carry = (xor & carry) << 1;
            xor = num;
        }
        return xor;
    }

    /**
     * 剑指offer-49 字符串转换为整数
     */
    public int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        boolean isPositive = true;
        int idx;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            if (str.length() == 1) {
                return 0;
            } else {
                isPositive = (str.charAt(0) != '-');
                idx = 1;
            }
        } else {
            idx = 0;
        }
        int result = 0;
        while (idx < str.length()) {
            if (str.charAt(idx) >= '0' && str.charAt(idx) <= '9') {
                result = 10 * result + (int)str.charAt(idx) - '0';
            } else {
                return 0;
            }
            idx++;
        }
        return isPositive? result: -1 * result;
    }

    /**
     * 剑指offer-50 返回重复的任意一个
     */
    public boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (length == 0) {
            return false;
        }
        Set<Integer> occured = new HashSet<>();
        for (int num: numbers) {
            if (occured.contains(num)) {
                duplication[0] = num;
                return true;
            } else {
                occured.add(num);
            }
        }
        return false;
    }

    /**
     * 剑指offer-51 构建乘积数组
     * 构建两个数组
     */
    public int[] multiply(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        int[] result = new int[a.length];
        int[] helperLeft = new int[a.length];
        int[] helperRight = new int[a.length];
        helperLeft[0] = helperRight[a.length - 1] = 1;
        for (int i = 1; i < a.length; i++) {
            helperLeft[i] = helperLeft[i - 1] * a[i - 1];
            helperRight[a.length - 1 - i] = helperRight[a.length - i] * a[a.length - i];
        }
        for (int i = 0; i < result.length; i++) {
            result[i] = helperLeft[i] * helperRight[i];
        }
        return result;
    }

    /**
     * 剑指offer-52 正则匹配
     */
    public boolean match(char[] str, char[] pattern)
    {
        if (pattern == null || pattern.length == 0) {
            return str != null && str.length == 0;
        }
        if (pattern.length == 1) {
            return (str.length == 1 && (pattern[0] == str[0] || pattern[0] == '.'));
        }
        if (pattern[1] != '*') {
            return (str[0] == pattern[0] || pattern[0] == '.') && match(Arrays.copyOfRange(str, 1, str.length)
                    , Arrays.copyOfRange(pattern, 1, pattern.length));
        }
        while (str.length != 0 && (str[0] == pattern[0] || pattern[0] == '.')) {
            if (match(str, Arrays.copyOfRange(pattern, 2, pattern.length))) {
                return true;
            }
            str = Arrays.copyOfRange(str, 1, str.length);
        }
        return match(str, Arrays.copyOfRange(pattern, 2, pattern.length));
    }

    /**
     * 剑指offer-54 字符流第一个不重复的字符
     */
    private List<Character> appeared = new ArrayList<>();

    public void insert(char ch)
    {
        appeared.add(ch);
    }
    public char firstAppearingOnce()
    {
        HashMap<Character, Integer> occTimes = new HashMap<>();
        appeared.forEach(ch->occTimes.put(ch, occTimes.getOrDefault(ch, 0) + 1));
        for (Character ch: appeared) {
            if (occTimes.get(ch) == 1) {
                return ch;
            }
        }
        return '#';
    }

    /**
     * 剑指offer-55 找到环的入口节点
     */
    public ListNode entryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null) {
            return null;
        }
        ListNode fast = pHead, slow = pHead;
        fast = fast.next.next;
        slow = slow.next;
        while (fast != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                slow = pHead;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 剑指offer-56 删除重复节点
     * 排序的链表 删除重复节点 所有的节点都要删除
     */
    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode pre = new ListNode(0);
        pre.next = pHead;
        ListNode cur = pre;
        while (cur.next != null && cur.next.next!=null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode temp = cur.next.next.next;
                while (temp != null && cur.next.val == temp.val) {
                    temp = temp.next;
                }
                cur.next = temp;
            } else {
                cur = cur.next;
            }
        }
        return pre.next;
    }

    /**
     * 剑指offer-57 二叉树的下一个节点
     */
    public TreeLinkNode getNext(TreeLinkNode pNode)
    {
        if (pNode == null) {
            return null;
        }
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            TreeLinkNode parent = pNode.next;
            if (parent.left == pNode) {
                return parent;
            } else {
                pNode = parent;
            }
        }
        return null;
    }

    /**
     * 剑指offer-58 对称的二叉树
     */
    public boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null) {
            return true;
        }
        return isSynmmetircalHelper(pRoot.left, pRoot.right);
    }

    private boolean isSynmmetircalHelper(TreeNode left, TreeNode right) {
        if (left == null) {
            return right == null;
        }
        if (right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSynmmetircalHelper(left.left, right.right) && isSynmmetircalHelper(left.right, right.left);
    }

    /**
     * 剑指offer-59 之字形打印二叉树
     */
    public ArrayList<ArrayList<Integer> > printNode(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Stack<TreeNode> curLayer = new Stack<>();
        Stack<TreeNode> nextLayer = new Stack<>();
        nextLayer.add(pRoot);
        boolean isReverse = false;
        while (!curLayer.isEmpty() || !nextLayer.isEmpty()) {
            curLayer = nextLayer;
            nextLayer = new Stack<>();
            ArrayList<Integer> curLayerResu = new ArrayList<>();
            while (!curLayer.isEmpty()) {
                TreeNode curNode = curLayer.pop();
                curLayerResu.add(curNode.val);
                if (isReverse) {
                    if (curNode.right != null) {
                        nextLayer.add(curNode.right);
                    }
                    if (curNode.left != null) {
                        nextLayer.add(curNode.left);
                    }
                } else {
                    if (curNode.left != null) {
                        nextLayer.add(curNode.left);
                    }
                    if (curNode.right != null) {
                        nextLayer.add(curNode.right);
                    }
                }
            }
            isReverse = !isReverse;
            result.add(curLayerResu);
        }
        return result;
    }

    /**
     * 剑指offer-60 从上到下打印二叉树
     */
    ArrayList<ArrayList<Integer> > printLayer(TreeNode pRoot) {
        if (pRoot == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        int layerSize, nextLayerSize = 1;
        nodes.add(pRoot);
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        while (nextLayerSize != 0) {
            layerSize = nextLayerSize;
            nextLayerSize = 0;
            int idx = 0;
            TreeNode node = nodes.pop();
            ArrayList<Integer> curLayerResu = new ArrayList<>();
            for (;;node = nodes.pop()) {
               curLayerResu.add(node.val);
               if (node.left != null) {
                   nodes.add(node.left);
                   ++nextLayerSize;
               }
               if (node.right != null) {
                   nodes.add(node.right);
                   ++nextLayerSize;
               }
               if (layerSize <= ++idx) {
                   break;
               }
            }
            result.add(curLayerResu);
        }
        return result;
    }

    /**
     * 剑指offer-61 序列化和反序列化二叉树
     */
    String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.pop();
            if (node != null) {
                builder.append(node.val);
            } else {
                builder.append('#');
            }
            if (node == null) {
                continue;
            }
            if (node.left != null) {
                nodes.add(node.left);
            } else {
                nodes.add(null);
            }
            if (node.right != null) {
                nodes.add(node.right);
            } else {
                nodes.add(null);
            }
        }
        return builder.toString();
    }

    public TreeNode deSerialize(String str) {
        if ("".equals(str)) {
            return null;
        }
        int nextLayerSize = 0, validSize = 1;
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(new TreeNode(str.charAt(0) - '0'));
        TreeNode result = nodes.peek();
        nextLayerSize = 2 * validSize;
        int iterIdx = 1;
        for (;iterIdx < str.length(); iterIdx += nextLayerSize) {
            String curLayer = str.substring(iterIdx, iterIdx + nextLayerSize);
            validSize = curLayer.length() - getOccTimesOfChar(curLayer, '#');
            for (int i = 0; i < curLayer.length(); i += 2) {
                TreeNode node = nodes.pop();
                if (str.charAt(i) != '#') {
                    TreeNode newNode = new TreeNode(str.charAt(i) - '0');
                    nodes.add(newNode);
                    node.left = newNode;
                }
                if (str.charAt(i + 1) != '#') {
                    TreeNode newNode = new TreeNode(str.charAt(i + 1) - '0');
                    nodes.add(newNode);
                    node.right = newNode;
                }
            }
            nextLayerSize = 2 * validSize;
        }
        return result;
    }

    private int getOccTimesOfChar(String s, char target) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (target == s.charAt(i)) {
                result++;
            }
        }
        return result;
    }

    /**
     * 滑动窗口最大值
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        if (num == null || num.length < size || size == 0) {
            return new ArrayList<>();
        }
        ArrayList<Integer> maxNums = new ArrayList<>();
        LinkedList<Integer> maxIdxs = new LinkedList<>();
        maxIdxs.add(0);
        for (int i = 1; i < size; i++) {
            Integer id = maxIdxs.getLast();
            while (num[id] < num[i]) {
                maxIdxs.removeLast();
                if (maxIdxs.isEmpty()) {
                    break;
                }
                id = maxIdxs.getLast();
            }
            maxIdxs.add(i);
        }
        maxNums.add(num[maxIdxs.getFirst()]);
        for (int i = size; i < num.length; i++) {
            int stIdx = i - size + 1;
            while (!maxIdxs.isEmpty() && maxIdxs.getFirst() < stIdx) {
                maxIdxs.removeFirst();
            }
            while (!maxIdxs.isEmpty()) {
                int id = maxIdxs.getLast();
                if (id < stIdx) {
                    maxIdxs.removeLast();
                    continue;
                }
                if (num[id] <= num[i]) {
                    maxIdxs.removeLast();
                } else {
                    break;
                }
            }
            maxIdxs.add(i);
            maxNums.add(num[maxIdxs.getFirst()]);
        }
        return maxNums;
    }

}
