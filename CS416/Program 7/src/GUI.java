//++++++++++++++++++++++++++++++++ GUI ++++++++++++++++++++++++++++++++++
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
/** 
 * GUI.java:
 * A JPanel to control the application's user interface and its 
 * connection to the main application code.
 * 
 * 
 * This GUI supports the DNAapp program
 *
 * @author rdb
 * Spring 2009
 * Spring 2010 - Modified to be used for gene displays with introns
 * Fall   2010 - Modified to use for gtf access
 * Spring 2015 - made checkstyle-compatible.
 * 
 */

public class GUI extends JPanel 
{
    //------------------ class variables ---------------------------
    static GUI     theGUI;   
    static boolean batch = false;
    
    //---------------- instance variables ---------------------------
    private DisplayPanel  _display;
    private GTFData        _gtf;        // the gtf data 
    private Gene           _refGene;   // gene currently displayed
    private DNASequenceSet _introns;   // introns to be displayed under gene
    
    private JLabel         _referencePosition; // loc of mouse in reference
    private int            _refPos;
    private JLabel         _sequencePicked; // loc of mouse in reference
    private String         _sequencePickedId = "";
    private JLabel         _geneInfo;
    private LabeledSpinner _geneSpin;
    
    //------------------- constructor -------------------------------
    /**
     * Container parent of the control panel is passed as an argument
     * along with the application parameters.
     * @param seqFileName String
     * @param gtfFileName String
     */
    public GUI( String gtfFileName, String seqFileName ) 
    {
        super ( new BorderLayout() );
        theGUI   = this;
        
        _display = new DisplayPanel( this );
        
        int displayWidth = _display.getPreferredSize().width; 
        int displayHeight = _display.getPreferredSize().height;
        
        final JScrollPane sPane = new JScrollPane( _display,
             ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
             ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
        );     
        this.add( sPane, BorderLayout.CENTER );
        
        JPanel northPanel = makeNorth();
        this.add( northPanel, BorderLayout.NORTH );
        
        JPanel southPanel = makeSouth();
        this.add( southPanel, BorderLayout.SOUTH );
        
        sPane.setPreferredSize( new Dimension( displayWidth, displayHeight ) );
        Log.trace( "test", gtfFileName + " " + seqFileName );
        if ( gtfFileName != null )
        {
            readGTF( gtfFileName );
            if ( seqFileName != null )
                readGTFSeq( seqFileName );
        }         
    }
    //------------------- makeNorth() -----------------------------------
    /**
     * Create buttons and orthlog spinner in North.
     * @return JPanel
     */
    private JPanel makeNorth()
    {
        JPanel northPanel = new JPanel( new FlowLayout() );
        
        //create Buttons in the North
        Component buttonMenu = makeButtonMenu();
        northPanel.add( buttonMenu );
        
        int max = 20;
        _geneSpin = new LabeledSpinner( "Gene: ", 0, max, 0 );
        _geneSpin.addChangeListener( new ChangeListener()
        {
            public void stateChanged( ChangeEvent ev )
            {
                JSpinner spinner = (JSpinner) ev.getSource();
                int spinValue = ( (Number) spinner.getValue() ).intValue();
                setReference( spinValue );
            }
        } );
        northPanel.add( _geneSpin );
        
        return northPanel;
    }
    //------------------- makeSouth() -----------------------------------
    /**
     * Create two sliders and 3 JLabels in the south region.
     * @return JPanel
     */
    private JPanel makeSouth()
    {
        //create display panel size scrollbar in the south
        JPanel southPanel = new JPanel( new GridLayout( 0, 1 ) );
        JPanel sliderPanel = new JPanel( new GridLayout( 1, 2 ) );
        LabeledSlider nucWidth = new LabeledSlider( "NucWidth", 1, 11, 10 );
        nucWidth.addChangeListener( new ChangeListener()
        { 
            public void stateChanged( ChangeEvent e )
            {
                int newWidth = ( (JSlider)e.getSource() ).getValue();
                _display.setNucleotideWidth( newWidth );
            }
        } );
        nucWidth.getJSlider().setMajorTickSpacing( 1 );
        sliderPanel.add( nucWidth );         
        
        int displayWidth = _display.getPreferredSize().width;
        LabeledSlider paneWidth = 
            new LabeledSlider( "PaneWidth", 0, 100000, displayWidth );
        paneWidth.addChangeListener( new ChangeListener()
        { 
            public void stateChanged( ChangeEvent e )
            {
                int h = _display.getPreferredSize().height;
                int newWidth = ( (JSlider)e.getSource() ).getValue();
                _display.updateSize( new Dimension( newWidth, h ) );
                theGUI.revalidate();
            }
        } );
        paneWidth.getJSlider().setMajorTickSpacing( 50000 );
        sliderPanel.add( paneWidth ); 
        
        JPanel labelPanel = new JPanel( new GridLayout( 1, 3 ) );
        createLabels( labelPanel );
        southPanel.add( sliderPanel );
        southPanel.add( labelPanel );
        return southPanel;
    }
    //--------------------- createLabels( Panel ) -----------------------
    /**
     * Create information labels and add to specified panel.
     * @param panel JPanel
     */
    private void createLabels( JPanel panel )
    {
        // create a label for reference  position of mouse
        _referencePosition = new JLabel( " Reference position: " + _refPos ); 
        _referencePosition.setBorder( new LineBorder( Color.BLACK ) );
        panel.add( _referencePosition );
        
        // create a label for last picked sequence
        _sequencePicked = new JLabel( " Seq: " + _sequencePickedId );
        _sequencePicked.setBorder( new LineBorder( Color.BLACK ) );
        panel.add( _sequencePicked );
        
        // create a label for gene information 
        _geneInfo = new JLabel( " Gene: " + _refGene );
        _geneInfo.setBorder( new LineBorder( Color.BLACK ) );
        panel.add( _geneInfo );
    }      
    
    //----------------- updateCurGene( String ) ------------------------
    /**
     * The gene has changed, show that in the label and the Spinner.
     * @param index int
     * @param label String
     */
    public void updateCurGene( int index, String label )
    {
        if ( _geneInfo != null )
            _geneInfo.setText( label );
        if ( _geneSpin != null )
            _geneSpin.getJSpinner().setValue( index );
    }
    //----------------- setReferencePosition --------------------------
    /**
     * Set the reference position label.
     * @param pos int
     */
    public void setReferencePosition( int pos )
    {
        _refPos = pos;
        _referencePosition.setText( " Reference position: " + _refPos + "  " );
    }
    //----------------- setSequencePicked( String ) ----------------------
    /**
     * Set the label for sequence picked.
     * @param id String
     */
    public void setSequencePicked( String id )
    {
        _sequencePickedId = id;
        _sequencePicked.setText( " Seq: " + id );
    }
    //------------------- makeButtonMenu --------------------------------
    /**
     * Create button menu.
     * @return Component
     */
    private Component makeButtonMenu()
    {
        String[] labels = { "Open GTF/Seq", "ShowThisGene", "Show Any Gene" };
        
        JPanel bMenu = new JPanel( new GridLayout( 1, 0 ) ); 
        JButton button;
        for ( int i = 0; i < labels.length; i++ )
        {
            button = new JButton( labels[ i ] );
            //button.setFont( getFont().deriveFont( 11.0f ) );
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
    private class ButtonListener implements ActionListener
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
                case 0:   // Open new GTF (and maybe seq) file
                    readFiles();
                    break;
                case 1:   // show current Gene
                    showGene();
                    break;
                case 2:   // Dialog box show gene
                    showGeneDialog();
                    break;
            }
        }
    } 
    //---------------------- readGTF( String ) ----------------------------
    /**
     * Read the GTF file.
     * @param gtfFileName String
     */
    private void readGTF( String gtfFileName )
    {
        File gtfFile = new File( gtfFileName );
        if ( gtfFile.exists() )
            DataManager.readGTFData( gtfFileName );
        else
            Log.error( "Cannot open file: " + gtfFileName );
    }
    //---------------------- readGTFSeq( String ) ----------------------------
    /**
     * Read the Seq file. 
     * @param seqFileName String
     */
    private void readGTFSeq( String seqFileName )
    {
        Log.trace( "test", "readGTFSeq called" );
        File seqFile = new File( seqFileName );
        if ( seqFile.exists() )
        {
            DataManager.readGTFSeqData( seqFileName );
            int spinMax = DataManager.getNumGenes() - 1;
            SpinnerModel model = new SpinnerNumberModel( 0, 0, spinMax, 1 );
            _geneSpin.getJSpinner().setModel( model );
            setReference( 0 );
        }
        else
            Log.error( "Cannot open file: " + seqFileName );
    }
    //---------------------- readFiles() -------------------------------
    /**
     * Read the GTF file and its associated sequence file. 
     */
    private void readFiles( )
    {
        String fileName = Utilities.getFileName( "Choose GTF file" );
        if ( fileName == null || fileName.length() == 0 )
            return;
        DataManager.readGTFData( fileName );
        
        String seqFileName = null;
        
        int dotLoc = fileName.lastIndexOf( "." );
        if ( dotLoc >= 0 )
        {
            seqFileName = fileName.substring( 0, dotLoc ) + ".seq";
            File seqFile = new File( seqFileName );
            if ( !seqFile.exists() )
                seqFileName = null;
        }
        if ( seqFileName == null )
            seqFileName = Utilities.getFileName( "Choose sequence data file" );
        readGTFSeq( seqFileName );
    }
    //---------------------- readGTFSeq() -------------------------------
    /**
     * Read the GTF sequence file.
     */
    private void readGTFSeq( )
    {
        String seqFileName = Utilities.getFileName( "Choose sequence file" );
        readGTFSeq( seqFileName );
    }
    //----------------- showGene() ------------------------------------
    /**
     * Show introns/exons in text form for current gene. 
     */
    private void showGene()
    {
        if ( _refGene == null )
            showGeneDialog();
        else
        {
            String geneRep = _refGene.longString( 4 );
            JOptionPane.showMessageDialog( null, geneRep );
        }
    }
    //----------------- showGeneDialog() ------------------------------------
    /**
     * Show the introns in their correct positions. 
     */
    private void showGeneDialog()
    {
        String prompt = "Enter gene id + optional int for max # nucleotides";
        String reply  = null;
        String args = JOptionPane.showInputDialog( null, prompt );
        if ( args != null && args .length() > 0 )
        {
            String[] parms = args.split( " " );
            if ( parms.length > 0 )
            {
                Gene gene = DataManager.getGeneId( parms[ 0 ] );
                if ( gene == null )
                    reply = parms[ 0 ] + " not found!";
                else if ( parms.length > 1 )
                    reply = gene.longString( toInt( parms[ 1 ] ) );
                else 
                    reply = gene.longString( 0 );
            }
            JOptionPane.showMessageDialog( null, reply );
        }
    }
    //----------------- showIntrons() ------------------------------------
    /**
     * Show the introns in their correct positions. 
     */
    private void showIntrons()
    {
        DNASequenceSet introns = _refGene.getIntrons();
        Log.trace( "test", "Introns: " + introns.size() );
        _display.secondary( introns );
    }
    //--------------- setReference( int ) --------------------------------
    /**
     * Set the reference display to be the DNASequence.
     * @param refIndex int
     */
    private void setReference( int refIndex )
    {
        setReference( DataManager.getGeneId( refIndex ) );      
    }
    //--------------- setReference( DNASequence ) -------------------------
    /**
     * Set the reference display to be the DNASequence.
     * @param ref Gene
     */
    private void setReference( Gene ref )
    {
        _refGene = ref;
        if ( ref != null )
        {
            _display.setReference( _refGene.getCDS() ); 
            _geneInfo.setText( _refGene.toString() );
            showIntrons();
        }
        else
        {
            _display.setReference( null );
            _display.clearSecondary();
        }
    }
    //---------------------- toInt( String ) ----------------------------
    /**
     * Utility method to convert a String to an int; bad format just
     * prints an error and returns 0.
     * @param s String
     * @return int
     */
    public static int toInt( String s )
    {
        try
        {
            int number = new Integer( s );
            return number;
        }
        catch ( NumberFormatException nfe )
        {
            Log.error( "GTFData.toInt: Cannot convert to int: " + s );
            return 0;
        }
    }
    //------------------ main ------------------------------------------
    /**
     * Convenience function for DrJava to open main app.
     * @param args String[]
     */
    public static void main( String [ ] args ) 
    {
        DNAapp.main( args );
    }
}
