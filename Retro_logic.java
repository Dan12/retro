//This class does all the logic and draws components

//import statments
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Retro_logic extends JPanel {
    //initial class called to set up variables
    public Retro_logic(){
        reset = false;
        start = false;
        left = false;
        right = false;
        space = true;
        spacepress = false;
        pause = false;
        spikeshow = false;
        emp = false;
        empactivate = false;
        gameover = false;
        pmes = "Start";
        empusesec = 0;
        empbar = 0;
        charxpos = 285;
        charypos = 410;
        bulspawnsec = 1;
        changecolorsec = 1;
        bombspawnsec = 1;
        score = 0;
        levelscore = 0;
        spikemove = 6;
        jumpsec = 0;
        jumppos = 0;
        lives = 3;
        bombspawnlev = 0;
        empbarfillspeed = 0;
        
        bombsshowarray = new boolean[55];
        for (int i = 0; i<55;i++){
            bombsshowarray[i] = false;
        }
        
        bulshowarray = new boolean[20];
        for (int i = 0; i<20;i++){
            bombsshowarray[i] = false;
        }
        
        bombsxposarray = new int[55];
        for (int i = 0; i<55;i++){
            bombsxposarray[i] = -50;
        }
        
        bombsyposarray = new int[55];
        for (int i = 0; i<55;i++){
            bombsyposarray[i] = -50;
        }
        
        bulxposarray = new int[20];
        for (int i = 0; i<20;i++){
            bulxposarray[i] = -50;
        }
        
        bulyposarray = new int[20];
        for (int i = 0; i<20;i++){
            bulyposarray[i] = -50;
        }
        
        rainbowcolor = new int[20];
        for (int i = 0; i<20;i++){
            rainbowcolor[i] = 1;
        }
        
        spikexpos = new int[3];
        spikexpos[0]=-44;
        spikexpos[1]=-27;
        spikexpos[2]=-10;
        
        spikeypos = new int[3];
        spikeypos[0]=440;
        spikeypos[1]=410;
        spikeypos[2]=440;
    }
    //graphics public void
    public void paintComponent(Graphics g) {
        if (reset){
            start = false;
            left = false;
            right = false;
            space = true;
            spacepress = false;
            pause = false;
            spikeshow = false;
            emp = false;
            empactivate = false;
            gameover = false;
            pmes = "Start";
            empusesec = 0;
            empbar = 0;
            charxpos = 285;
            charypos = 410;
            bulspawnsec = 1;
            changecolorsec = 1;
            bombspawnsec = 1;
            score = 0;
            levelscore = 0;
            spikemove = 6;
            jumpsec = 0;
            jumppos = 0;
            lives = 3;
            bombspawnlev = 0;
            empbarfillspeed = 0;
            bombsshowarray = new boolean[55];
            for (int i = 0; i<55;i++){bombsshowarray[i] = false;}
            bulshowarray = new boolean[20];
            for (int i = 0; i<20;i++){bombsshowarray[i] = false;}
            bombsxposarray = new int[55];
            for (int i = 0; i<55;i++){bombsxposarray[i] = -50;}
            bombsyposarray = new int[55];
            for (int i = 0; i<55;i++){bombsyposarray[i] = -50;}
            bulxposarray = new int[20];
            for (int i = 0; i<20;i++){bulxposarray[i] = -50;}
            bulyposarray = new int[20];
            for (int i = 0; i<20;i++){bulyposarray[i] = -50;}
            rainbowcolor = new int[20];
            for (int i = 0; i<20;i++){rainbowcolor[i] = 1;}
            spikexpos = new int[3];
            spikexpos[0]=-44;
            spikexpos[1]=-27;
            spikexpos[2]=-10;
            spikeypos = new int[3];
            spikeypos[0]=440;
            spikeypos[1]=410;
            spikeypos[2]=440;
            reset = false;
        }
        super.paintComponent(g);
        //draw bottom
        g.setColor(Color.GREEN);
        g.fillRect(0, 440, 700, 60);
        //draw spike
        g.setColor(new Color(50,150,50));
        g.fillPolygon(spikexpos, spikeypos, 3);
        //draw score, lives, and emp bar
        g.setColor(Color.BLACK);
        String scorestr = String.format("%s", score);
        String livestr = String.format("%s", lives);
        g.drawString("Score: ",20, 464);
        g.drawString(scorestr, 64, 464);
        g.drawString("Lives: ",500, 464);
        g.drawString(livestr, 544, 464);
        g.drawString("EMP Bar:", 220, 464);
        g.setColor(Color.BLACK);
        g.fillRect(280, 452, 103, 16);
        g.setColor(Color.BLUE);
        g.fillRect(281, 453, (int) Math.round(empbar), 14);
        
        //if lives are less than or equal to zero, set gameover to true
        if (lives<=0){
            gameover = true;
            start = false;
        }
        
        //if emp bar is full, show string emp ready
        if (emp){
            Random red = new Random();
            int red1 = 1 + red.nextInt(160);
            Random blue = new Random();
            int blue1 = 1 + blue.nextInt(160);
            Random green = new Random();
            int green1 = 1 + green.nextInt(160);
            g.setColor(new Color(red1,green1,blue1));
            g.fillRect(281, 453, 101, 14);
            g.setColor(Color.WHITE);
            g.drawString("EMP Ready!", 297, 464);
        }
        
        //if up arrow clicked but emp not full, set empactive to false
        if (empactivate && !emp){
            empactivate=false;
        }

        //check if bullet hit bomb
        for (int bombi = 0; bombi<55; bombi++){
            for (int buli = 0; buli<20; buli++){
                if (bulxposarray[buli]+6>=bombsxposarray[bombi] && bulxposarray[buli]<=bombsxposarray[bombi]+20 && bulyposarray[buli]<=bombsyposarray[bombi]+20 && bulyposarray[buli]+20>=bombsyposarray[bombi] && bulshowarray[buli] && bombsshowarray[bombi]){
                    bulshowarray[buli] = false;
                    bombsshowarray[bombi] = false;
                    score++;
                    levelscore++;
                }
            }
        }
        
        //check if character hit spike
        float bcheck1 = spikeypos[0]+((30/17)*spikexpos[0]);
        float bcheck2 = spikeypos[0]-((30/17)*spikexpos[2]);
        float charxposfloat = charxpos;
        float charyposfloat = charypos;
        if (!empactivate){
            if ((charyposfloat+30>=(-30/17)*(charxposfloat+30)+bcheck1 && charyposfloat+30>=(30/17)*(charxposfloat+30)+bcheck2)|| (charyposfloat+30>=(-30/17)*charxposfloat+bcheck1 && charyposfloat+30>=(30/17)*charxposfloat+bcheck2)){
                lives--;
                spikeshow = false;
                spikexpos[0]=-44;
                spikexpos[1]=-47;
                spikexpos[2]=-10;
            }
        }
        
        //draw and animate bombs and check if any bombs hit bottom
        for (int i = 0; i<55; i++){
            if (bombsshowarray[i]){
                g.setColor(Color.BLACK);
                g.fillRect(bombsxposarray[i], bombsyposarray[i], 20, 20);
                g.setColor(new Color(255,255-(bombsyposarray[i]/2),0));
                g.fillRect(bombsxposarray[i]+1, bombsyposarray[i]+1, 18, 18);
                if (start && !empactivate){
                    bombsyposarray[i]+=1;
                }
                if (bombsyposarray[i]>420 && !empactivate){
                    bombsshowarray[i] = false;
                    lives--;
                }
            }
        }
        
        //draws all bullets and sets rainbow color of bullets
        for (int i = 0; i<20; i++){
            if (bulshowarray[i]){
                switch(rainbowcolor[i]){
                    case 1:
                        g.setColor(Color.BLUE);
                        break;
                    case 2:
                        g.setColor(new Color(111, 255, 255));
                        break;
                    case 3:
                        g.setColor(new Color(111, 0, 255));
                        break;
                    case 4:
                        g.setColor(Color.RED);
                        break;
                    case 5:
                        g.setColor(Color.ORANGE);
                        break;   
                    case 6:
                        g.setColor(Color.YELLOW);
                        break;
                    case 7:
                        g.setColor(Color.GREEN);
                        break;
                }
                if (start){
                    if (rainbowcolor[i]>=7 && changecolorsec>=10){
                        rainbowcolor[i] = 1;
                    }
                    else if (changecolorsec>=10){
                        rainbowcolor[i]++;
                        changecolorsec = 1;
                    }
                    else {
                        changecolorsec++;
                    }
                }
                g.fillRect(bulxposarray[i], bulyposarray[i], 6, 20);
                if (start){
                    bulyposarray[i]-=8;
                }
                if (bulyposarray[i]<0){
                    bulshowarray[i] = false;
                }
            }
            else {
                rainbowcolor[i] = 1;
            }
        }
        
        //draw character
        g.setColor(Color.RED);
        g.fillRect(charxpos, charypos, 30, 30);
        g.setColor(Color.BLACK);
        g.fillRect(charxpos+10, charypos-8, 10, 8);
        
        //set initial and pause message
        if (!start && !gameover){
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            if ("Start".equals(pmes)){
                g.drawString("Press Enter Key To "+pmes, 80, 240);
            }
            else{
                g.drawString("Press Enter Key To "+pmes, 40, 240);
            }
        }
        
        //if p button pressed, pause game
        if (pause) {
            start = false;
            pmes = "Resume";
            pause = false;
        }
        
        //if game is going on
        if (start){
            
            //set pause to false
            pause = false;
            
            //move character
            if(left && charxpos>0 && !spacepress && !empactivate){
                charxpos -= 8;
            }
            if(left && charxpos>0 && spacepress && !empactivate){
                charxpos -= 2;
            }
            if(right && charxpos<570 && !spacepress && !empactivate){
                charxpos += 8;
            }
            if(right && charxpos<570 && spacepress && !empactivate){
                charxpos += 2;
            }
            
            //check if enough time has passed for new bullet to spawn
            if (bulspawnsec<6){
                bulspawnsec++;
            }
            
            //if time, spawn bullet
            else if (bulspawnsec>=6 && space && !empactivate){
                for (int i = 0; i<40; i++){
                    if (!bulshowarray[i]){
                        bulshowarray[i] = true;
                        bulxposarray[i] = charxpos+12;
                        bulyposarray[i] = charypos;
                        break;
                    }
                }
                bulspawnsec = 1;
            }
            
            //check if enough time has passed for new bomb
            if (bombspawnsec<20-(bombspawnlev*2)){
                bombspawnsec++;
            }
            
            //if enough time, spawn new bomb
            else{
                Random place = new Random();
                int place1 = 1 + place.nextInt(54);
                Random xrand = new Random();
                int xrand1 = 1 + xrand.nextInt(580);
                if (!bombsshowarray[place1] && !empactivate){
                    bombsshowarray[place1] = true;
                    bombsxposarray[place1] = xrand1;
                    bombsyposarray[place1] = 0;
                }
                bombspawnsec = 1;
            }
            
            //check if able to spawn spike, if so, check which side of field character is on and spawn on other side
            Random spikegen = new Random();
            int spikegen1 = 1 + spikegen.nextInt(50);
            if (spikegen1==25 && !spikeshow && !empactivate){
                spikeshow = true;
                if (charxpos>285){
                    spikemove = 7;
                    spikexpos[0] = -34;
                    spikexpos[1] = -17;
                    spikexpos[2] = 0;
                }
                else {
                    spikemove = -7;
                    spikexpos[0] = 600;
                    spikexpos[1] = 617;
                    spikexpos[2] = 634;
                }
            }
            
            //if spike is active, move it
            if (spikeshow && !empactivate){
                for (int i = 0; i<3; i++){
                    spikexpos[i]+=spikemove;
                }
            }
            
            //if spike reaches end of field, don't show it
            if (spikexpos[0]<-44 || spikexpos[0]>610){
                spikeshow = false;
            }
               
            //if space is pressed, jump
            if (spacepress && !empactivate){
                jumppos = (float) ((12*jumpsec)-(.6*jumpsec*jumpsec));
                charypos= 410-((int) Math.round(jumppos));
                jumpsec++;
            }
            
            //when jump if over, set spacepress to false and reset variables
            if (jumpsec>1 && jumppos<=0){
                spacepress = false;
                jumpsec = 0;
                charypos = 410;
            }
            
            //if emp not loaded, load it
            if (!emp){
                empbar += (float) (0.1-(.009*empbarfillspeed));
            }
            
            if (empbar>=100){
                emp = true;
            }
            
            //if the amount of time since emp used is greater than 60, reset emp
            if (empusesec>60){
                empusesec=0;
                emp=false;
                empactivate=false;
                empbar = 0;
            }
            
            //if emp active, create blue sphere and flash and reset field
            if (emp && empactivate && empusesec<=60){
                if (empusesec<20){
                    g.setColor(new Color(0,0,255,100));
                    g.fillOval((charxpos+15)-empusesec*40, (charypos+15)-empusesec*40, empusesec*80, empusesec*80);
                }
                if (empusesec==20){
                    spikeshow=false;
                    spikexpos[0]=-44;
                    spikexpos[1]=-27;
                    spikexpos[2]=-10;
                    for (int i = 0; i<20; i++){
                        bulshowarray[i]=false;
                        bulxposarray[i] = -50;
                        bulyposarray[i] = -50;
                    }
                    for (int i = 0; i<55; i++){
                        if (bombsshowarray[i]){
                            score++;
                            levelscore++;
                        }
                        bombsshowarray[i]=false;
                        bombsxposarray[i] = -50;
                        bombsyposarray[i] = -50;
                    }
                }
                if (empusesec>=20){
                    g.setColor(new Color(255,255,255,255-((empusesec-20)*6)));
                    g.fillRect(0,0,600,600);
                }
                empusesec++;
            }
            if (levelscore>=75 && score!=0 && bombspawnlev<7){
                bombspawnlev++;
                empbarfillspeed++;
                levelscore-=75;
            }
        }
        
        //if gameover, display gameover message and score
        if (gameover){
            g.setColor(new Color(10,10,10,160));
            g.fillRect(60, 150, 500, 160);
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
            String fscorestr = String.format("%s", score);
            g.drawString("Game Over. Final Score:", 80, 200);
            g.drawString(fscorestr,280,280);
        }
    }
    //initializing variables
    int score;
    int levelscore;
    int charxpos;
    int charypos;
    int bulspawnsec;
    int bombspawnsec;
    int changecolorsec;
    int spikemove;
    int jumpsec;
    int lives;
    float empbar;
    int empusesec;
    int bombspawnlev;
    int empbarfillspeed;
    float jumppos;
    int spikexpos[];
    int spikeypos[];
    int bombsxposarray[];
    int bombsyposarray[];
    int bulxposarray[];
    int bulyposarray[];
    int rainbowcolor[];
    String pmes;
    boolean bulshowarray[];
    boolean bombsshowarray[];
    boolean spikeshow;
    boolean start;
    boolean left;
    boolean right;
    boolean space;
    boolean pause;
    boolean spacepress;
    boolean emp;
    boolean empactivate;
    boolean gameover;
    boolean reset;
}