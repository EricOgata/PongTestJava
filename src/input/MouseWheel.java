package input;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 * Handles wheel rotation events.
 *
 * @author Eric
 */
public class MouseWheel extends EventQueue<MouseWheelEvent>
        implements MouseWheelListener
{
    private int wheelRotation = 0;
 
    /**
     * Returns the total wheel rotation between the last two updates.
     *
     * Negative rotation is away from the user, positive is towards
     * @return
     */
    public int getWheelRotation()
    {
        return wheelRotation;
    }
 
    @Override
    public void update()
    {
        // reset the wheel rotation
        wheelRotation = 0;
        super.update();
    }
 
    @Override
    public void processEvent(MouseWheelEvent event)
    {
        // increment the wheel rotation
        wheelRotation += event.getWheelRotation();
    }
 
    public void mouseWheelMoved(MouseWheelEvent event)
    {
        addEvent(event);
    }
}