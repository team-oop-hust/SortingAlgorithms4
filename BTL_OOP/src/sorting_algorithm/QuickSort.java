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
	int delay=30;
	public void Progress(int low, int high,Element[]arr) {
		int j=high;
		Coloring(arr[high],red);
		for(int i=0;i<high;i++) {
			if(arr[i].getValue()<arr[j].getValue()) {
				Swap(i,arr);j--;
			}
		}
		Progress(low,j-1);
		Progress(j+1,high);
		if(high=1) break;
		}
	public void Move(int p, Element[]arr)
	{
		try 
		{
			Point[] waypoint = new Point[3];
			int High = arr.length-1;
			int yTmp=arr[p].getPosition().y+50;
			waypoint[0] = new Point(arr[p].getPosition().x, yTemp);
			waypoint[1] = new Point(arr[High].getPosition().x, yTmp);
			waypoint[2] = new Point(arr[High].getPosition().x,arr[High].getPosition().y);
			for(int i=p+1;p<High;p++) {
			Point[] temp = new Point[1];
			temp[0] = new Point(arr[i-1].getPosition().x,arr[i-1].getPosition().y);
			arr[i].setPosition(MoveTowards(arr[i].getPosition(),temp, 10));
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

	public void Swap(int p,Element[]arr)
	{
		int High=arr.lenght-1;
		Element temp=arr[p];
		for(int i=p+1;p<High;p++) {
		arr[i] = arr[i+1];
		}
		arr[High]=temp;
		this.Move(p,arr);
	}
    public void Sort() {
    	try {
    		int high=elements.lenght-1;
    		Progress(0,high,elements);
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
