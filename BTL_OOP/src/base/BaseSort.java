package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;
import window.CompleteSortWindow;
import window.MainWindow;

public class BaseSort {

	protected Element[] elements;
	protected JPanel container;
	protected boolean isIncrease;
	protected boolean isSorting;//kiemtra
	final int speed = 10;
	final int distance = 80;
	final int labelSize = 50;
	final int delayFrame = 10; //miliseconds
	public static BaseSort currentSort;
	
	public BaseSort()
	{
		
	}
	
	public BaseSort(JPanel container)
	{
		this.container = container;
		this.elements = MainWindow.frame.elements;
		currentSort = this;
	}
	
	
	
	public void Swap(int e1, int e2)
	{
		Element tmp = elements[e1];
		elements[e1] = elements[e2];
		elements[e2] = tmp;
		Move(elements[e1], elements[e2].getPosition(), 10);
		Move(elements[e2], elements[e1].getPosition(), 10);
		Coloring(elements[e1], Color.yellow);
		Coloring(elements[e2], Color.red);
	}
	
	public void Swap(Element e1, Element e2)
	{
		
	}
	
	public void Coloring(Element e, Color color)
	{
		e.setColor(color);
	}
	
	public void Highlight(int line)
	{
		
	}
	
	public void Move(Element e, int x, int y, int delay)
	{
		Move(e, new Point(x, y), delay);
	}
	
	public void Move(Element e, Point p, int delay)
	{
		
	}
	

	public Point MoveTowards(Point current, Point target, int maxDistanceDelta)
	{
		Point result;
		Point a = new Point(target.x - current.x, target.y - current.y);
		double magnitude = Math.sqrt(a.x * a.x + a.y * a.y);
		if(magnitude <= maxDistanceDelta || magnitude == 0f)
		{
			result = target;
		}
		else 
		{
			result = new Point(current.x + (int)(a.x * maxDistanceDelta / magnitude),
					current.y + (int)(a.y * maxDistanceDelta / magnitude));
		}
		return result;
	}
	
	public void MoveFollowWaypoints(Element e, Point[] waypoints, int speed)
	{
		try 
		{
			int index = 0;
			while(index < waypoints.length)
			{
				if(e.getPosition().equals(waypoints[index]))
				{
					index++;
				}
				e.setPosition(MoveTowards(e.getPosition(), waypoints[index], speed));
				Thread.sleep(delayFrame);
			}
		}
		catch(Exception ex)
		{
			
		}
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
	
	public int[] getValue()
	{
		int[] res = new int[elements.length];
		for(int i = 0; i < res.length; i++)
		{
			res[i] = elements[i].getValue();
		}
		return res;
	}
	
	public void ShowCompleteSortDialog()
	{
		CompleteSortWindow window = new CompleteSortWindow();
		window.setVisible(true);
	}
}
