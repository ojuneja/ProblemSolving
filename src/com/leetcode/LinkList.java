/**
 * 
 */
package com.leetcode;

/**
 * @author Ojas Juneja
 *
 */
class ListNode {
	int val;
	ListNode next;

	ListNode(int val) {
		this.val = val;
		next = null;
	}

	public ListNode() {
	}
}

public class LinkList {
	ListNode head = null;

	void addHead(int value) {
		ListNode linkNode = new ListNode(value);
		linkNode.next = head;
		head = linkNode;
	}

	ListNode getHead() {
		return head;
	}

	/**
	 * Leetcode problem number: lc2 Problem Name: Add Two Numbers status: yellow
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode node = new ListNode(0);
		ListNode result = node;
		int sum = 0;
		if (l1 == null & l2 == null) {
			return l1;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				sum = sum + l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				sum = sum + l2.val;
				l2 = l2.next;
			}
			node.next = new ListNode(sum % 10);
			node = node.next;
			sum = sum / 10;
		}
		if (sum == 1) {
			node.next = new ListNode(sum % 10);
		}
		return result.next;
	}

	/**
	 * Leetcode problem number: lc2 Problem Name: Add Two Numbers status: green
	 */
	public ListNode addTwoNumbersRec(ListNode l1, ListNode l2) {
		if (l1 == null & l2 == null) {
			return null;
		}
		if (l1 == null) {
			return l2;
		}
		if (l2 == null) {
			return l1;
		}
		return doRecursion(l1, l2, 0);
	}

	/**
	 * Leetcode problem number: lc2 Problem Name: Add Two Numbers status: yellow
	 */
	private ListNode doRecursion(ListNode l1, ListNode l2, int sum) {
		ListNode result = new ListNode();
		if (l1 == null & l2 == null) {
			if (sum != 0) {
				result = new ListNode(sum);
				return result;
			}
			return null;
		}
		if (l1 != null) {
			sum = sum + l1.val;
		}
		if (l2 != null) {
			sum = sum + l2.val;
		}
		result.val = sum % 10;
		ListNode node = doRecursion(l1 == null ? null : l1.next, l2 == null ? null : l2.next,
				sum / 10 >= 1 ? sum / 10 : 0);
		result.next = node;
		return result;
	}

	/**
	 * Leetcode problem number: lc206 Problem Name: Reverse Linked List status:
	 * green
	 */
	public ListNode reverseList(ListNode head) {
		ListNode rev = null;
		ListNode next = null;
		if (head == null || head.next == null) {
			return head;
		}
		while (head != null) {
			next = head.next;
			head.next = rev;
			rev = head;
			head = next;
		}
		return rev;
	}

	/**
	 * Leetcode problem number: lc206 Problem Name: Reverse Linked List status:
	 * green
	 */
	public ListNode reverseListRec(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode next = head.next;
		ListNode rev = head;
		rev.next = null;
		ListNode node = reverseListRec(next);
		rev.next = node;
		return rev;
	}

	/**
	 * Leetcode problem number: lc141 Problem Name:Linked List Cycle status:
	 * green
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head.next;
		while (slow.val != fast.val) {
			slow = slow.next;
			if (fast.next == null) {
				return false;
			}
			fast = fast.next.next;
			if (fast == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Leetcode problem number: lc160 ProblemName: Intersection of Two Linked
	 * Lists status: green
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode nodeA = headA;
		ListNode nodeB = headB;
		int countA = 0, countB = 0;
		boolean flag = true;
		if (headA == null || headB == null) {
			return null;
		}
		while (nodeA != null) {
			nodeA = nodeA.next;
			countA++;
		}
		while (nodeB != null) {
			nodeB = nodeB.next;
			countB++;
		}
		int diff = countA > countB ? countA - countB : countB - countA;
		flag = countA > countB ? true : false;
		countA = 0;
		while (countA < diff) {
			if (flag) {
				headA = headA.next;
			} else {
				headB = headB.next;
			}
			countA++;
		}
		while (headA != null && headB != null) {
			if (headA.val == headB.val)
				return headA;
			else {
				headA = headA.next;
				headB = headB.next;
			}
		}
		return null;
	}

	/**
	 * Leetcode problem number: lc19 ProblemName: Remove Nth Node From End of
	 * List status: green
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		int counter = 0;
		ListNode start = head;
		ListNode node = head;
		ListNode prev = null;
		if (head == null) {
			return head;
		}
		while (node != null && counter < n) {
			node = node.next;
			counter++;
		}
		while (node != null) {
			prev = start;
			start = start.next;
			node = node.next;
		}
		if (prev != null) {
			prev.next = start.next;
			start = null;
		}
		return head;
	}

	/**
	 * Leetcode problem number: lc142 Problem Name: Linked List Cycle II status:
	 * green
	 */
	public ListNode detectCycle(ListNode head) {
		if (head == null) {
			return null;
		}
		ListNode slow, fast;
		slow = fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next; // skips two nodes
			slow = slow.next;
			if (slow == fast) {
				break;
			}
		}
		if (fast == null || fast.next == null)
			return null;
		ListNode first = head;
		while (first != slow) {
			first = first.next;
			slow = slow.next;
		}
		return first;
	}

	/**
	 * Leetcode problem number: lc234 Problem Name:Palindrome Linked List
	 * status: green
	 */
	public boolean isPalindrome(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		ListNode rev = null;
		if (head == null || head.next == null) {
			return true;
		}
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = head.next;
			head.next = rev;
			rev = head;
			head = slow;
		}
		if (fast != null) {
			slow = slow.next;
		}
		while (slow != null && rev != null) {
			if (rev.val != slow.val) {
				return false;
			}
			slow = slow.next;
			rev = rev.next;
		}
		return slow == null && rev == null ? true : false;
	}

	/**
	 * Leetcode problem number: lc83 Problem Name: Remove Duplicates from Sorted
	 * List status: green
	 */
	public ListNode deleteDuplicates(ListNode head) {
		ListNode node = head;
		if (head == null || head.next == null) {
			return head;
		}
		while (node != null && node.next != null) {
			if (node.next.val == node.val) {
				int val = node.val;
				ListNode temp = node.next;
				while (temp.val == val) {
					temp = temp.next;
					if (temp == null) {
						break;
					}
				}
				node.next = temp;
			}
			node = node.next;
		}
		return head;
	}

	/**
	 * Leetcode problem number: lc82 Problem Name: Remove Duplicates from Sorted
	 * List II status: yellow
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode node = fakeHead;
		while (node.next != null && node.next.next != null) {
			if (node.next.val == node.next.next.val) {
				int dup = node.next.val;
				while (node.next != null && node.next.val == dup) {
					node.next = node.next.next;
				}
			} else {
				node = node.next;
			}
		}
		return fakeHead.next;
	}

	/**
	 * Leetcode problem number: lc203 Problem Name:Remove Linked List Elements
	 * status: green
	 */
	public ListNode removeElements(ListNode head, int val) {
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode node = fakeHead;
		if (head == null) {
			return head;
		}
		while (node != null && node.next != null) {
			if (node.next.val == val) {
				ListNode temp = node.next;
				while (temp != null && temp.val == val) {
					temp = temp.next;
				}
				node.next = temp;
			}
			node = node.next;
		}
		return fakeHead.next;
	}

	/**
	 * Leetcode problem number: lc328 Problem Name: Odd Even Linked List status:
	 * green
	 */
	public ListNode oddEvenList(ListNode head) {
		ListNode fakeNode = new ListNode(0);
		ListNode dummy = fakeNode;
		fakeNode.next = head;
		ListNode node = head;
		ListNode prev = null;
		int count = 1;
		if (head == null || head.next == null) {
			return head;
		}
		while (node != null) {
			if (count % 2 == 0) {
				fakeNode.next = node;
				prev.next = node.next;
				fakeNode = fakeNode.next;
			} else {
				prev = node;
			}
			node = node.next;
			count++;
		}
		fakeNode.next = null;
		prev.next = dummy.next;
		return head;
	}

	/**
	 * Leetcode problem number: lc92 Problem name: Reverse Linked List II
	 * status: green
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode prev = null;
		ListNode rev = null;
		ListNode next = head;
		ListNode node = head;
		int counter = 1;
		if (head == null || head.next == null) {
			return head;
		}
		while (node != null && counter != m) {
			prev = node;
			node = node.next;
			counter++;
		}
		while (node != null && counter != n + 1) {
			next = node.next;
			node.next = rev;
			rev = node;
			node = next;
			counter++;
		}
		if (prev != null) {
			prev.next.next = node;
			prev.next = rev;
		} else {
			head.next = node;
			head = rev;
		}
		return head;
	}

	/**
	 * Leetcode problem number: lc86 ProblemName:Partition List status: green
	 */
	public ListNode partition(ListNode head, int x) {
		if (head == null)
			return null;
		ListNode fakeHead1 = new ListNode(0);
		ListNode fakeHead2 = new ListNode(0);
		ListNode linknode1 = fakeHead1;
		ListNode linknode2 = fakeHead2;
		while (head != null) {
			if (head.val < x) {
				linknode1.next = head;
				head = head.next;
				linknode1 = linknode1.next;
			} else {
				linknode2.next = head;
				head = head.next;
				linknode2 = linknode2.next;
			}
		}
		linknode2.next = null;
		linknode1.next = fakeHead2.next;
		return fakeHead1.next;

	}

	/**
	 * Leetcode problem number: lc24 Problem Name: Swap Nodes in Pairs status:
	 * green
	 */
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = head;
		ListNode current = head.next;
		ListNode next;
		head = head.next;
		while (current != null && current.next != null) {
			next = current.next;
			current.next = prev;
			if (next.next == null) {
				prev.next = next;
			} else {
				prev.next = next.next;
			}
			prev = next;
			current = next.next;
		}
		if (current != null) {
			current.next = prev;
			prev.next = null;
			
		}
		return head;
	}

	public static void main(String[] args) {
		LinkList lc2_1 = new LinkList();
		LinkList lc2_2 = new LinkList();
		lc2_1.addHead(3);
		lc2_1.addHead(2);
		lc2_1.addHead(5);
		lc2_1.addHead(2);
		lc2_2.addHead(3);
		lc2_2.addHead(2);
		lc2_2.addHead(9);
		lc2_2.addHead(2);
		lc2_2.addHead(4);
		ListNode node = lc2_1.addTwoNumbersRec(lc2_1.getHead(), lc2_2.getHead());

		LinkList lc206 = new LinkList();
		lc206.reverseListRec(node);

		LinkList lc160 = new LinkList();
		lc160.getIntersectionNode(lc2_1.getHead(), lc2_2.getHead());

		LinkList lc19 = new LinkList();
		lc19.removeNthFromEnd(lc2_2.getHead(), 2);

		LinkList lc234 = new LinkList();
		lc234.addHead(1);
		lc234.addHead(2);
		lc234.addHead(3);
		lc234.addHead(2);
		lc234.addHead(1);
		System.out.println(lc234.isPalindrome(lc234.getHead()));

		LinkList lc83 = new LinkList();
		lc83.addHead(1);
		lc83.addHead(2);
		lc83.addHead(2);
		// lc83.addHead(3);
		lc83.deleteDuplicates(lc83.getHead());

		LinkList lc82 = new LinkList();
		lc82.addHead(1);
		lc82.addHead(1);
		lc82.deleteDuplicates2(lc82.getHead());

		LinkList lc203 = new LinkList();
		lc203.addHead(1);
		lc203.removeElements(lc203.getHead(), 1);

		LinkList lc328 = new LinkList();
		lc328.addHead(1);
		lc328.addHead(2);
		lc328.addHead(3);
		lc328.addHead(4);
		lc328.addHead(5);
		lc328.oddEvenList(lc328.getHead());

		LinkList lc92 = new LinkList();
		lc92.addHead(1);
		lc92.addHead(2);
		lc92.addHead(3);
		lc92.addHead(4);
		lc92.addHead(5);
		lc92.reverseBetween(lc92.getHead(), 1, 5);

		LinkList lc24 = new LinkList();
		lc24.addHead(1);
		lc24.addHead(2);
		lc24.addHead(3);
		lc24.addHead(4);
		lc92.swapPairs(lc24.getHead());
	}
}
