package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;


public class BaseSort {

	protected Element[] elements;
	protected JPanel container;
	protected boolean isIncrease;
	protected boolean isSorting;
	final int speed = 10;
	final int distance = 80;
	final int labelSize = 50;
	private static BaseSort currentSort;
	
	public BaseSort()
	{
		
	}
	
	public BaseSort(JPanel container)
	{
		if(currentSort != null && container.equals(currentSort.container))
		{
			currentSort.RemoveAllElements();
		}
		this.container = container;
		currentSort = this;
	}
	
	public void InitRandomArray(int size)
	{
		Dimension d = this.container.getSize();
		Point pos = new Point((d.width - distance * (size - 1) - labelSize) / 2, d.height / 2);
		elements = new Element[size];
		Random random = new Random();
		for(int i = 0 ; i < elements.length; i++)
		{
			elements[i] = new Element(random.nextInt(100));
			elements[i].setPosition(new Point(pos.x, pos.y));
			container.add(elements[i].getLabel());
			pos.x += distance;
		}
	}
	
	public void Swap(int e1, int e2)
	{
		Element tmp = elements[e1];
		elements[e1] = elements[e2];
		elements[e2] = tmp;
		Move(elements[e1], elements[e2].getPosition(), 10);
		Move(elements[e2], elements[e1].getPosition(), 10);
		Highlight(elements[e1], Color.yellow);
		Highlight(elements[e2], Color.red);
	}
	
	public void Highlight(Element e, Color color)
	{
		e.setColor(color);
	}
	
	public void Highlight(int line)
	{
		
	}
	
	public void Move(Element e, int x, int y, int delay)
	{
		try {
			int yUp = e.getPosition().y - speed * 10;
			while(e.getPosition().x != x || e.getPosition().y != y)
			{
				if(e.getPosition().x != x)
				{
					if(e.getPosition().y != yUp)
					{
						e.setPosition(e.getPosition().x, e.getPosition().y - speed);
					}
					else 
					{
						int moveRange = speed * Integer.signum(x - e.getPosition().x);
						e.setPosition(e.getPosition().x + moveRange, e.getPosition().y);
					}
				}
				else 
				{
					e.setPosition(e.getPosition().x, e.getPosition().y + speed);
				}
				Thread.sleep(delay);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void Move(Element e, Point p, int delay)
	{
		try {
			int x = p.x;
			int y = p.y;
			int num = 80 / speed;
			int yUp = e.getPosition().y - speed * num;
			while(e.getPosition().x != x || e.getPosition().y != y)
			{
				if(e.getPosition().x != x)
				{
					if(e.getPosition().y != yUp)
					{
						e.setPosition(e.getPosition().x, e.getPosition().y - speed);
					}
					else 
					{
						int moveRange = speed * Integer.signum(x - e.getPosition().x);
						e.setPosition(e.getPosition().x + moveRange, e.getPosition().y);
					}
				}
				else 
				{
					e.setPosition(e.getPosition().x, e.getPosition().y + speed);
				}
				Thread.sleep(delay);
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void MoveUp(Element e, int moveRange, int delay)
	{
		Move(e, e.getPosition().x, e.getPosition().y - moveRange, delay);
	}
	
	public void MoveDown(Element e, int moveRange, int delay)
	{
		Move(e, e.getPosition().x, e.getPosition().y + moveRange, delay);
	}
	
	public void Sort()
	{
		
	}

	public JPanel getContainer()
	{
		return this.container;
	}
	
	public Element getElement(int index)
	{
		return elements[index];
	}
	
	public void RemoveAllElements()
	{
		for(int i = 0; i < elements.length; i++)
		{
			this.container.remove(elements[i].getLabel());
		}
	}
}
