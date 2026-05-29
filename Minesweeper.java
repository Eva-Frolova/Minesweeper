import javax.swing.*;
import java.awt.event.*;
import java.awt.Font;
import java.util.ArrayList;

//Main class where everything is ran
/*
 * @author Eva
 */
public class Minesweeper {
    
    private static final ArrayList<Tile> TileList = new ArrayList<>();
    
    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame f= new JFrame();
        
        //The loops create button objects and assigns properties
        for(int row = 0; row < 10; row++){
            for(int col = 0; col < 10; col++){
                Tile tile = new Tile(row, col);
                tile.getButton().setBounds(col*40, row*40, 40, 40);
                tile.getButton().setFont(new Font("Arial", Font.PLAIN, 10));
                tile.getButton().setText("");
                f.add(tile.getButton());
                TileList.add(tile);
                
                //Adds scanner that monitors the clicks and preforms output
                tile.getButton().addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        assignNum();
                        tile.Opening();
                        openIfEmpty();
                    }
                });
            }    
        }
        
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
    
    //Method assigns number of mines around a tile
    public static void assignNum(){
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
                //left col
                else if(TileList.get(i).getY() == 0){
                    if(TileList.get(i - 10).getIsMine()){mineNum++;}
                    if(TileList.get(i - 9).getIsMine()){mineNum++;}
                    if(TileList.get(i + 1).getIsMine()){mineNum++;}
                    if(TileList.get(i + 10).getIsMine()){mineNum++;}
                    if(TileList.get(i + 11).getIsMine()){mineNum++;}
                }
                //right col
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
        }
    
    //Method opens tiles that are empty
    public static void openIfEmpty(){
        for (int i = 0; i < TileList.size(); i++){
            if(TileList.get(i).getOpen() == true && TileList.get(i).getNum() == 0){
            
            //edge tiles
            if (!(TileList.get(i).getX() > 0 && TileList.get(i).getX() < 9 &&
            TileList.get(i).getY() > 0 && TileList.get(i).getY() < 9)){
                //upper left corner
                if(TileList.get(i).getX() == 0 && TileList.get(i).getY() == 0){
                    TileList.get(1).Opening();
                    TileList.get(10).Opening();
                    TileList.get(11).Opening();
                }
                //upper right corner
                else if(TileList.get(i).getX() == 0 && TileList.get(i).getY() == 9){
                    TileList.get(8).Opening();
                    TileList.get(18).Opening();
                    TileList.get(19).Opening();
                }
                //bottom left corner
                else if(TileList.get(i).getX() == 9 && TileList.get(i).getY() == 0){
                    TileList.get(80).Opening();
                    TileList.get(81).Opening();
                    TileList.get(91).Opening();
                }
                //bottom right corner
                else if(TileList.get(i).getX() == 9 && TileList.get(i).getY() == 9){
                    TileList.get(88).Opening();
                    TileList.get(89).Opening();
                    TileList.get(98).Opening();
                }
                //left col
                else if(TileList.get(i).getY() == 0){
                    TileList.get(i - 10).Opening();
                    TileList.get(i - 9).Opening();
                    TileList.get(i + 1).Opening();
                    TileList.get(i + 10).Opening();
                    TileList.get(i + 11).Opening();
                }
                //right col
                else if(TileList.get(i).getY() == 9){
                    TileList.get(i - 11).Opening();
                    TileList.get(i - 10).Opening();
                    TileList.get(i - 1).Opening();
                    TileList.get(i + 9).Opening();
                    TileList.get(i + 10).Opening();
                }
                //upper row
                else if(TileList.get(i).getX() == 0){
                    TileList.get(i - 1).Opening();
                    TileList.get(i + 1).Opening();
                    TileList.get(i + 9).Opening();
                    TileList.get(i + 10).Opening();
                    TileList.get(i + 11).Opening();
                }
                //bottom row
                else if(TileList.get(i).getX() == 9){
                    TileList.get(i - 11).Opening();
                    TileList.get(i - 10).Opening();
                    TileList.get(i - 9).Opening();
                    TileList.get(i - 1).Opening();
                    TileList.get(i + 1).Opening();
                }
                else{} // <- it's just there
                
            }
            else{
                //tiles that don't touch the edge
                TileList.get(i - 11).Opening();
                TileList.get(i - 10).Opening();
                TileList.get(i - 9).Opening();
                TileList.get(i - 1).Opening();
                TileList.get(i + 1).Opening();
                TileList.get(i + 9).Opening();
                TileList.get(i + 10).Opening();
                TileList.get(i + 11).Opening();
            }
            }
        }
        }
  
}

//Class stores information and behavior of an individual tile
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
    
    //Setters and getters
    //Return and manage private variables
    public JButton getButton(){return button;}
    public boolean getIsMine(){return isMine;}
    public int getNum(){return tileNum;}
    public void setNum(int num){tileNum = num;}
    public boolean getOpen(){return isOpen;}
    public void setOpen(boolean num){isOpen = num;}
    public int getX(){return tileX;}
    public int getY(){return tileY;}
    
    //randomly assigns if tile is a mine
    public boolean assignMine(){
        int randomNum = (int)(Math.random() * 5); //5 = 20 minese average
        if (randomNum == 0){tileNum = -1; return true;} else{return false;}
    }
    
    //Opens a tile
    public void Opening(){
        if(getNum() == 0){
            getButton().setVisible(false);
            setOpen(true);
        }
        else if(getIsMine()){
            getButton().setText(getNum() + "");
            System.out.println("KABOOM!");
        }
        else{
            getButton().setText(getNum() + "");
        }
    }    
}
