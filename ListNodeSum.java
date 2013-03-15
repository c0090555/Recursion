/*
Problem 1: Add numbers from the least significant numbers
	e.g.: 1->2->3 + 4->5->6 = 5->7->9
	
Problem 2: Add numbers from the most significant numbers
 	e.g.: 1->2 + 3->4->5 =  3->5->7

Use recursion

 */

public class ListNodeSum {
	public ListNode addFromLeastSig(ListNode m, ListNode n) {
		if (m == null) {
			return n;
		}
		if (n == null) {
			return m;
		}
		ListNode res = addFromLeastSigHelper(m, n, 0);
		return res;

	}

	public ListNode addFromLeastSigHelper(ListNode m, ListNode n, int carry) {
		if (m == null && n == null) {
			return null;
		}
		int val = carry;
		if (m != null) {
			val += m.val;
		}
		if (n != null) {
			val += n.val;
		}
		carry = val / 10;
		val = val % 10;
		ListNode res = new ListNode(val);
		ListNode next = addFromLeastSigHelper(m == null ? null : m.next,
				n == null ? null : n.next, carry);
		res.next = next;
		return res;
	}

	public ListNode addFromMostSig(ListNode m, ListNode n) {
		if (m == null) {
			return n;
		}
		if (n == null) {
			return m;
		}
		int lm = lengthOfList(m);
		int ln = lengthOfList(n);
		if (lm != ln) {
			int diff = Math.abs(lm - ln);
			if (lm > ln) {
				n = padList(n, diff);
			} else {
				m = padList(m, diff);
			}
		}

		PartialSum res = addFromMostSigHelper(m, n);
		if (res.carry == 0) {
			return res.n;
		} else {
			ListNode start = new ListNode(res.carry);
			start.next = res.n;
			return start;
		}

	}

	public ListNode padList(ListNode m, int length) {
		while (length > 0) {
			ListNode zero = new ListNode(0);
			zero.next = m;
			m = zero;
			length--;
		}
		return m;
	}

	public int lengthOfList(ListNode m) {
		int l = 0;
		while (m != null) {
			l++;
			m = m.next;
		}
		return l;
	}

	public PartialSum addFromMostSigHelper(ListNode m, ListNode n) {
		if (m == null || n == null) {
			return new PartialSum(null, 0);
		}
		PartialSum nextSum = addFromMostSigHelper(m.next, n.next);
		int val = m.val + n.val + nextSum.carry;
		int carry = val / 10;
		val = val % 10;
		ListNode current = new ListNode(val);
		current.next = nextSum.n;
		return new PartialSum(current, carry);

	}

	class PartialSum {
		public int carry;
		public ListNode n;

		PartialSum(ListNode n, int c) {
			carry = c;
			this.n = n;
		}
	}

	public static void main(String[] args) {
		ListNodeSum o = new ListNodeSum();
		ListNode a = new ListNode(1);
		a.next = new ListNode(2);
		a.next.next = new ListNode(6);

		ListNode b = new ListNode(4);
		b.next = new ListNode(5);
		// b.next.next = new ListNode(6);

		ListNode res = o.addFromMostSig(a, b);
		while (res != null) {
			System.out.print(res.val + " ");
			res = res.next;
		}

	}

}
