package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Handles mouse and mouse motion events.
 * 
 * The last event is used to determine the mouse's location.
 *
 * @author Eric
 */
public class Mouse extends EventQueue<MouseEvent>
        implements MouseListener, MouseMotionListener
{
    private int x;
    private int y;
    private boolean leftButton;
    private boolean middleButton;
    private boolean rightButton;
 
    /**
     * Get the x position of the mouse.
     * @return the x position of the mouse.
     */
    public int getX()
    {
        return x;
    }
 
    /**
     * Get the y position of the mouse.
     * @return the y position of the mouse.
     */
    public int getY()
    {
        return y;
    }
 
    /**
     * Returns true if the left mouse button is currently pressed.
     * @return left mouse butting state.
     */
    public boolean isLeftButtonPressed()
    {
        return leftButton;
    }
 
    /**
     * Returns true if the middle mouse button is currently pressed.
     * @return middle mouse butting state.
     */
    public boolean isMiddleButtonPressed()
    {
        return middleButton;
    }
 
    /**
     * Returns true if the right mouse button is currently pressed.
     * @return right mouse butting state.
     */
    public boolean isRightButtonPressed()
    {
        return rightButton;
    }
 
    @Override
    protected void processEvent(MouseEvent event)
    {
        x = event.getX();
        y = event.getY();
 
        // if the mouse was pressed, update the correct button
        if(event.getID() == MouseEvent.MOUSE_PRESSED)
        {
            if(event.getButton() == MouseEvent.BUTTON1) leftButton = true;
            if(event.getButton() == MouseEvent.BUTTON2) middleButton = true;
            if(event.getButton() == MouseEvent.BUTTON3) rightButton = true;
             
        }
 
        // if the mouse was released, update the correct button
        if(event.getID() == MouseEvent.MOUSE_RELEASED)
        {
            if(event.getButton() == MouseEvent.BUTTON1) leftButton = false;
            if(event.getButton() == MouseEvent.BUTTON2) middleButton = false;
            if(event.getButton() == MouseEvent.BUTTON3) rightButton = false;
        }
    }
    
    /**
     * função verifica se X e Y do mouse estão nos valores minimos e máximos
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @return
     */
    public boolean mouseOver(int x, int y, int minX, int maxX, int minY, int maxY){    	
    	if( (minX <= x && x <= maxX ) && (minY <= y && y <= maxY)){
    		return true;
    	}else{
    		return false;
    	}    	
    }
    
    public void mouseClicked(MouseEvent event)
    {
        addEvent(event);
    }
 
    public void mousePressed(MouseEvent event)
    {
        addEvent(event);
    }
 
    public void mouseReleased(MouseEvent event)
    {
        addEvent(event);
    }
     
    public void mouseEntered(MouseEvent event)
    {
        addEvent(event);
    }
 
    public void mouseExited(MouseEvent event)
    {
        addEvent(event);
    }
 
    public void mouseDragged(MouseEvent event)
    {
        addEvent(event);
    }
 
    public void mouseMoved(MouseEvent event)
    {
        addEvent(event);
    }
}