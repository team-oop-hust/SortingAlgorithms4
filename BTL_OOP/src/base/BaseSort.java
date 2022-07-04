package base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemColor;
import java.util.Random;
import javax.swing.JPanel;
import window.CompleteSortWindow;
import window.MainWindow;

public class BaseSort {

	protected Element[] elements;
	protected JPanel container;
	protected boolean isSorting;//kiemtra
	final int speed = 10;
	final int distance = 80;
	final int labelSize = 50;
	final int delayFrame = 10; //miliseconds
	public static BaseSort currentSort;
	
	protected Color processingColor = new Color(255, 153, 153);
	protected Color selectedGreen = new Color(153, 255, 153);
	protected Color selectedYellow = new Color(255, 255, 153);
	
	public BaseSort()
	{
		
	}
	
	public BaseSort(JPanel container)
	{
		this.container = container;
		this.elements = MainWindow.frame.elements;
		currentSort = this;
		Init();
	}
	
	public void Init()
	{
		
	}
	
	public void Swap(int e1, int e2) {
		try
		{
			if(e1 == e2)
			{
				Coloring(elements[e1], this.processingColor);
				Thread.sleep(200);
				Coloring(elements[e1], SystemColor.blue);
				return;
			}
			Coloring(elements[e1], this.selectedYellow);
			Coloring(elements[e2], this.selectedYellow);
			Element tmp = elements[e1];
			elements[e1] = elements[e2];
			elements[e2] = tmp;
			this.Move(elements[e1], elements[e2], 30);
			Coloring(elements[e1], SystemColor.blue);
			Coloring(elements[e2], SystemColor.blue);
		}
		catch(Exception e)
		{
			
		}
	}

	public void Coloring(Element e, Color color)
	{
		e.setColor(color);
	}
	
	public void Highlight(int line)
	{
		
	}
	
	public void Move(Element e, Point p, int delay)
	{
		
	}
	
	public void Move(Element e, Element f, int delay)
	{
		try 
		{
			Point[] waypoint1 = new Point[3];
			int yTmp = e.getPosition().y + 50;
			waypoint1[0] = new Point(e.getPosition().x, yTmp);
			waypoint1[1] = new Point(f.getPosition().x, yTmp);
			waypoint1[2] = new Point(f.getPosition().x, f.getPosition().y);
			
			Point[] waypoint2 = new Point[3];
			int yTmp2 = f.getPosition().y - 50;
			waypoint2[0] = new Point(f.getPosition().x, yTmp2);
			waypoint2[1] = new Point(e.getPosition().x, yTmp2);
			waypoint2[2] = new Point(e.getPosition().x, e.getPosition().y);
			
			int index1 = 0, index2 = 0;
			while(index1 < waypoint1.length || index2 < waypoint2.length)
			{
				e.setPosition(MoveTowards(e.getPosition(), waypoint1[index1], 10));
				f.setPosition(MoveTowards(f.getPosition(), waypoint2[index2], 10));
				
				if(e.getPosition().equals(waypoint1[index1]))
				{
					index1++;
				}
				if(f.getPosition().equals(waypoint2[index2]))
				{
					index2++;
				}
				Thread.sleep(delay);
			}
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
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
		if(MainWindow.frame.isIncrease)
		{
			SortIncrease();
		}
		else 
		{
			SortDecrease();
		}
		MainWindow.frame.End();
	}
	
	public void SortIncrease()
	{
		
	}
	
	public void SortDecrease()
	{
		
	}
	
	public void UpdateSubElements()
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
		RemoveSubElements();
	}
	
	public void RemoveSubElements()
	{
		
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
	
	public void setElements(Element[] elements) 
	{
		this.elements = elements;
	}
	
	public void ShowCompleteSortDialog()
	{
		CompleteSortWindow window = new CompleteSortWindow();
		window.setVisible(true);
	}
	
	public void HighlightRow(int line)
	{
		MainWindow.frame.HighlightRow(line);
	}
}
