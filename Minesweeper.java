import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.util.ArrayList;

public class Minesweeper {
    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame f= new JFrame();
        
        ArrayList<Tile> TileList = new ArrayList<>();
        
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                Tile tile = new Tile(row, col);
                tile.getButton().setBounds(col*40, row*40, 40, 40);
                tile.getButton().setFont(new Font("Arial", Font.PLAIN, 10));
                
                tile.getButton().setText("");
                
                f.add(tile.getButton());
                TileList.add(tile);
                
                tile.getButton().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    
                        if(tile.getNum() == 0){
                            tile.getButton().setVisible(false);
                        }
                        else if(tile.getIsMine() == true){
                            tile.getButton().setText(tile.getNum() + "");
                            System.out.println("KABOOM!");
                        }
                        else{
                            tile.getButton().setText(tile.getNum() + "");
                        }
                    }
                });
            }    
        }
        
        //this long for loop assigns numbers to the tiles after the field is created
        for (int i = 0; i < TileList.size(); i++){
            int mineNum = 0;
            
            //tile is a mine
            if(TileList.get(i).getIsMine()){mineNum = -1;}
            
            //edge tiles
            else if (!(TileList.get(i).getX() > 0 && TileList.get(i).getX() < 9 &&
            TileList.get(i).getY() > 0 && TileList.get(i).getY() < 9)){
                //upper left corner
                if(TileList.get(i).getX() == 0 && TileList.get(i).getY() == 0){
                    if(TileList.get(1).getIsMine()){mineNum++;}
                    if(TileList.get(10).getIsMine()){mineNum++;}
                    if(TileList.get(11).getIsMine()){mineNum++;}
                }
                //upper right corner
                else if(TileList.get(i).getX() == 0 && TileList.get(i).getY() == 9){
                    if(TileList.get(8).getIsMine()){mineNum++;}
                    if(TileList.get(18).getIsMine()){mineNum++;}
                    if(TileList.get(19).getIsMine()){mineNum++;}
                }
                //bottom left corner
                else if(TileList.get(i).getX() == 9 && TileList.get(i).getY() == 0){
                    if(TileList.get(80).getIsMine()){mineNum++;}
                    if(TileList.get(81).getIsMine()){mineNum++;}
                    if(TileList.get(91).getIsMine()){mineNum++;}
                }
                //bottom right corner
                else if(TileList.get(i).getX() == 9 && TileList.get(i).getY() == 9){
                    if(TileList.get(88).getIsMine()){mineNum++;}
                    if(TileList.get(89).getIsMine()){mineNum++;}
                    if(TileList.get(98).getIsMine()){mineNum++;}
                }
                //left row 
                else if(TileList.get(i).getY() == 0){
                    if(TileList.get(i - 10).getIsMine()){mineNum++;}
                    if(TileList.get(i - 9).getIsMine()){mineNum++;}
                    if(TileList.get(i + 1).getIsMine()){mineNum++;}
                    if(TileList.get(i + 10).getIsMine()){mineNum++;}
                    if(TileList.get(i + 11).getIsMine()){mineNum++;}
                }
                //right row
                else if(TileList.get(i).getY() == 9){
                    if(TileList.get(i - 11).getIsMine()){mineNum++;}
                    if(TileList.get(i - 10).getIsMine()){mineNum++;}
                    if(TileList.get(i - 1).getIsMine()){mineNum++;}
                    if(TileList.get(i + 9).getIsMine()){mineNum++;}
                    if(TileList.get(i + 10).getIsMine()){mineNum++;}
                }
                //upper row
                else if(TileList.get(i).getX() == 0){
                    if(TileList.get(i - 1).getIsMine()){mineNum++;}
                    if(TileList.get(i + 1).getIsMine()){mineNum++;}
                    if(TileList.get(i + 9).getIsMine()){mineNum++;}
                    if(TileList.get(i + 10).getIsMine()){mineNum++;}
                    if(TileList.get(i + 11).getIsMine()){mineNum++;}
                }
                //bottom row
                else if(TileList.get(i).getX() == 9){
                    if(TileList.get(i - 11).getIsMine()){mineNum++;}
                    if(TileList.get(i - 10).getIsMine()){mineNum++;}
                    if(TileList.get(i - 9).getIsMine()){mineNum++;}
                    if(TileList.get(i - 1).getIsMine()){mineNum++;}
                    if(TileList.get(i + 1).getIsMine()){mineNum++;}
                }
                else{} // <- it's just there
                
            }
            else{
                //tiles that don't touch the edge
                if(TileList.get(i - 11).getIsMine()){mineNum++;}
                if(TileList.get(i - 10).getIsMine()){mineNum++;}
                if(TileList.get(i - 9).getIsMine()){mineNum++;}
                if(TileList.get(i - 1).getIsMine()){mineNum++;}
                if(TileList.get(i + 1).getIsMine()){mineNum++;}
                if(TileList.get(i + 9).getIsMine()){mineNum++;}
                if(TileList.get(i + 10).getIsMine()){mineNum++;}
                if(TileList.get(i + 11).getIsMine()){mineNum++;}
            }
            TileList.get(i).setNum(mineNum);
        }
        
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
}

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Tile {
    private JButton button = new JButton("");
    private int tileX;
    private int tileY;
    private boolean isOpen;
    private int tileNum; //-1 = mine;
    private boolean isMine;
    
    // constructor
    public Tile(int x, int y){ 
        tileX = x;
        tileY = y;
        isOpen = false;
        isMine = assignMine();
    }
    
    public JButton getButton(){return button;}
    public boolean getIsMine(){return isMine;}
    public int getNum(){return tileNum;}
    public void setNum(int num){tileNum = num;}
    public int getX(){return tileX;}
    public int getY(){return tileY;}
    public boolean assignMine(){
        int randomNum = (int)(Math.random() * 5);
        if (randomNum == 0){tileNum = -1; return true;} else{return false;}
    }
}
