package base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Element {
	
	Color color;
	int value;
	Point position;
	JLabel label;
	
	public Element(int value)
	{
		initLabel();
		this.setValue(value);
		this.setColor(Color.blue);
	}
	
	public Element(int value, Point position)
	{
		initLabel();
		this.setValue(value);
		this.setColor(Color.blue);
		this.setPosition(position);
	}
	
	public Element(int value, Point position, Color color)
	{
		initLabel();
		this.setValue(value);
		this.setColor(color);
		this.setPosition(position);
	}
	
	private void initLabel()
	{
		label = new JLabel();
		label.setSize(50, 50);
		label.setOpaque(true);
		label.setFont(new Font("Helvetica", Font.BOLD, 17));
		label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
		this.label.setBackground(color);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		this.label.setText(Integer.toString(value));
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
		this.label.setLocation(position);
	}
	
	public void setPosition(int x, int y) {
		this.position = new Point(x, y);
		this.label.setLocation(position);
	}
	
	public JLabel getLabel()
	{
		return this.label;
	}
	
	public Element clone()
	{
		return new Element(this.value, this.position, this.color);
	}
	
	public int compare(Element e)
	{
		if(this.getValue() == e.getValue())
			return 0;
		if(this.getValue() > e.getValue())
			return 1;
		return -1;
	}
}
