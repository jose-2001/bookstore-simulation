/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-------
 * @Authors: Juan Pablo Ramos, Juan Esteban Caicedo and Jose Alejandro García
 * @Date: March, 21th 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-------
*/
package model;

import exceptions.MyQueueException;

public class MyQueue<T> implements MyQueueInterface<T> {

    // -----------------------------------------------------------------
	// Attributes
    // -----------------------------------------------------------------

    private int length;

    // -----------------------------------------------------------------
	// Relations
    // -----------------------------------------------------------------

    private Node<T> front;
    private Node<T> back;

    // -----------------------------------------------------------------
	// Methods
    // -----------------------------------------------------------------

    /**
	 * Name: MyQueue
	 * Constructor method of a queue.
	*/
    public MyQueue() {
        length = 0;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setFront(Node<T> front) {
        this.front = front;
    }

    public Node<T> getBack() {
        return back;
    }

    public void setBack(Node<T> back) {
        this.back = back;
    }

    @Override
    public void enqueue(T value) {
        Node<T> newNode = new Node<T>(value);
        if (isEmpty()) {
            front = newNode;
            back = newNode;
        } else {
            newNode.setNextNode(back);
            back.setPrevNode(newNode);
            back = newNode;
        }
        length++;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public T getFront() throws MyQueueException {
        if (isEmpty())
            throw new MyQueueException("There is nothing in the queue");
        else
            return front.getValue();
    }

    @Override
    public T dequeue() throws MyQueueException {
        if (isEmpty())
            throw new MyQueueException("There is nothing in the queue");
        else {
            T value = front.getValue();
            if (getLength() == 1) {
                front = null;
                back = null;
            } else {
                front.getPrevNode().setNextNode(null);
                front = front.getPrevNode();
            }
            length--;
            return value;
        }
    }
}