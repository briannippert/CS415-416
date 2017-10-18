//++++++++++++++++++++++++++++ DisplayPanel ++++++++++++++++++++++++++++
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
/** 
 * DisplayPanel.java: This class supports the display of DNA alignment data.
 *     The top line of the display shows a "reference" DNA string defined by
 *     a single instance of DNASequence, typically quite long.
 * 
 *     Below the reference (on 1 or more lines) are multiple DNASequence
 *     objects whose horizontal positions place them at appropriate locations
 *     compared to the reference. These could be sequence reads that "align"
 *     at that position, or introns that are located at the start position
 *     in the chromosome, but are spliced out when dna is converted to
 *     rna in the process of being translated to proteins.
 * 
 *     There are scrollbars in horizontal and vertical directions.
 * 
 * @author rdb
 * CS416 Spring 2009
 * Edited 4/14/15 made checkstyle compatible
 */

public class DisplayPanel extends JPanel 
{  
    //------------------- class variables --------------------------
    static private  DisplayPanel theDisplay;
    
    //------------------- instance variables ------------------------
    private DNASequence    _reference;
    private DNASequenceSet _alignList;
    
    // variables for organizing reads
    private int       _minTrack = 0;   // min track allowed to get reads
    private int       _hiwaterTrack = 0; // highest # track with reads in it
    private int       _firstTrackY  = 60;
    private int       _hiTrackY = 60; // highest Y position of tracks so far
    
    // _tracks represent a horizontal strip of display area at a fixed y
    //         with a fixed height
    private Vector<DNASequenceSet> _tracks; 
    private Vector<Integer>        _trackH;   // the heights (not Y) of tracks
    private Vector<Integer>        _trackY;   // the y value of tracks
    
    //------------- magic constants
    private int       _refX = 10; // start of consensus display
    private int       _refY = 20;
    private int       _nucHeight  = 20;
    //     
    private int       _nucW      = 10;  // space for the nucleotide
    private int       _fontSize      = 16;  // font depends on _nucW
    private int[]     _fontSizeArray = { 8, 10, 12, 14, 15, 16, 18 };
    // By experimentation:
    //    (11,18) (10,16) (9,15) (8,14) (7,12) (6,10) (5,8)
    private int       _maxWidth = 11;
    private int       _minTextWidth = 5;
    private int       _minTextHeight = 10;
    private int       _minBorderWidth = 3;
    private int       _trackHeight = 20;
    private int       _trackVOffset = 6;
    private int       _trackHSpace = 10;  // # nucleotide space between seq in
    // same track
    private int       _separatorHeight = 3;
    
    private int       _defaultW   = 20000;
    private int       _defaultH   = 1000; 
    
    
    // variables for handling mouse position mapping
    private GUI       _gui;
    
    //--------------------- constructor ----------------------------
    /**
     * Constructor.
     * @param gui GUI
     */
    public DisplayPanel ( GUI gui ) 
    {
        super();
        theDisplay = this;
        _gui = gui;
        _alignList = new DNASequenceSet();
        if ( GUI.batch )
            return;
        _tracks   = new Vector<DNASequenceSet>();
        _trackH   = new Vector<Integer>();
        _trackY   = new Vector<Integer>();
        setLayout( null );
        setPreferredSize( new Dimension( _defaultW, _defaultH ) );
        setupMouseListeners();
        
        update();
    }
    //----------------------- setupMouseListeners() -----------------------
    /**
     * Build the mouse handlers.
     */
    private void setupMouseListeners()
    {
        this.addMouseListener(
            new MouseAdapter() 
            { 
                /** Get mouse event. @param me MouseEvent */
                public void mouseClicked( MouseEvent me )
                {
                    Point mouseAt = me.getPoint();
                    int   pickY = me.getY();
                    String msg = "Seq not found for pick at " + mouseAt;
                    int nucPosition = ( me.getX() - _refX ) / _nucW;
                    int track = getTrackFromY( pickY );
                    //int track = ( me.getY() - _readStartY ) / _trackVOffset;
                    if ( track >= 0 )
                    {
                        String header = findReadPicked( track, nucPosition );
                        if ( header != null )
                            msg = header;
                    }
                    else if ( pickY >= _refY && pickY <= _refY + _nucHeight )
                        msg = _reference.toString();
                       //msg = _reference.longString();
                    _gui.setSequencePicked( msg );
                }
            } );
        this.addMouseMotionListener(
            new MouseMotionAdapter() 
            { 
                public void mouseMoved( MouseEvent me )
                {
                    Point mouseAt = me.getPoint();
                    int nucPosition = ( me.getX() - _refX ) / _nucW;
                    _gui.setReferencePosition( nucPosition );
                }
            } 
        );
    } 
    //----------------------- getTrackFromY( int y ) -----------------------
    /**
     * Find free track space.
     * @param y int
     * @return int
     */
    private int getTrackFromY( int y )
    {
        int theTrack = -1; 
        for ( int t = 0; t < _trackY.size() && theTrack == -1 ; t++ ) 
        {
            Integer yTrack = _trackY.get( t );
            int yt = yTrack.intValue();
            if ( y >= yt  && y < yt + _trackHeight ) 
                theTrack = t;
        }
        return theTrack;
    }
    //--------------------- setReference( DNASequence ) --------------------
    /**
     * Define a reference sequence.
     * @param dna DNASequence
     */
    public void setReference( DNASequence dna )
    {
        _reference = dna;
        update();
    }
    //----------------------- getNucHeight() ---------------------
    /**
     * Get the nucleotide display height for read sequences.
     * @return int
     */
    public int getNucHeight()
    {
        return _trackHeight;
    }
    //----------------------- setNucHeight( int ) ---------------------
    /**
     * Set the nucleotide display height for read sequences.
     * @param h int
     */
    public void setNucHeight( int h )
    {
        _trackHeight = h;
    }
    //-------------------- secondary( DNASequenceSet ) ------------------
    /**
     * Specify the secondary sequences to be displayed below the reference
     * sequence; these could be aligned reads, introns, aligned orthologs, etc.
     * 
     * These can be specified as a group with this call, or individually with
     * the addSeq method.
     * @param seqSet DNASequenceSet
     */
    public void secondary( DNASequenceSet seqSet )
    {
        clearSecondary();
        //newRegion( 50 );
        Iterator<DNASequence> iter = seqSet.iterator();
        while ( iter.hasNext() )
        {
            DNASequence seq = iter.next();
            addSeq( seq );
        }
        Log.trace( "test", "Display: " + _alignList.size() );
    }
    //----------------------- clearSecondary() -----------------------------
    /**
     * Clear the secondary sequence display area.
     */
    public void clearSecondary()
    {
        if ( _tracks != null )
        {
            _tracks.clear();
            _trackH.clear();
            _trackY.clear();
        }
        if ( _alignList != null )
            _alignList.clear();
        _minTrack = 0;
        _hiwaterTrack = 0;
        _hiTrackY = _firstTrackY;
        updateTracks();
        update();
    }
    //----------------------- addSeq( DNASequence ) -----------------------
    /**
     * Add a read to the list to be displayed, put in order of length.
     * @param seq DNASequence
     */
    public void addSeq( DNASequence seq )
    {
        if ( seq == null )
        {
            System.err.println( "DisplayPanel.addSeq got null parameter " );
            return;
        }
        //addSeqP( seq ); // invoke private version of read
        _alignList.add( seq );

        assignReadToTrack( seq );
        update();
    }
    //----------------------- addSeqP( DNASequence ) ----------------
    /**
     * Add a read to the list to be displayed.
     * @param seq DNASequence
     */
    private void addSeqP( DNASequence seq )
    {
        _alignList.add( seq );
    }
    
    //----------------------  setNucleotideWidth( int ) -------------------
    /**
     * Set the width of nucleotide display.
     * @param newW int
     */
    public void setNucleotideWidth( int newW )
    {
        if ( newW > 0 && newW < _maxWidth )
        {
            _nucW = newW;
            if ( _nucW >= _minTextWidth )
                _fontSize = _fontSizeArray[ _nucW - _minTextWidth ];
            _minTextHeight = _nucW * 2;
        }   
        update();
    }
    //--------------------- updateSize( Dimension ) ------------------
    /**
     * Update the size of the display panel.
     * @param dim Dimension
     */
    public void updateSize( Dimension dim )
    {
        this.setPreferredSize( dim );
        this.setSize( dim );
        Dimension mDim = new Dimension( dim.width, dim.height - _refY );
        update();
    }
    //------------------------ update() -----------------------------
    /**
     * Update the graphical layout.
     */
    public void update()
    {
        if ( GUI.batch )
            return;
        this.revalidate();
        this.repaint();
    }
    //------------------------ updateTracks() -----------------------------
    /**
     * Update the graphical representation of the list being shown.
     */
    public void updateTracks()
    {
        if ( GUI.batch )
            return;
        _tracks.clear();
        _trackH.clear();
        Iterator<DNASequence> iter = _alignList.iterator();
        while ( iter.hasNext() )
        {
            assignReadToTrack( iter.next() );
        }
        /***************************/
    }
    //------------------------- newRegion( int ) ------------------------
    /**
     * Start a new vertical region, parameter is number of tracks of space 
     * between regions.
     * @param space int
     */
    public void newRegion( int space )
    {
        // fill in phony tracks
        for ( int i = 0; i < space; i++ )
        {
            _tracks.add( null );
            _trackH.add( new Integer( -1 ) );
            _trackY.add( new Integer( -1 ) );
        }
        _hiwaterTrack += space;
        _minTrack = _hiwaterTrack + 1;
        _hiTrackY += space * _separatorHeight;
        /*******************************************/
    }
    //--------------- assignReadToTrack( DNASequence ) --------------------
    /**
     * Check each of the tracks from top to bottom.
     *    if read fits in track without overlap with existing reads
     *       add it to the track
     *    if track is empty
     *       create new list for track and add this read to it.
     *       track lists are ordered by starting position  
     * @param seq DNASequence
     */
    private void assignReadToTrack( DNASequence seq )
    {
        if ( GUI.batch || seq == null ) 
            return;
        
        int t = _minTrack;   // start at minTrack
        boolean assigned = false;
        while ( t < _tracks.size() && !assigned )
            assigned = fitsInTrack( t++, seq );
        if ( !assigned )
        {
            DNASequenceSet track = new DNASequenceSet();
            track.add( seq );
            _tracks.add( track );
            _trackH.add( new Integer( _trackHeight ) );
            _trackY.add( new Integer( _hiTrackY ) );
            _hiTrackY += _trackHeight + _trackVOffset;
        }
        if ( t > _hiwaterTrack )
            _hiwaterTrack = t;
        /********************************************/
    }
    //-------------------- fitsInTrack( List, DNASequence ) ---------------
    /**
     * Check if the sequence can be displayed in this track, if so, do it
     * return true if it fit, false otherwise.
     * @param t int
     * @param seq DNASequence
     * @return boolean
     */
    private boolean fitsInTrack( int t, DNASequence seq )
    {
        if ( _trackH.get( t ) == -1 )
            return false;
        DNASequenceSet track = _tracks.get( t );
        
        Iterator<DNASequence> iter = track.iterator();
        boolean fits = true;
        DNASequence s = null;
        while ( iter.hasNext() && fits )
        {
            s = iter.next();
            fits = !overlap( s, seq );
        }
        
        if ( fits )
            track.add( seq );
        return fits;
    }
    //------------------- overlap( DNASequence, DNASequence )--------------
    /**
     * Returns true if the two sequences overlap, false otherwise.
     * @param s1 DNASequence
     * @param s2 DNASequence
     * @return boolean
     */
    private boolean overlap( DNASequence s1, DNASequence s2 )
    {
        int start1 = s1.getReferencePosition() - _trackHSpace * _nucW;
        int end1 = start1 + s1.length() - 1 + 2 * _trackHSpace * _nucW;
        int start2 = s2.getReferencePosition();
        int end2 = start2 + s2.length() - 1;
        
        return !( start2 > end1 || start1 > end2 );
    }
    //------------------- overlap( DNASequence, int )--------------
    /**
     * Returns true if the sequence includes the position.
     * @param s   DNASequence
     * @param pos int
     * @return boolean
     */
    private boolean overlap( DNASequence s, int pos )
    {
        int start = s.getReferencePosition();
        int end = start + s.length() - 1;
        
        return start <= pos && pos <= end;
    }
    //----------------------- findReadPicked( int, int ) ----------------
    /**
     * Find out which read was picked.
     * @param t int
     * @param pos int
     * @return String
     */
    private String findReadPicked( int t, int pos )
    {
        if ( t < 0 || t >= _tracks.size() )
            return null;
        DNASequenceSet track = _tracks.get( t );
        
        Iterator<DNASequence> iter = track.iterator();
        DNASequence s = null;
        boolean     found = false;
        while ( iter.hasNext() && !found )
        {
            s = iter.next();
            found = overlap( s, pos );
        }
        
        if ( s == null )
            return null;
        else 
            return s.toString();
    }
    //----------------------- paintComponent( Graphics ) ------------------
    /**
     * paintComponent - calls draw and fill awt methods.
     * @param brush java.awt.Graphics 
     */
    public void paintComponent( java.awt.Graphics brush )
    {
        super.paintComponent( brush );
        
        if ( _reference == null )
            return;
        Graphics2D brush2 = (Graphics2D) brush;
        Color saveColor = brush2.getColor();
        Stroke saveStroke = brush2.getStroke();
        drawDNA( brush2, _reference.getDNA(), _refX, _refY, _nucHeight );
        
        drawReads( brush2 );
        brush2.setColor( saveColor );
        brush2.setStroke( saveStroke );
    }
    //------------ drawReads( Graphics2D  ) ---------------------
    /**
     * Generate display for the reads.
     * @param brush Graphics2D
     */
    private void drawReads( Graphics2D brush )
    {
        if ( _alignList == null || _alignList.size() == 0 )
            return;
        //int trackY = _readStartY;
        int trackY = 0;
        
        for ( int t = 0; t < _tracks.size(); t++ )
        {
            DNASequenceSet track = _tracks.get( t );
            trackY = _trackY.get( t );
            int h = _trackH.get( t );
            if ( h == -1 )   // this is a separator track, draw a line
            {
                drawSeparator( brush, trackY );
                if ( track.size() != 0 )
                    System.err.println( "DisplayPanel: sep track not empty" );
            }
            else
            {
                Iterator<DNASequence> iter = track.iterator();
                while ( iter.hasNext() )
                {
                    DNASequence seq = iter.next();
                    int p = seq.getReferencePosition();
                    if ( p >= 0 )
                    {
                        int len = seq.getDNA().length();
                        drawDNA( brush, seq.getDNA(), 
                                _refX + p * _nucW, trackY, h );
                    }
                }
            }
        }
        /********************************************/
        
    }
    //------------ drawSeparator( Graphics2D, int ) ----------------------
    /**
     * Draw a separator line.
     * @param brush Graphics2D
     * @param y int
     */
    private void drawSeparator( Graphics2D brush, int y )
    {
        brush.setColor( Color.BLACK );
        Stroke saveStroke = brush.getStroke();
        brush.setStroke( new BasicStroke( _separatorHeight ) );
        brush.drawLine( 0, y, 10000, y );
        brush.setStroke( saveStroke );
    }
    //------------ drawDNA( Graphics2D, String, int, int, int  ) ----------
    /**
     * Generate display for a dna sequence.
     * @param brush Graphics2D
     * @param dna String
     * @param xStart int
     * @param yStart int
     * @param height int
     */
    private void drawDNA( Graphics2D brush, String dna, 
                         int xStart, int yStart, int height )
    {
        if ( dna == null )
            return;
        int dx = _nucW;
        int dy = height;
        int x = xStart;
        int y = yStart;
        
        for ( int n = 0; n < dna.length(); n++ )
        {
            switch ( dna.charAt( n ) )
            {
                case 'A': case 'a': 
                    brush.setColor( Color.GREEN ); break;
                case 'T': case 't': 
                    brush.setColor( Color.RED ); break;
                case 'G': case 'g': 
                    brush.setColor( Color.YELLOW ); break;
                case 'C': case 'c': 
                    brush.setColor( Color.BLUE ); break;
                case 'N': case 'n': 
                    brush.setColor( Color.MAGENTA ); break;
                case 'X': case 'x': 
                    brush.setColor( Color.GRAY ); break;
                case '-':           
                    brush.setColor( Color.BLACK ); break;
                case '*':           
                    brush.setColor( Color.WHITE ); break;
                default:            
                    brush.setColor( Color.GRAY ); break;
            }
            brush.fillRect( x, y, dx, dy );
            if ( _nucW >= _minBorderWidth )
            {
                brush.setColor( Color.BLACK );
                brush.drawRect( x, y, dx, dy );
            }
            x += dx;
        } 
        if ( _nucW >= _minTextWidth && height >= _minTextHeight )
        {
            brush.setFont( new Font( "Monospaced", Font.PLAIN, _fontSize ) );
            brush.setColor( Color.BLACK );
            int textBaseline = (int) ( dy * 0.75 );
            brush.drawString( dna, xStart, yStart + textBaseline );
        }
    }   
    
    //------------------ main ------------------------------------------
    /**
     * Convenience main for DrJava invocation of the app.
     * @param args String[]
     */
    public static void main( String [ ] args ) 
    {
        DNAapp.main( args );
    }
}
