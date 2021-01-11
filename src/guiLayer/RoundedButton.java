package guiLayer;

import java.awt.AWTEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;


public class RoundedButton extends Component {

    private ActionListener actionListener;
    private String label;
    private Color bg;
    private Color borderColor;
    protected boolean pressed = false;
    private int xAxis = 0;
    private int yAxis = 0;
    private Font font;

    /**
     * Constructs a RoundedButton with the specified label.
     *
     * @param label the label of the button
     */
    public RoundedButton(String label, Color color) {
        this.label = label;
        this.bg = color;
        this.borderColor = color;
        this.setName(label);
        this.font = new Font("Latto",Font.BOLD,14);
    }
    
    public RoundedButton(String label, Color bg, Color borderColor) {
        this.label = label;
        this.bg = bg;
        this.borderColor = borderColor;
        this.font = new Font("Latto",Font.BOLD,14);
    }
    
    public RoundedButton(String label, Color bg, Color borderColor, Font font) {
        this.label = label;
        this.bg = bg;
        this.borderColor = borderColor;
        this.font = font;
    }

    @Override
    public void paint(Graphics g) {

    	Graphics2D g2 = (Graphics2D) g;
    	
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
       
        g2.setRenderingHints(rh);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        
        // draw the label centered in the button
        Font f = getFont();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            
            g2.setRenderingHints(rh);
            g2.setColor(getForeground());
            g2.setFont(font);
            g2.drawString(label, getWidth() / 2 - fm.stringWidth(label) / 2  + xAxis, getHeight() / 2 + fm.getMaxDescent() + yAxis);
        }
    }
    
    
    
    public void addOffset(int xAxis, int yAxis)
    {
    	this.xAxis = xAxis;
    	this.yAxis = yAxis;
    	this.repaint();
    }

    
    public void setBorderColor(Color c) {
    	borderColor = c;
    	repaint();
    }
    
    public void setBackgroundColor(Color c) {
    	bg = c;
    	repaint();
    }
}