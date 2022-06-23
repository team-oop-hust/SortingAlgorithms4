package sorting_algorithm;

import base.BaseSort;
import base.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;


public class QuickSort extends BaseSort{
	public QuickSort(JPanel container) {
		super(container);
		this.InitRandomArray(15, 1000);
	}
	
	public void Sort() {
		int high=this.elements.length;
		this.Progress(this.elements,0,high);
	}
	public void Progress(Element[] elements,int low, int high){
    int pivot = elements[high].getValue();
    int i = low - 1;
    for(int j = low; j <= high - 1; j++)
    {
        if (elements[j].getValue() < pivot)
        {
            i++;
            this.Swap(i,j);
        }
    }
    this.Swap(i + 1, high);
	Progress(elements,low, i);
    Progress(elements,i+2, high);
}
 
	public void Move(Element e, Element f, int delay)
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
		Coloring(elements[e1], Color.yellow);
		Coloring(elements[e2], Color.yellow);
		Element tmp = elements[e1];
		elements[e1] = elements[e2];
		elements[e2] = tmp;
		this.Move(elements[e1],elements[e2], 30);
		Coloring(elements[e1], Color.blue);
		Coloring(elements[e2], Color.blue);
	}
}
