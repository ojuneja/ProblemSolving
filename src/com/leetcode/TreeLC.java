package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class TreeNode {
	TreeNode left;
	TreeNode right;
	TreeNode next;
	int val = 0;

	TreeNode(int val) {
		this.val = val;
	}
}

public class TreeLC {

	private TreeNode root;
	private TreeNode prev = null, node1 = null, node2 = null;
	int max = 0;

	void insert(int key) {
		root = insertInTree(root, key);
	}

	TreeNode getRoot() {
		return root;
	}

	/*
	 * Simple insertion in tree
	 */
	public TreeNode insertInTree(TreeNode node, int key) {
		if (node == null) {
			node = new TreeNode(key);
			node.left = null;
			node.right = null;
		} else if (node.val >= key) {
			TreeNode nodeLeft = insertInTree(node.left, key);
			node.left = nodeLeft;
		} else {
			TreeNode nodeRight = insertInTree(node.right, key);
			node.right = nodeRight;
		}
		return node;
	}

	/**
	 * 
	 * Leetcode problem number: lc 107 Problem Name: Binary Tree Order
	 * Traversal2 Status: green
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		Stack<List<TreeNode>> stack = new Stack<>();
		List<List<Integer>> listResult = new ArrayList<List<Integer>>();
		if (root == null) {
			List<Integer> values = new ArrayList<Integer>();
			listResult.add(values);
			return listResult;
		}
		if (root.left == null && root.right == null) {
			List<Integer> values = new ArrayList<Integer>();
			values.add(root.val);
			listResult.add(values);
			return listResult;
		} else {
			List<TreeNode> node = new ArrayList<TreeNode>();
			node.add(root);
			stack.push(node);
			int level = 0;
			while (true) {
				int listSize = stack.get(level).size();
				List<TreeNode> listNode = new ArrayList<TreeNode>();
				for (int i = 0; i < listSize; i++) {
					TreeNode treeNode = stack.get(level).get(i);
					if (treeNode.left != null) {
						listNode.add(treeNode.left);
					}
					if (treeNode.right != null) {
						listNode.add(treeNode.right);
					}
				}
				if (listNode.size() == 0) {
					break;
				}
				stack.push(listNode);
				level++;
			}
		}
		while (!stack.isEmpty()) {
			List<TreeNode> listTreeNodes = stack.pop();
			List<Integer> valueTreeNodes = new ArrayList<>();
			for (TreeNode eachNode : listTreeNodes) {
				valueTreeNodes.add(eachNode.val);
			}
			listResult.add(valueTreeNodes);
		}
		return listResult;
	}

	/**
	 * 
	 * Leetcode problem number: lc 101 Problem Name: Symmteric Tree Status:
	 * yellow
	 */
	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		return isSymmetric(root.left, root.right);
	}

	/**
	 * 
	 * Leetcode problem number: lc 101 Problem Name: Symmteric Tree Helper
	 * Status: yellow
	 */
	public boolean isSymmetric(TreeNode l, TreeNode r) {
		if (l == null && r == null) {
			return true;
		} else if (r == null || l == null) {
			return false;
		}

		if (l.val != r.val)
			return false;
		return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);

	}

	/**
	 * 
	 * Leetcode problem number: lc 235 Problem Name: Lowest Common Ancestor of a
	 * Binary Search Tree Tree Method: Recursion Status: yellow
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		if (root.val < p.val && root.val < q.val) {
			return lowestCommonAncestor(root.right, p, q);
		} else if (root.val > p.val && root.val > q.val) {
			return lowestCommonAncestor(root.left, p, q);
		}
		return root;
	}

	/**
	 * 
	 * Leetcode problem number: lc 235 Problem Name: Lowest Common Ancestor of a
	 * Binary Search Tree Tree Method: Iteration Status: yellow
	 */
	public TreeNode lowestCommonAncestorIter(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		while (root != null) {
			if (root.val < p.val && root.val < q.val) {
				root = root.right;
			} else if (root.val > p.val && root.val > q.val) {
				root = root.left;
			} else
				break;
		}
		return root;
	}

	/**
	 * Leetcode problem number: lc226 Problem name: Invert Binary Tree method:
	 * recursion status: green
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}

		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = right;
		root.right = left;
		return root;
	}

	/**
	 * Leetcode problem number: lc226 Problem name: Invert Binary Tree method:
	 * iteration status: yellow
	 */
	public TreeNode invertTreeRec(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode temp;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode node = q.remove();
			if (node.right != null) {
				q.add(node.right);
			}
			if (node.left != null) {
				q.add(node.left);
			}
			temp = node.right;
			node.right = node.left;
			node.left = temp;
		}
		return root;
	}

	/**
	 * Leetcode problem number: lc226 Problem name: Same Tree method: recursion
	 * status: green
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p == null || q == null)
			return false;
		if (p.val != q.val)
			return false;
		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	/**
	 * Leetcode problem number: lc98 Problem name: Validate Binary Search Tree
	 * method: recursion status: red
	 */
	public boolean isValidBST(TreeNode root) {
		return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	}

	/**
	 * Leetcode problem number: lc98 Problem name: Validate Binary Search Tree
	 * method: recursion status: red
	 */
	public boolean isValidBST(TreeNode p, double min, double max) {
		if (p == null)
			return true;

		if (p.val <= min || p.val >= max)
			return false;

		return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
	}

	/**
	 * Leetcode problem number: lc112 Problem name: Path Sum method: recursion
	 * status: red
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root.val == sum && (root.left == null && root.right == null))
			return true;

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}

	/**
	 * Leetcode problem number: lc144 Problem name: Binary Tree Preorder
	 * Traversal method: recursion status: red
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<>();
		if (root == null) {
			return list;
		}
		stack.add(root);
		while (!stack.isEmpty()) {
			while (root != null) {
				list.add(root.val);
				if (root.right != null) {
					stack.push(root.right);
				}
				root = root.left;
			}
			root = stack.pop();
		}
		return list;
	}

	/**
	 * Leetcode problem number: lc129 Problem name: Sum Root to Leaf Numbers
	 * method: recursion status: red
	 */
	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;

		return dfs(root, 0, 0);
	}

	/**
	 * Leetcode problem number: lc129 Problem name: Sum Root to Leaf Numbers
	 * method: recursion status: red
	 */
	public int dfs(TreeNode node, int num, int sum) {
		if (root == null) {
			return sum;
		}
		num = num * 10 + node.val;
		if (node.left == null && node.right == null) {
			sum += num;
			return sum;
		}
		sum = dfs(node.left, num, sum) + dfs(node.right, num, sum);
		return sum;
	}

	/**
	 * Leetcode problem number: lc113 Problem name: Path Sum II method:
	 * recursion status: green
	 */
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(root.val);
		dfs(root, sum - root.val, result, l);
		return result;
	}

	/**
	 * Leetcode problem number: lc113 Problem name: Path Sum II method:
	 * recursion status: green
	 */
	void dfs(TreeNode root, int sum, ArrayList<List<Integer>> result, ArrayList<Integer> l) {
		if (sum == 0 && root.left == null && root.right == null) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.addAll(l);
			result.add(temp);
		}
		// search path of left node
		if (root.left != null) {
			l.add(root.left.val);
			dfs(root.left, sum - root.left.val, result, l);
			l.remove(l.size() - 1);
		}
		// search path of right node
		if (root.right != null) {
			l.add(root.right.val);
			dfs(root.right, sum - root.right.val, result, l);
			l.remove(l.size() - 1);
		}
	}

	/**
	 * Leetcode problem number: lc106 Problem name: Construct Binary Tree from
	 * Inorder and Postorder Traversal method: recursion status: yellow
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		int inStart = 0;
		int inEnd = inorder.length - 1;
		int postEnd = postorder.length - 1;
		return constructTree(inorder, postorder, inEnd, inStart, postEnd);
	}

	/**
	 * Leetcode problem number: lc106 Problem name: Construct Binary Tree from
	 * Inorder and Postorder Traversal method: recursion status: yellow
	 */
	public TreeNode constructTree(int[] inorder, int[] postorder, int inEnd, int inStart, int postEnd) {
		if (inStart < inEnd && postEnd < 0) {
			return null;
		}
		int rootValue = postorder[postEnd];
		TreeNode node = new TreeNode(rootValue);
		int k = postEnd;
		for (int i = inEnd; i < inStart; i--) {
			if (inorder[i] == rootValue) {
				k = i;
				break;
			}
		}
		node.right = constructTree(inorder, postorder, inEnd, k + 1, postEnd - 1);
		node.left = constructTree(inorder, postorder, k - 1, inStart, postEnd - (inStart - k) - 1);
		return node;
	}

	/**
	 * Leetcode problem number: lc108 Problem Name: Convert Sorted Array to
	 * Binary Search Tree Method: recursion status: yellow
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length == 0) {
			return null;
		}
		if (nums.length == 1) {
			return new TreeNode(nums[0]);
		}
		return makeTree(nums, 0, nums.length - 1);
	}

	public TreeNode makeTree(int[] nums, int start, int end) {
		if (end > start) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode node = new TreeNode(nums[mid]);
		node.left = makeTree(nums, start, mid - 1);
		node.right = makeTree(nums, mid + 1, end);
		return node;
	}

	/**
	 * Leetcode problem number: lc94 Problem Name: Binary Tree Inorder Traversal
	 * status: yellow
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (root == null) {
			return null;
		}
		if (root.left == null && root.right == null) {
			return result;
		}
		stack.push(root);
		while (!stack.isEmpty()) {
			while (root.left != null) {
				root = root.left;
				stack.push(root);
			}
			result.add(root.val);
			while (root.right == null) {
				if (!stack.isEmpty()) {
					root = stack.pop();
					result.add(root.val);
				} else
					break;
			}
			root = root.right;
			if (root == null) {
				break;
			}
		}
		return result;
	}

	/**
	 * Leetcode problem number: lc236 Problem Name: Lowest Common Ancestor of a
	 * Binary Tree status: yellow
	 */
	public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
		if (covers(root.left, p) && covers(root.left, q)) {
			return lowestCommonAncestorBinaryTree(root.left, p, q);
		}
		if (covers(root.right, p) && covers(root.right, q)) {
			return lowestCommonAncestorBinaryTree(root.right, p, q);
		}
		return root;
	}

	/**
	 * Leetcode problem number: lc236 Problem Name: Lowest Common Ancestor of a
	 * Binary Tree status: yellow
	 */
	public boolean covers(TreeNode node, TreeNode subTree) {
		if (node == null) {
			return false;
		}
		if (node.val == subTree.val)
			return true;
		return covers(node.left, subTree) && covers(node.right, subTree);
	}

	/**
	 * Leetcode problem number: lc Method Name: status: yellow
	 */
	public void recoverTree(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return;
		}
		inorderTraverse(root);
		int tmp = node1.val;
		node1.val = node2.val;
		node2.val = tmp;
	}

	public void inorderTraverse(TreeNode root) {
		if (root == null)
			return;
		inorderTraverse(root.left);
		if (prev != null) {
			if (prev.val >= root.val) {
				if (node1 == null) {
					node1 = prev;
				}
				node2 = prev;
			}
		}
		prev = root;
		inorderTraverse(root.right);
	}

	public void connect(TreeNode root) {
		if (root == null)
			return;

		LinkedList<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		LinkedList<Integer> depthQueue = new LinkedList<Integer>();

		if (root != null) {
			nodeQueue.offer(root);
			depthQueue.offer(1);
		}

		while (!nodeQueue.isEmpty()) {
			TreeNode topNode = nodeQueue.poll();
			int depth = depthQueue.poll();

			if (depthQueue.isEmpty()) {
				topNode.next = null;
			} else if (depthQueue.peek() > depth) {
				topNode.next = null;
			} else {
				topNode.next = nodeQueue.peek();
			}

			if (topNode.left != null) {
				nodeQueue.offer(topNode.left);
				depthQueue.offer(depth + 1);
			}

			if (topNode.right != null) {
				nodeQueue.offer(topNode.right);
				depthQueue.offer(depth + 1);
			}
		}
	}

	
	
	public static void main(String[] args) {
		TreeLC lc107 = new TreeLC();
		lc107.insert(12);
		lc107.insert(9);
		lc107.insert(7);
		lc107.insert(15);
		lc107.insert(13);
		lc107.insert(20);
		lc107.insert(100);
		lc107.levelOrderBottom(lc107.getRoot());
		TreeLC lc235L = new TreeLC();
		lc235L.insert(13);
		TreeLC lc235R = new TreeLC();
		lc235R.insert(20);
		lc107.lowestCommonAncestor(lc107.getRoot(), lc235R.getRoot(), lc235R.getRoot());
		TreeLC lc129 = new TreeLC();
		lc129.sumNumbers(lc107.getRoot());
		lc129.connect(lc107.getRoot());
	}
}
