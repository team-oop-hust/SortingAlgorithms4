package sorting_algorithm;

import base.BaseSort;
import base.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RadixSort extends BaseSort{

	Point[] boxPos;
	Point[] arrayPos;
	JLabel[] labelBox;
	
	public RadixSort(JPanel container) {
		super(container);
//		this.InitRandomArray(15, 1000);
		initBoxPos();
		initArrayPos();
		System.out.println("Init");
	}
	
	void initBoxPos()
	{
		boxPos = new Point[10];
		labelBox = new JLabel[10];
		int x = (container.getSize().width - 950) / 2;
		int y = 280;
		for(int i = 0; i < 10; i++)
		{
			boxPos[i] = new Point(x, y);
			labelBox[i] = new JLabel("" + i);
			labelBox[i].setSize(90, 160);
			labelBox[i].setOpaque(true);
			labelBox[i].setLocation(x - 20, y - 90);
			labelBox[i].setFont(new Font("Helvetica", Font.BOLD, 17));
			labelBox[i].setBackground(Color.GRAY);
			labelBox[i].setHorizontalAlignment(SwingConstants.CENTER);
		    labelBox[i].setVerticalAlignment(SwingConstants.BOTTOM);
		    container.add(labelBox[i]);
			x += 100;
		}
	}
	
	void initArrayPos()
	{
		arrayPos = new Point[elements.length];
		for(int i = 0; i < arrayPos.length; i++)
		{
			arrayPos[i] = new Point(elements[i].getPosition());
		}
	}
	
	
	
	
	public void Sort()
	{
		int m = getMax(elements);
	    for (int exp = 1; m / exp > 0; exp *= 10)
	    {
	    	Highlight((int)Math.log10(exp));
	    	countSort(exp);
	    }
	    ResetValue();
	}
	
	public void Swap(Element e1, Element e2)
	{
		
	}
	
	int getMax(Element[] arr)
	{
	    int mx = arr[0].getValue();
	    for (int i = 1; i < arr.length; i++)
	    {
	    	int num = arr[i].getValue();
	        if (num > mx)
	            mx = num;
	    }
	    return mx;
	}
	
	void countSort(int exp)
	{
		int length = elements.length;
	    Element[] output = new Element[length];
	    int i;
	    int[] count = new int[10];
	  
	    for (i = 0; i < length; i++)
	    {
	    	int index = (elements[i].getValue() / exp) % 10;
	    	count[index]++;
	    	MoveToBox(elements[i], index);
	    }
	    
	    for(i = 0; i < 10; i++)
	    {
	    	boxPos[i].y += count[i] * 60;
	    }
	    
	    for (i = 1; i < 10; i++)
	        count[i] += count[i - 1];
	  
	    for (i = length - 1; i >= 0; i--) {
	    	int index = count[(elements[i].getValue() / exp) % 10] - 1;
	        output[index] = elements[i];
	        count[(elements[i].getValue() / exp) % 10]--;
	    }
	  
	    for (i = 0; i < length; i++)
	    {
	    	elements[i] = output[i];
	    	MoveToArray(elements[i], i);
	    }
	}
	  

	void MoveToBox(Element e, int index)
	{
		Coloring(e, Color.green);
		Move(e, boxPos[index], 10);
  
		boxPos[index].y -= 60;
		Coloring(e, Color.blue);
	}
	
	@Override
	public void Move(Element e, Point p, int delay)
	{
		try 
		{
			Point[] waypoint = new Point[3];
			int yTmp = (e.getPosition().y + p.y) / 2;
			waypoint[0] = new Point(e.getPosition().x, yTmp);
			waypoint[1] = new Point(p.x, yTmp);
			waypoint[2] = new Point(p.x, p.y);
			int index = 0;
			while(index < waypoint.length)
			{
				e.setPosition(MoveTowards(e.getPosition(), waypoint[index], 10));
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
	
	public void Highlight(int row)
	{
		for(Element e : elements)
		{
			int num = e.getValue();
			String tmp = Integer.toString(num);
			String hl, tmp2 = "";
			if(tmp.length() == row)
			{
				hl = "0";
			}
			else if(tmp.length() < row) 
			{
				hl = "0";
				int l = tmp.length();
				for(int i = 0; i < row - tmp.length() - 1; i++)
				{
					tmp += "0";
				}
			}
			else 
			{
				hl = Character.toString(tmp.charAt(tmp.length() - row - 1));
				if(row != tmp.length() - 1)
				{
					tmp2 = tmp.substring(0, tmp.length() - row - 1);
				}
				
				if(row == 0)
				{
					tmp = "";
				}
				else 
				{
					tmp = tmp.substring(tmp.length() - row);
				}
			}
			
			e.setLabelText("<html>" + tmp2 + "<font color='red'>" + hl + "</font>" + tmp + "</html>");
		}
	}
	
	void ResetValue()
	{
		for(Element e: elements)
		{
			e.setValue(e.getValue());
		}
	}
	
	void MoveToArray(Element e, int indexArray)
	{
		Coloring(e, Color.yellow);
		try 
		{
			while(!e.getPosition().equals(arrayPos[indexArray]))
			{
				
				e.setPosition(MoveTowards(e.getPosition(), arrayPos[indexArray], 10));
				
				Thread.sleep(10);
			}
		}
		catch(Exception ex)
		{
			
		}
		Coloring(e, Color.blue);
	}
	
	@Override
	public void RemoveAllElements()
	{
		super.RemoveAllElements();
		for(int i = 0; i < 10; i++)
		{
			this.container.remove(labelBox[i]);
		}
	}
}
