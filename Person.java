class Person {
	Car currentCar;
	String name;

	public Person(String name, Car currentCar) {
		this.name = name;
		this.currentCar = currentCar;
	}

	public String getName() {
		// returns the name set in the constructor
		// O(1)
		return this.name;
	}

	public Car getCurrentCar() {
		// returns the current car
		// O(1)
		return this.currentCar;
	}

	public boolean moveToCar(Car c) {
		// Tries to move the person from their current car
		// to the car passed in as a parameter. If the two
		// cars are not adjacent, returns false (and the
		// person remains in their current car). Returns
		// true if the person was able to move.
		// O(1)
		Car pastCar = currentCar.getPrevious();
		Car nextCar = currentCar.getNext();
		
		while(pastCar != null){
			if(pastCar.equals(c)){
				this.currentCar =c;
				return true;
			}
			pastCar = pastCar.getPrevious();
		}

		while(nextCar != null){
			if(nextCar.equals(c)){
				this.currentCar =c;
				return true;
			}
			nextCar = nextCar.getNext();
		}

		return false;
	}

	public boolean equals(Object o) {
		// two people are "equal" if they have the same name
		// O(1)
		return (((Person) o).getName().equals(getName()));
	}

	public String toString() {
		// returns the person's name
		// O(1)
		return getName();
	}

	// example test code... edit this as much as you want!
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		Car c3 = new Car("C3");

		c1.setNext(c2);
		c2.setPrevious(c1);
		c2.setNext(c3);
		c3.setPrevious(c2);

		Person p1 = new Person("P1", c1);

		if (p1.getCurrentCar().equals(c1) && p1.getName().equals("P1")) {
			System.out.println("Yay 1");
		}

		if (p1.moveToCar(c3) && p1.getCurrentCar().equals(c3) && p1.moveToCar(c1) && p1.getCurrentCar().equals(c1)) {
			System.out.println("Yay 2");
		}

		Person p1b = new Person("P1", c1);
		if (p1.equals(p1b) && !p1.equals(new Person("P2", c1))) {
			System.out.println("Yay 3");
		}
	}
}