package guiLayer;

import java.awt.*;

/**
 * This class is a part of the Warehouse system
 * for Vestbjerg Byggecenter. It contains the
 * definition used to make rounded buttons, instead of
 * using the conventional Jbuttons. This has
 * been implemented to support a more flat design
 * for the system.
 */

public class RoundedButton extends Component{

    private String label;
    private Color backgroundColor;
    private Color textColor;
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
    public RoundedButton(String label, Color backgroundColor) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.textColor = Color.BLACK;
        this.borderColor = backgroundColor;
        this.setName(label);
    }
    
    /**
     * Constructs a RoundedButton with the specified parameters.
     * 
     * @param label
     * @param backgroundColor = background color
     * @param borderColor
     * @param font
     */
    public RoundedButton(String label, Color backgroundColor, Color borderColor, Font font) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.textColor = Color.BLACK;
        this.borderColor = borderColor;
        this.setName(label);
        this.font = font;
    }
    
    /**
     * Constructs a RoundedButton with the specified parameters.
     * 
     * @param label
     * @param backgroundColor
     * @param foregroundColor
     * @param borderColor
     * @param font
     */
    public RoundedButton(String label, Color backgroundColor, Color foregroundColor, Color borderColor, Font font) {
        this.label = label;
        this.backgroundColor = backgroundColor;
        this.textColor = foregroundColor;
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
        
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.setColor(borderColor);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        
        // draw the label centered in the button
        Font font = getFont();
        if (font != null) {
            FontMetrics fm = getFontMetrics(getFont());

            g2.setRenderingHints(rh);
            g2.setColor(textColor);
            g2.setFont(this.font);
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
    	backgroundColor = c;
    	repaint();
    }
    
    @Override
    public void setForeground(Color c)
    {
    	textColor = c;
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