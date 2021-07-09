import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;


public class PotLuck extends JFrame implements ActionListener {

    private JPanel contents;
    private JPanel panel;

    final int ROWS = 4;
    final int COLUMNS = 4;
    final int TOTAL_BUTTON_NO = ROWS * COLUMNS;

    JLabel label;
    JButton prizeButton,
            bombButton1,
            bombButton2;
    int prizeNo = 0;
    int bomb1No = 0;
    int bomb2No = 0;
    int counter = 0;

    public PotLuck() {

        setTitle( "Find the prize! Don't forget to be careful about two hidden bombs!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 600, 600);
        contents = new JPanel();
        contents.setBorder( new EmptyBorder( ROWS, ROWS, COLUMNS, COLUMNS ) );
        contents.setLayout( new BorderLayout() );
        setContentPane( contents );
        panel = new JPanel();
        contents.add( panel, BorderLayout.CENTER );
        panel.setLayout( new GridLayout( ROWS, COLUMNS, 10, 10 ) );

        while ( bomb1No == bomb2No || prizeNo == bomb1No ||  prizeNo == bomb2No ){
            bomb1No = (int)( Math.random() * ( TOTAL_BUTTON_NO ) );
            bomb2No = (int)( Math.random() * ( TOTAL_BUTTON_NO ) );
            prizeNo = (int)( Math.random() * ( TOTAL_BUTTON_NO ) );
        }

        for( int i = 0; i < TOTAL_BUTTON_NO; i++ ){
            if( i == prizeNo ){
                prizeButton = new JButton();
                prizeButton.addActionListener( this );
                panel.add( prizeButton );
            }
            else if( i == bomb1No ){
                bombButton1 = new JButton();
                bombButton1.addActionListener( this );
                panel.add( bombButton1 );
            }
            else if( i == bomb2No ){
                bombButton2 = new JButton();
                bombButton2.addActionListener( this );
                panel.add( bombButton2 );
            }
            else{
                JButton normalButton = new JButton();
                normalButton.addActionListener( this );
                panel.add( normalButton );
            }
        }

        label = new JLabel("Clicks so far: " + counter);
        label.setHorizontalAlignment( SwingConstants.CENTER );
        contents.add( label, BorderLayout.NORTH );
    }

    public static void main( String[] args){
        PotLuck frame = new PotLuck();
        frame.setVisible(true);
    }

    public void actionPerformed( ActionEvent event ){

        if ( event.getSource() != prizeButton && event.getSource() != bombButton1 && event.getSource() != bombButton2){
            counter ++;
            ( (JButton) event.getSource() ).setEnabled(false);
            label.setText("Clicks so far: " + counter );
        }

        else if ( event.getSource() == prizeButton){
            label.setText("You got it in " + (counter ++) + " attempts!" );
            for( int i = 0; i < ( TOTAL_BUTTON_NO ); i++ ){
                if(panel.getComponent(i) != prizeButton ){
                    ( (JButton) panel.getComponent( i ) ).setEnabled(false);

                }
            }
        }
        else {
            label.setText("Sorry! You are blown up at attempt " + (counter++));
            for(int i = 0; i < ( TOTAL_BUTTON_NO ); i++){
                if(panel.getComponent(i) != prizeButton || panel.getComponent(i) != bombButton1 ||
                        panel.getComponent(i) != bombButton2){
                    ( (JButton) panel.getComponent( i ) ).setEnabled(false);
                }
            }
        }
    }
}
