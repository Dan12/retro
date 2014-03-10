//This class updates the graphics and responds to key events

//import statments
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Retro extends JFrame implements KeyListener{
    private static String play_check = "";
    
    public Retro() {
        super("Retro");
        panel = new Retro_logic();
        add(panel);
        addKeyListener(this);     
    }
    
    //setting up JFrame
    public static void main(String args[]) throws IOException{
        Retro retro = new Retro();
        retro.setSize(600, 500);
        retro.setDefaultCloseOperation(3);
        retro.setLocation(100, 100);
        retro.setVisible(true);
        retro.setBackground(Color.WHITE);
        //set filepath
        String filePath = new File("").getAbsolutePath();
        String file_name = filePath.concat("/info.txt");

        //get highscore from file
        try {
            ReadFile file = new ReadFile(file_name);
            String[] aryLines = file.OpenFile();
            
            play_check = aryLines[1];
        }

        catch (IOException e){
            System.out.println( e.getMessage() );
        }
        
        if ("no".equals(play_check)){
            JOptionPane.showMessageDialog(null, "How To Play:\nUse The Arrow Keys To Move\nPress Space To Jump\nWhen EMP Bar Is Full, Press The Up Arrow Key To Clear The Field\nShoot Down The Bombs To Get Points\nDon't Let The Bombs Hit The Ground Or Else You Will Lose A Life\nAvoid The Spikes Or Else You Will Lose A Life\nPress The 'V' Button To Pause\nYou Have Three Lives, Good Luck" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
        retro.go();
    }
    
    public void go(){
        //updates graphics several times a second while game is in play
        do {
            try {
                Thread.sleep(20);
            }
            catch(InterruptedException interruptedexception) { 
                System.out.println( interruptedexception.getMessage() );
            }
            repaint();
            //update(getGraphics());
            if (panel.gameover){
                //set filepath
                String filePath = new File("").getAbsolutePath();
                String file_name = filePath.concat("/info.txt");

                //get highscore from file
                try {
                    ReadFile file = new ReadFile(file_name);
                    String[] aryLines = file.OpenFile();

                    high = Integer.parseInt(aryLines[0]);
                    //compares highscore to current score
                    if (panel.score>high){
                        high=panel.score;
                        nhigh=true;
                    }
                    //writes the highscore to file, whether new or old
                    String hs = String.format("%s", high);


                    WriteFile data = new WriteFile(file_name, true);
                    data.writeToFile(hs+"\nyes");

                }

                catch (IOException e){
                    System.out.println( e.getMessage() );
                }

                int fscore=panel.score;
                JOptionPane.showMessageDialog(null, "Your Final Score Was: "+fscore, "Score", JOptionPane.INFORMATION_MESSAGE);
                if (!nhigh)
                JOptionPane.showMessageDialog(null, "High Score: "+high, "High Score", JOptionPane.INFORMATION_MESSAGE);
                if (nhigh)
                JOptionPane.showMessageDialog(null, "You Got a New High Score of: "+high, "New High Score", JOptionPane.INFORMATION_MESSAGE);
                int resp=
                        JOptionPane.showConfirmDialog(null, "Play Again?", "Play Again?", JOptionPane.YES_NO_OPTION);
                if (resp == JOptionPane.YES_OPTION){
                    panel.reset = true;
                }
                else {
                    
                    System.exit(0);
                }
            }
        } while(true);
        
    }

    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case 37://left arrow key
                panel.left = true;
                break;
            case 39://right arrow key
                panel.right = true;
                break;
            case 10://enter key
                panel.start = true;
                break;
            case 86://'v' key
                panel.pause = true;
                break;
            case 32://spacebar
                panel.spacepress = true;
                break;
            case 38://up arrow key
                panel.empactivate = true;
                break;
        }
    }

    public void keyReleased(KeyEvent ke) {
        switch(ke.getKeyCode()) {
            case 37:
                panel.left = false;
                break;
            case 39:
                panel.right = false;
                break;
        }
    }
    
    public void keyTyped(KeyEvent ke) {
    }
    
    //creating variables
    Retro_logic panel;
    int high;
    boolean nhigh;
}
