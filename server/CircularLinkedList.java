public class CircularLinkedList<E> implements IList<E>
{
	Node<E> head;
	Node<E> tail;
	private int p = 0;
	public CircularLinkedList()
	{
		head = null;;
		tail = null;
	}
	public int size(){
		if(head==null)
			return 0;
		int count = 1;
		Node<E> node = head.getNext();
		while(node!=head)
		{
			count++;
			node = node.getNext();
		}
		return count;	
	}
	public boolean add(E obj){
		Node<E> node = new Node(obj, null);
		if(head==null)
		{
			tail = node;
			head = node;
		}
		else
		{
			tail.setNext(node);
			tail = node;
			tail.setNext(head);
		}
		return true;
	}
	public void add(int index, E obj){
		Node<E> node = head.getNext();
		Node<E> a = new Node(obj, null);
		if(index>this.size() || index < 0)
			throw new IndexOutOfBoundsException("Index was out of bound");
		
		if(index == 0)
		{
			a.setNext(head);
			head = a;
			tail.setNext(head);
		}
		else{
			node = head.getNext();
			for(int i = 1; node!=head && i < index; i++)
			{
				node = node.getNext();
			}
			a.setNext(node);
	//		System.out.println("Before second loop: " + this.toString());
			node = head;
	//		System.out.println("NODE IS " + node.getData() + "Head is " + head.getData());
			for(int i = 0; i < index - 1; i++)
			{
	//			System.out.println("Inside the loop");
				node=node.getNext();
			}
	//		System.out.println("NODE IS " + node.getData() + "Head is " + head.getData() + "a is : " + a.getData());
	//		System.out.println("a.getNext().getData()" + a.getNext().getData());
			node.setNext(a);
	//		System.out.println("After second loop: " + this.toString());
		}
	}
	public E set(int index, E obj){
		if(index>=this.size() || index < 0)
			throw new IndexOutOfBoundsException("Index was out of bound");
		Node<E> node = head;
		Node<E> temp;
		Node<E> a = new Node(obj, null);
		if(index==0)
		{
			node = node.getNext();
			temp = head;
			head = a;
			head.setNext(node);
			return temp.getData();
		}
		for(int i = 0; node!=null && i < index; i++)
		{
			node = node.getNext();
		}
		temp = node;
		node.setData(obj);
		return temp.getData();
	}
	
	public E getNext()
	{
		Node<E>  node = head;
		p++;
		for(int i = 1; i < p; i++)
		{
			node = node.getNext();
		}
		return node.getData();
	}
	
	public E get(int index){
		if(index>=this.size() || index < 0)
			throw new IndexOutOfBoundsException("Index was out of bound");
		Node<E> node = head;
		for(int i = 0; node != null && i < index; i++)
			node = node.getNext();
		return node == null ? null : node.getData();
	}
	public E remove(int index){
		if(index>=this.size() || index < 0)
			throw new IndexOutOfBoundsException("Index was out of bound");
		Node<E> node = head.getNext();
		Node<E> temp;
		for(int i = 1; i < index - 1 && node!=head; i++)
		{
			node = node.getNext();
		}
		if(index==0)
		{	
			temp = head;
			head = temp.getNext();
			tail.setNext(head);
			return temp.getData();
		}
		temp = node.getNext();
		System.out.println("TEMP IS : " + temp.getData());
		if(temp.getNext() == head)
		{
			tail = node;
		}
		if(temp.getNext()==head)
			System.out.println("HEAD IS THE NEXT THING " );
		node.setNext(temp.getNext());
		
		return temp.getData();
	}
	public String toString(){
		String s = "[";
		if(head!=null){
			Node<E> node = head;
			while (node.getNext()!=head)
			{
				s += node.getData()+", ";
				node = node.getNext();
			}
			s+=node.getData()+ "]";
		}
		else
			s+="]";
		return s;
	}
}
class Node<E>
{
	Object data;
	Node<E> next;	
	public Node(E d, Node<E> n)
	{
		data = d;
		next = n;
	}
	public void setData(E obj)
	{
		data = obj;
	}
	public E getData()
	{
		return (E)data;
	}
	public void setNext(Node<E> n)
	{
		next = n;
	}
	public Node<E> getNext()
	{
		return next;
	}
}