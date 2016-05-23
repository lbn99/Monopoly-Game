public interface IList<E>
{
	public int size();
	public boolean add(E obj);
	public void add(int index, E obj);
	public E set(int index, E obj);
	public E get(int index);
	public E remove(int index);
	
	public String toString();
}