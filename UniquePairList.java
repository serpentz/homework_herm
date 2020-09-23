class UniquePairList<K,V> {
	private static class Pair<K,V> {

		private K key;
		private V value;
		
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			//Two pairs are equal ONLY if their KEYS are
			//equal. Values don't matter for equality.
			//O(1)
			return (((Pair<K,V>)o).getKey() == getKey());
		}
		
		@Override
		public String toString() {
			//this method is done for you
			return "<" + getKey() + "," + getValue() + ">";
		}
		
		public K getKey() {
			//returns the key from the pair
			//O(1)
			return this.key;
		}
		
		public V getValue() {
			//returns the value from the pair
			//O(1)
			return this.value;
		}
	}
	
	//example test code... edit this as much as you want!
	public static void main(String[] args) {
		Pair<String,Integer> p1 = new Pair<>("Fred", 1);
		Pair<String,Integer> p2 = new Pair<>("Alex", 1);
		Pair<String,Integer> p3 = new Pair<>("Fred", 2);
		
		if(p1.getKey().equals("Fred") && p1.getValue() == 1 && p1.equals(p3)) {
			System.out.println("Yay 1");
		}
		
		if(!p1.equals(p2)) {
			System.out.println("Yay 2");
		}
		
		//this is actually a test of UniqueList, not UniquePairList
		UniqueList<Pair<String,Integer>> set = new UniqueList<>();
		set.append(p1);
		
		//get the value from the set that is _equal to_ p3 (in this case, p1)
		Pair<String,Integer> p1fromSet = set.get(p3);
		if(p1fromSet.getValue() == 1) {
			System.out.println("Yay 3");
		}
	}
	
	/*****************************************************************/
	/****************** DO NOT EDIT BELOW THIS LINE ******************/
	/********************* EXCEPT TO ADD JAVADOCS ********************/
	/*****************************************************************/
	
	private UniqueList<Pair<K,V>> set = new UniqueList<>();
	
	public boolean append(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		return set.append(pair);
	}
	
	public boolean update(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		if(!remove(key)) {
			return false;
		}
		return set.append(pair);
	}
	
	@SuppressWarnings("unchecked")
	public boolean remove(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.remove(pair);
	}
	
	@SuppressWarnings("unchecked")
	public V getValue(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.get(pair).getValue();
	}
	
	public UniqueList<K> getKeys() {
		UniqueList<K> keySet = new UniqueList<>();
		for(Pair<K,V> p : set) {
			keySet.append(p.getKey());
		}
		return keySet.clone();
	}
	
	public int size() {
		return set.size();
	}
}