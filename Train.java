import java.util.Iterator;

class Train implements Iterable<Car> {
	protected Car head;
	protected Car tail;
	int size = 0;
	String name;

	public Train(String name) {
		this.name = name;
		head = null;
		tail = null;
		size = 0;
	}

	public String getName() {
		// returns the train's name
		// O(1)
		return this.name;
	}

	public Iterator<Car> iterator() {
		// returns an iterator which traverses
		// the train from the first car (the one closest
		// to the front of the train) to the last car
		// use an anonymous class here
		// required iterator methods: next() and hasNext()
		// both methods are required to work in O(1) time
		return new Iterator<Car>() {

			Car current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Car next() {
				if (hasNext()) {
					Car temp = current;
					current = current.getNext();
					return temp;
				}
				return null;
			}
		};
	}

	public boolean equals(Object o) {
		// two trains are equal if they have the same name
		// O(1)
		return getName().equals(((Train) o).toString());
	}

	public void connectCar(Car c) {
		// connects the car to the end of the cars for this train
		// requied Big-O: O(n) where n=the length of the linked list
		// of cars starting at c, NOT n=the number of cars already
		// connected to this train.
		Car newNode = c;

		// If list is empty
		if (head == null) {
			// Both head and tail will point to newNode
			head = newNode;
			tail = newNode;

			if (newNode.getNext() != null) {
				head.next = newNode.next;
				tail = newNode.next;
			}
			// head's previous will point to null
			head.previous = null;
			// tail's next will point to null, as it is the last node of the list
			tail.next = null;
		} else {
			// newNode will be added after tail such that tail's next will point to newNode
			tail.next = newNode;
			// newNode's previous will point to tail
			newNode.previous = tail;
			// newNode will become new tail
			tail = newNode;
			// As it is last node, tail's next will point to null
			tail.next = null;
		}
	}

	public Car disconnectCar(Car c) {
		// returns the car disconnected from the train
		// should throw the following exception if the car isn't on
		// the train: RuntimeException("Can not disconnect a car that doesn't exist");
		// required Big-O: O(n) where n=the number of cars on this train
		// Car disconectedCar;
		// if(!list.contains(c)){
		// throw new RuntimeException("Can not disconnect a car that doesn't exist");
		// }
		// disconectedCar = list.get(c);
		// list.remove(c);

		if (head == null) {
			throw new RuntimeException("Can not disconnect a car that doesn't exist");
		}

		if (head == tail && c.equals(head)&& c.equals(tail)) {
			head = null;
			tail = null;
		} else if (c == tail) {
			RemoveAtEnd();
		} else if (c == head) {
			RemoveAtStart();
		} else {
			Car current = head;
			while (current != c) {
				current = current.getNext();
			}
			current.getPrevious().setNext(null);
			tail = current.getPrevious();
			current = null;
		}
		return c;
	}

	void RemoveAtStart() {

		
			head = null;
			tail = null;
		
	}

	void RemoveAtEnd() {

		if (tail == head) {
			tail = null;
			head = null;
		} else {
			tail = tail.getPrevious();
			tail.setNext(null);
			;
		}
	}

	public void reverseTrain() {
		// reconnects all the cars on the train in the reverse order
		// that they currently are (e.g. changes C1->C2->C3 to
		// C3->C2->C1). You may find using a second train to do this useful.
		// required Big-O: O(n) where n=the number of cars on this train
		// careful not to end up with O(n^2) which is easy to do by calling O(n)
		// methods in a loop
	}

	// example test code... edit this as much as you want!
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");

		c1.setNext(c2);
		c2.setPrevious(c1);

		Train t1 = new Train("T1");
		Train t1b = new Train("T1");

		if (t1.getName().equals("T1") && t1.equals(t1b)) {
			System.out.println("Yay 1");
		}

		t1.printAscii();

		t1.connectCar(c1);
		t1.printAscii();

		Car c3 = new Car("C3");
		Car c4 = new Car("C4");

		c3.setNext(c4);
		c4.setPrevious(c3);

		t1.connectCar(c3);
		t1.connectCar(c4);
		t1.printAscii();

		t1.reverseTrain();
		t1.printAscii();
	}

	/*****************************************************************/
	/****************** DO NOT EDIT BELOW THIS LINE ******************/
	/*****************************************************************/

	public String toString() {
		String s = getName();
		for (Car c : this) {
			s += " " + c;
		}
		return s;
	}

	public void printAscii() {
		/*
		 * From: http://www.ascii-art.de/ascii/t/train.txt o O___ _________ _][__|o| |O
		 * O O O| <_______|-|_______| /O-O-O o o
		 */

		System.out.print(String.format("%-4s", getName()) + "o O___");
		for (Car c : this) {
			System.out.print(" _________");
		}
		System.out.println();

		System.out.print("  _][__|o|");
		for (Car c : this) {
			System.out.print(" | " + String.format("%-5s", c.getName()) + " |");
		}
		System.out.println();

		System.out.print(" |_______|");
		for (Car c : this) {
			System.out.print("-|_______|");
		}
		System.out.println();

		System.out.print("  /O-O-O  ");
		for (Car c : this) {
			System.out.print("   o   o  ");
		}
		System.out.println();
	}
}