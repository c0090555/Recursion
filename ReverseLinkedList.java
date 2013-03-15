/*
 * Reverse a LinkedList without any extra space
 Solution:
 http://analgorithmaday.blogspot.com/2011/03/reverse-linked-listusing-recursion.html
 
 Use recursion and consider the last two ListNodes
 
 */

public class ReverseLinkedList {
	public ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode result = reverse(head.next);
		head.next.next = head;// start from the second last ListNode
		head.next = null;
		return result;

	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		ReverseLinkedList o = new ReverseLinkedList();

		ListNode newHead = o.reverse(head);
		System.out.println("start");
		while (newHead != null) {
			System.out.println(newHead.val);
			newHead = newHead.next;
		}
	}
}
