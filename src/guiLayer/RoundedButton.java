package guiLayer;

import java.awt.AWTEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionListener;


public class RoundedButton extends Component {

    private ActionListener actionListener;
    private String label;
    private Color bg;
    private Color borderColor;
    protected boolean pressed = false;

    /**
     * Constructs a RoundedButton with the specified label.
     *
     * @param label the label of the button
     */
    public RoundedButton(String label, Color color) {
        this.label = label;
        this.bg = color;
        this.borderColor = Color.BLACK;
    }
    
    public RoundedButton(String label, Color bg, Color borderColor) {
        this.label = label;
        this.bg = bg;
        this.borderColor = borderColor;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(bg);
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // draw the parameter of the button
        //setBorderColor(getBackground().darker().darker().darker());
        g.setColor(borderColor);
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // draw the label centered in the button
        Font f = getFont();
        if (f != null) {
            FontMetrics fm = getFontMetrics(getFont());
            g.setColor(getForeground());
            g.drawString(label, getWidth() / 2 - fm.stringWidth(label) / 2, getHeight() / 2 + fm.getMaxDescent());
        }
    }

    public void addActionListener(ActionListener actionListener) {
    	this.actionListener = AWTEventMulticaster.add(actionListener, actionListener);
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
    }
    
    public void setBorderColor(Color c) {
    	borderColor = c;
    	repaint();
    }
    
    public void setBackgroundColor(Color c) {
    	bg = c;
    	repaint();
    }
    
    public void doClick() {
    	setBorderColor(Color.RED);
    	repaint();
    }
}