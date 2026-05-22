import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Minesweeper {
    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame f= new JFrame();
        
        
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                JButton b1 = new JButton("");
                b1.setBounds(col*40, row*40, 40, 40);
                f.add(b1);
                
                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println((int)(Math.random() * 2));
                        b1.setVisible(false); 
                    }
                });
            }    
        }

        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}

public class Tile {
    private JButton button;
    private boolean isOpen;
    private int num; //-1 = mine;
    private boolean isMine;
    ArrayList<Tile> neighbors = new ArrayList<>();
    
    // constructor
    public Tile(){ 
        isOpen = false;
        isMine = assignMine();
        num = 0;
    }
    
    public boolean assignMine(){
        int randomNum = (int)(Math.random() * 33);
        if (randomNum == 0){return true;} else{return false;}
        
    }
    
    
    // if not mine find number of mines next to it 
    
}
