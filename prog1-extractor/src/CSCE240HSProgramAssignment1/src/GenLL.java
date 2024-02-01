/*
 * Jacob Walton
 */
public class GenLL <T> {
	private class ListNode
	{
		T data;
		ListNode link;//Ref to the next node
		public ListNode(T aData, ListNode aLink)
		{
			data = aData;
			link = aLink;
		}
	}
	
	private ListNode head;//Ref to the first element
	private ListNode current;//Current reference of interest. Movable.
	private ListNode previous;//One Node behind current. IE previous.link = current
	public GenLL()
	{
		head = current = previous = null;
	}
	public void add(T aData)
	{
		ListNode newNode = new ListNode(aData,null);
		if(head == null)//empty list
		{
			head = current = newNode;
			return;
		}
		ListNode temp = head;
		while(temp.link != null)
			temp = temp.link;
		temp.link = newNode;
	}
	public void print()
	{
		for(ListNode temp = head; temp != null; temp = temp.link)
			System.out.println(temp.data);
		System.out.println();
	}
	public void addAfterCurrent(T aData)
	{
		if(current == null)
			return;
		ListNode newNode = new ListNode(aData,current.link);
		current.link = newNode;
	}
	public T getCurrent()
	{
		if(current == null)
			return null;
		return current.data;
	}
	public void setCurrent(T aData)
	{
		if(aData == null || current == null)
			return;
		current.data = aData;
	}
	public void gotoNext()
	{
		if(current == null)
			return;
		previous = current;
		current = current.link;
	}
	public void reset()
	{
		current = head;
		previous = null;
	}
	public boolean hasMore()
	{
		return current != null;
	}
	public void removeCurrent()
	{
		if(current == head)
		{
			head = head.link;
			current = head;
		}
		else
		{
			previous.link = current.link;
			current = current.link;
		}
	}
}
