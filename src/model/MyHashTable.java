/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-------
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: March, 21th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-------
*/
package model;

public class MyHashTable<K extends Number,V> implements MyHashTableInterface<K,V> {

	public static final int AAR_SIZE = 16;
	public static final double A = (Math.sqrt(5)-1.0)/2.0;
	private HNode<K,V>[] nodes; 
	
	// -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

	
	// -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

	/**
	 * Name: Shelf
	 * Constructor method of a shelf.
	 * @param identifier - shelf's identifier - identifier = String, identifier != null, identifier != ""
	 * @param bookNum - number of book in a shelf - bookNum = int, bookNum != null, bookNum != 0
	*/
	public MyHashTable() {
		nodes=(HNode<K,V>[]) new Object[AAR_SIZE]; //Discutir
	}

	@Override
	public void insert(K key, V value) {
		int index=hash((int)key);
		HNode<K,V> node = new HNode<>(key, value);
		if(nodes[index]==null) {
			nodes[index] = node;
		}else {
			HNode<K,V> temp = nodes[index];
			while(temp.getNext()!=null) {
				temp=temp.getNext();
			}
			temp.setNext(node);
			node.setPrev(node);
		}
	}

	@Override
	public HNode<K,V> search(K key) {
		int index=hash((int)key);
		boolean found=true;
		HNode<K,V> temp = nodes[index];
		if(temp!=null) {
			while(temp.getKey()!=key && found) {
				if(temp.getPrev()!=null) {
					temp=temp.getNext();
				}else {
					found=false;
				}
			}
		}else {
			found=false;
		}
		if(found) {
			return temp;
		}else {
			return null;
		}
	}

	@Override
	public void delete(K key) {
		HNode<K,V> nodeToDelete = search(key);
		if(nodeToDelete!=null) {
			HNode<K,V> prev=nodeToDelete.getPrev();
			HNode<K,V> next=nodeToDelete.getNext();
			if(prev==null) {
				int index=hash((int)key);
				next.setPrev(null);
				nodes[index]=next;
			}else {
				if(next==null) {
					prev.setNext(null);
				}else {
					prev.setNext(next);
					next.setPrev(prev);
				}
			}
		}
		
	}

	public int hash(int k) {
		int index = (int)Math.floor(AAR_SIZE*((k*A)%1));
		return index;
	}
}