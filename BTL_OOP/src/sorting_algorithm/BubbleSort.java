package sorting_algorithm;

import base.BaseSort;
import base.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

public class BubbleSort extends BaseSort{
	
	public BubbleSort(JPanel container) {
		super(container);
		this.InitRandomArray(15, 1000);
	}
	
	public void Sort() {
		try {
		for (int i=0; i<elements.length-1; i++) {
			boolean swapped = false;
			for (int j=0; j<elements.length-1; j++) {
				Coloring(elements[j], Color.red);
				Coloring(elements[j+1], Color.red);
				if (elements[j].getValue()>elements[j+1].getValue()) {
					this.Swap(j, j+1);
					swapped = true;
				}
				else Thread.sleep(400);
				Coloring(elements[j], Color.blue);
				Coloring(elements[j+1], Color.blue);
			}
			if (swapped==false) break;
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		ResetValue();
	}
	
	void ResetValue()
	{
		for(Element e: elements)
		{
			e.setValue(e.getValue());
		}
	}
	
	public void Swapp(Element e, Element f, int delay)
	{
		try 
		{
			Point[] waypoint = new Point[4];
			int yTmp = e.getPosition().y + 50;
			waypoint[0] = new Point(e.getPosition().x, e.getPosition().y);
			waypoint[1] = new Point(e.getPosition().x, yTmp);
			waypoint[2] = new Point(f.getPosition().x, yTmp);
			waypoint[3] = new Point(f.getPosition().x, f.getPosition().y);
			int index = 1;
			while(index < waypoint.length)
			{
				e.setPosition(MoveTowards(e.getPosition(), waypoint[index], 10));
				f.setPosition(MoveTowards(f.getPosition(), waypoint[waypoint.length-1-index], 10));
				if(f.getPosition().equals(waypoint[waypoint.length-1-index]))
				{
					index++;
				}
				Thread.sleep(delay);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void Swap(int e1, int e2)
	{
		Element tmp = elements[e1];
		Point p = tmp.getPosition();
		elements[e1] = elements[e2];
		elements[e2] = tmp;
		this.Swapp(elements[e1],elements[e2], 30);
	}
	
	public void Swap(Element e1, Element e2)
	{
		
	}
}
