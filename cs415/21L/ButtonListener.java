/**
 * ButtonListener interface. - A ButtonListener object creates a 
 *     Button object and passes itself to the Button as a "listener".
 *     When a button event occurs, the Button invokes the 
 *     corresponding method of the ButtonListener, so it 
 *     knows what has happened.
 * 
 * @author rdb
 * 
 */
public interface ButtonListener
{
    /******************************************************************/
    /**
     * button. 
     * 
     * @param buttonLabel String
     * @param buttonId int
     */   
    public void buttonPressed( String buttonLabel, int buttonId );
    /******************************************************************/
    /**
     * button. 
     * 
     * @param buttonLabel String
     * @param buttonId int
     */   
    public void buttonReleased( String buttonLabel, int buttonId );
    /******************************************************************/
    /**
     * button. 
     * 
     * @param buttonLabel String
     * @param buttonId int
     */   
    public void buttonClicked( String buttonLabel, int buttonId );
}