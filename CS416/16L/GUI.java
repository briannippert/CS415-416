//++++++++++++++++++++++ GUI.java +++++++++++++++++++++++++++
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/** 
 * GUI.java:
 * A JPanel to control the application's interface to the application.
 *
 * @author rdb
 * CS416 Spring 2008
 */

public class GUI extends JPanel 
{
    //---------------- instance variables ---------------------------
    private Container    _parent;
    private DrawPanel _display;
    private TreeApp      _app;
    private ControlPanel _controls;
    
    //------------------- constructor -------------------------------
    /**
     * Container parent of the control panel is passed as an argument
     * along with the application object.
     * @param parent Container
     */
    public GUI( Container parent ) 
    {
        super ( new BorderLayout() );
        
        _parent  = parent;  
        
        _display = new DrawPanel();
        _app     = new TreeApp( _display );
        
        JScrollPane sPane = new JScrollPane( _display,
                        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
        //sPane.getVerticalScrollBar().setUnitIncrement(10);
        //sPane.getHorizontalScrollBar().setUnitIncrement(10);
        
        sPane.setPreferredSize( new Dimension( 500, 400 ) );     
        
        this.add( sPane, BorderLayout.CENTER );
        
        //create ControlPanel in the South
        _controls = new ControlPanel( this, _app );
        this.add( _controls, BorderLayout.SOUTH );
        
        //create Buttons in the North
        Component buttonMenu = makeButtonMenu();
        this.add( buttonMenu, BorderLayout.NORTH );
    }
    //------------------- makeButtonMenu --------------------------------
    /**
     * Create the button menu.
     * @return Component
     */
    private Component makeButtonMenu()
    {
        // JPanel defaults to FlowLayout
        String[] labels = { "Data", "Print", "Find", "Delete" };
        
        JPanel bMenu = new JPanel( new GridLayout( 1, 0 ) ); 
        JButton button;
        for ( int i = 0; i < labels.length; i++ )
        {
            button = new JButton( labels[ i ] );
            //button.setFont( getFont().deriveFont( 11.0f ));
            bMenu.add( button );
            button.addActionListener( new ButtonListener( i ) );
        }      
        return bMenu;
    }
    //+++++++++++++++++ ButtonListener inner class ++++++++++++++++++++++++
    /**
     * ButtonListener handles all button events and passes them along
     * to methods of the ListPanel class.
     */
    public class ButtonListener implements ActionListener
    {
        int _buttonId;
        /** Constructor. @param buttonId int */
        public ButtonListener( int buttonId )
        {
            _buttonId = buttonId;
        }
        /** Event handler. @param ev ActionEvent */
        public void actionPerformed( ActionEvent ev )
        {
            switch ( _buttonId )
            {
                case 0:
                    _app.makeData();
                    break;
                case 1:
                    _app.print();
                    break;
                case 2:
                    _app.find();
                    break;
                case 3:
                    _app.delete();
                    break;            
            }               
        }
    }         
}
