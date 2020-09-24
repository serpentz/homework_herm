import java.util.Iterator;

class UniqueList<T> implements Iterable<T> {

	ListNode<T> head = null;
	ListNode<T> tail = null;
	int size = 0;

	class ListNode<K> {

		private K element;
		private ListNode<K> next;

		public ListNode(K elem) {
			this.element = elem;
		}

		public ListNode(K elem, ListNode<K> next) {
			this.element = elem;
			this.next = next;
		}

		public void setElement(K element) {
			this.element = element;
		}

		public K getElement() {
			return this.element;
		}

		public void setNext(ListNode<K> next) {
			this.next = next;
		}

		public ListNode<K> getNext() {
			return this.next;
		}

	}

	public boolean append(T value) {
		ListNode<T> newNode = new ListNode<T>(value);
		ListNode<T> temp = this.head;
		// adds an item to the list at the end
		if (contains(value)) {
			return false;
		}
		if (isEmpty()) {
			this.head = newNode;
			this.tail = newNode;
			this.size++;
			return true;
		} else {
			while (temp != null) {
				if (temp.getNext() == null && temp == this.tail) {
					temp.setNext(newNode);
					this.tail = newNode;

					break;
				}
				temp = temp.getNext();
			}
		}
		// returns false if the value can not be added
		// (i.e. the value already exists in the list)

		// O(n) worst case, where n = the number of items
		// because you need to check for duplicates!
		this.size++;
		return true;
	}

	public boolean isEmpty() {
		return this.head == null;
	}

	public boolean remove(T value) {
		// remove a value from the list

		// return false if the item could not be found
		// return true if you remove the item
		if (isEmpty() || !contains(value)) {
			return false;
		}

		ListNode<T> previous = head;
		ListNode<T> current = head.getNext();

		// T dataOld = current.getElement();
		// if ((dataOld == null && value == null) || (dataOld != null &&
		// dataOld.equals(value))) {
		// ListNode<T> afterRemoved = current.getNext();
		// previous.setNext(afterRemoved);
		// if (afterRemoved == null) { // i.e. removing last element
		// tail = previous;
		// }
		// size--;
		// return true;
		// } else {
		// previous = current;
		// current = current.getNext();
		// }

		if (previous.getElement() == value) {
			if (previous.getNext() != null) {
				head = current;
				return true;
			} else {
				head = null;

				return true;
			}
		}
		while (current != null) {
			if (current.getElement().equals(value)) {
				previous.setNext(current.getNext());
				return true;
			}
			previous = previous.getNext();
			current = current.getNext();
		}

		// O(n) worst case, where n = the number of items

		return false;
	}

	@SuppressWarnings("unchecked")
	public T get(T value) {
		// return null if the item could not be found
		// return the item FROM THE LIST if it was found
		// (do not return the parameter, while the value
		// is "equal" they may not be the same in computer
		// memory... review the difference between
		// .equals() and == from CS211)
		if (isEmpty()) {
			return null;
		}

		ListNode<T> temp = this.head;
		while (temp != null) {
			if (temp.getElement().equals(value)) {
				return temp.getElement();
			}
			temp = temp.getNext();
		}

		// O(n) worst case, where n = the number of items

		return null;
	}

	public boolean contains(T value) {
		// return true if the item can be found in the
		// list, reuse code from get() to implement this
		// method
		if (isEmpty()) {
			return false;
		}
		ListNode<T> temp = this.head;
		while (temp != null) {
			if (temp.getElement().equals(value)) {
				return true;
			}
			temp = temp.getNext();
		}

		// O(n) worst case, where n = the number of items

		return false;
	}

	public int size() {
		// return the number of items in the set
		// O(1)
		return this.size;
	}

	public UniqueList<T> clone() {
		// make a copy of the UniqueList such that this is true:
		UniqueList<T> newList = new UniqueList<>();
		// if s1 is a simple set containing {1, 2, 3} and s2 is a clone of s1, then:
		// s1 != s2 BUT there exist three objects in s1 and s2 which are ==
		// see main method tests for example
		for (T ele : this) {
			newList.append(ele);
		}
		// O(n)
		return newList;
	}

	public Iterator<T> iterator() {
		// return an iterator over the list, returning the first item
		// through the last item (in that order).

		// also, you only need to implement next() and
		// hasNext() for this iterator, previous(), add(), etc.
		// are all optional on this assignment (implement them
		// if you want to use them in your code).
		return new Iterator<T>() {

			ListNode<T> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				if (hasNext()) {
					T data = current.element;
					current = current.next;
					return data;
				}
				return null;
			}
		};

	}

	// example test code... edit this as much as you want!
	public static void main(String[] args) {
		UniqueList<String> names = new UniqueList<>();

		if (names.append("Fred") && names.append("Alex") && !names.append("Fred")) {
			System.out.println("Yay 0");
		}

		if (names.size() == 2 && names.contains("Fred") && names.get("Alex").equals("Alex")) {
			System.out.println("Yay 1");
		}

		if (names.remove("Alex") && names.size() == 1 && names.get("Alex") == null) {
			System.out.println("Yay 2");
		}

		boolean hasEverything = false;
		for (String name : names) {
			if (hasEverything) {
				hasEverything = false;
				break;
			}

			hasEverything = name.equals("Fred");
		}
		if (hasEverything) {
			System.out.println("Yay 3");
		}

		class Cat {
			String name;

			public Cat(String name) {
				this.name = name;
			}

			public boolean equals(Object o) {
				if (o instanceof Cat) {
					Cat c = (Cat) o;
					return c.name.equals(this.name);
				}
				return false;
			}
		}

		UniqueList<Cat> catSet1 = new UniqueList<>();
		catSet1.append(new Cat("Sammy"));
		catSet1.append(new Cat("Grouchy"));
		UniqueList<Cat> catSet2 = catSet1.clone();
		if (catSet1 != catSet2 && catSet1.size() == catSet2.size()) {
			int matched = 0;
			for (Cat c : catSet1) {
				if (catSet2.get(c) == c)
					matched++;
			}
			if (matched == 2) {
				System.out.println("Yay 4");
			}
		}
	}
}