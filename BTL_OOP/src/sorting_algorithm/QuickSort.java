package sorting_algorithm;

import base.BaseSort;
import base.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

public class QuickSort extends BaseSort {
	public QuickSort(JPanel container) {
		super(container);
	}

	public void Sort() {
		int high = this.elements.length -1;
		this.Progress(0, high);
	}
	
	public int partition(int low, int high)
	{
		Coloring(elements[high], Color.red);
	    int pivot = elements[high].getValue();
	    int i = (low - 1);
	    for(int j = low; j <= high - 1; j++)
	    {
	        if (elements[j].getValue() < pivot)
	        {
	            i++;
	            Swap(i, j);
	        }
	    }
	    Swap(i + 1, high);
	    Coloring(elements[high], Color.blue);
	    return (i + 1);
	}

	public void Progress(int low, int high) {
		
		if (low < high)
	    {
	        int pi = partition(low, high);
			Progress(low, pi - 1);
			Progress(pi + 1, high);
	    }
	}

	public void Move(Element e, Element f, int delay) {
		try {
			Point[] waypoint = new Point[4];
			int yTmp = e.getPosition().y + 50;
			waypoint[0] = new Point(e.getPosition().x, e.getPosition().y);
			waypoint[1] = new Point(e.getPosition().x, yTmp);
			waypoint[2] = new Point(f.getPosition().x, yTmp);
			waypoint[3] = new Point(f.getPosition().x, f.getPosition().y);
			int index = 1;
			while (index < waypoint.length) {
				e.setPosition(MoveTowards(e.getPosition(), waypoint[index], 10));
				f.setPosition(MoveTowards(f.getPosition(), waypoint[waypoint.length - 1 - index], 10));
				if (f.getPosition().equals(waypoint[waypoint.length - 1 - index])) {
					index++;
				}
				Thread.sleep(delay);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void Swap(int e1, int e2) {
		try
		{
			if(e1 == e2)
			{
				Coloring(elements[e1], Color.yellow);
				Thread.sleep(200);
				Coloring(elements[e1], Color.blue);
				return;
			}
			Coloring(elements[e1], Color.yellow);
			Coloring(elements[e2], Color.yellow);
			Element tmp = elements[e1];
			elements[e1] = elements[e2];
			elements[e2] = tmp;
			this.Move(elements[e1], elements[e2], 30);
			Coloring(elements[e1], Color.blue);
			Coloring(elements[e2], Color.blue);
		}
		catch(Exception e)
		{
			
		}
	}
}
