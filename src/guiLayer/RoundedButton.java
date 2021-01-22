package guiLayer;

/**
 * This class is a part of the Warehouse system
 * for Vestbjerg Byggecenter. It contains the
 * definition used to make rounded buttons, instead of
 * using the conventional Jbuttons. This has
 * been implemented to support a more flat design
 * for the system.
 */

import java.awt.Color;
import java.awt.*;


public class RoundedButton extends Component {

    private String label;
    private Color bg;
    private Color fg;
    private Color borderColor;
    protected boolean pressed = false;
    private int xAxis = 0;
    private int yAxis = 0;
    private Font font;

    /**
     * Constructs a RoundedButton with the specified parameters.
     *
     * @param label the label of the button
     */
    public RoundedButton(String label, Color color) {
        this.label = label;
        this.bg = color;
        this.fg = Color.BLACK;
        this.borderColor = color;
        this.setName(label);
    }
    
    /**
     * Constructs a RoundedButton with the specified parameters.
     * 
     * @param label
     * @param bg = background color
     * @param borderColor
     * @param font
     */
    public RoundedButton(String label, Color bg, Color borderColor, Font font) {
        this.label = label;
        this.bg = bg;
        this.fg = Color.BLACK;
        this.borderColor = borderColor;
        this.setName(label);
        this.font = font;
    }
    
/**
 * Constructs a RoundedButton with the specified parameters.
 * 
 * @param label
 * @param bgColor
 * @param fgColor
 * @param borderColor
 * @param font
 */
    public RoundedButton(String label, Color bgColor, Color fgColor, Color borderColor, Font font) {
        this.label = label;
        this.bg = bgColor;
        this.fg = fgColor;
        this.borderColor = borderColor;
        this.setName(label);
        this.font = font;
    }

    
    @Override
    public void paint(Graphics g) {

    	Graphics2D g2 = (Graphics2D) g;
    	//2D graphics is used to avoid pixelation on the button
    	
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        g2.setColor(bg);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
       
       
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        
        // draw the label centered in the button
        Font f = getFont();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            
            g2.setRenderingHints(rh);
            g2.setColor(fg);
            g2.setFont(font);
            g2.drawString(label, getWidth() / 2 - fm.stringWidth(label) / 2  + xAxis, getHeight() / 2 + fm.getMaxDescent() + yAxis);
        }
    }
    
    
    /**
     * Moved the label inside the button, since the labels
     * are not always centered. This allows
     * manual adjustment of the labels position.
     * 
     * @param xAxis
     * @param yAxis
     */
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
    
    @Override
    public void setBackground(Color c) {
    	bg = c;
    	repaint();
    }
    
    @Override
    public void setForeground(Color c)
    {
    	fg = c;
    	repaint();
    }
    
    public void setText(String label) {
    	this.label = label;
    	this.setName(label);
    	repaint();
    }
    
    public void setFont(Font font) {
    	this.font = font;
    }
}