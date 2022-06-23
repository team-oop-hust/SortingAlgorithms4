package sorting_algorithm;

import base.BaseSort;
import base.Element;
import base.element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Random;
import javax.swing.JPanel;

public class QuickSort extends BaseSort{
	//
	int delay=30;
	public void Progress(int low, int high) {
		int j=high;
		Coloring(elements[high],red);
		for(int i=0;i<high;i++) {
			if(elements[i].getValue()<elements[j].getValue()) {
				Swap(i);j--;
			}
		}
		Progress(low,j-1);
		Progress(j+1,high);
		}
	public void Move(int p, Element[]arr)
	{
		try 
		{
			Point[] waypoint = new Point[3];
			int High = arr.length-1;
			yTmp=arr[p].getPosition().y+50;
			waypoint[0] = new Point(arr[p].getPosition().x, yTemp);
			waypoint[1] = new Point(arr[High].getPosition().x, yTmp);
			waypoint[2] = new Point(arr[High].getPosition().x,arr[High].getPosition().y);
			for(int i=p+1;p<High;p++) {
			Point[] temp = new Point[1];
			temp[0] = new Point(arr[i-1].getPosition().x,arr[i-1].getPosition().y);
			int index0=0;
			while(index0 < temp.length)
			{
			arr[i].setPosition(MoveTowards(arr[i].getPosition(),temp, 10));}
			if(arr[i].getPosition().equals(temp[index0]))
				{
					index0++;
				}
			Thread.sleep(delay);
		}	
			int index = 0;
			while(index < waypoint.length)
			{
				arr[p].setPosition(MoveTowards(arr[p].getPosition(),waypoint, 10));
				if(e.getPosition().equals(waypoint[index]))
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

	public void Swap(int p)
	{
		int High=elements.lenght-1;
		for(int i=p+1;p<High;p++) {
		Element tmp = elements[i];
		elements[i] = elements[i+1];
		elements[i+1] = tmp;}
		elements[High]=element[p];
		this.Move(p,elements);
	}
    public void Sort() {
    	try {
    		int high=elements.lenght-1;
    		Progress(0,high);
    		}
    		catch(Exception ex)
    		{
    			ex.printStackTrace();
    		}
    		ResetValue();
    	}
	public QuickSort(JPanel container) {
		super(container);
		this.InitRandomArray(15, 1000);
	}
}
