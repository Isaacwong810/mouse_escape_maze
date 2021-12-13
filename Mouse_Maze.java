/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.s54816829;

/**
 *
 * @author Issac wong
 */

public class Mouse_Maze {
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Mouse mouse = new Mouse();
        int tempx,tempy;
        Maze m = new Maze();
        double sucess=0;
        for(int i=0;i<1000000;i++){
             int maze[][] = {{1,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0},
                            {0,0,0,0,0,0,0}};
             mouse.x=0;
             mouse.y=0;
            tempx= mouse.x;tempy=mouse.y;     //temp is predecessor of mouse
             
            boolean end = false;
            while(!end){     
                mouse.Dice(mouse);     //mouse choose direction randomly               
               if((m.isSafe(maze,mouse.x,mouse.y))){         // if direction is safe      
                   maze[mouse.x][mouse.y]=1;                   // mark the place from 0->1
                   tempx= mouse.x;tempy=mouse.y;                            // update the predecessor
                   if((mouse.x==6) && (mouse.y==6))   // if the mouse is arrive goal
                    {
                        sucess++;                
                        end=true;
                    }

                   else if(((mouse.x+1>6)||(maze[mouse.x+1][mouse.y]==1))&&((mouse.x-1<0)||(maze[mouse.x-1][mouse.y]==1))&&((mouse.y+1>6)||(maze[mouse.x][mouse.y+1]==1))&&((mouse.y-1<0)||(maze[mouse.x][mouse.y-1]==1))) 
                        // mouse reach dead end
                    {
                        end=true;
                    }
               }
               
               else{                                      //if not safe mouse recover the previous location by predecessor and dice again
                   mouse.x=tempx;
                   mouse.y=tempy;
 
               }
               }
            }
       double sucess_rate;
        sucess_rate = sucess/1000000;
        System.out.println("The Monte Carlo simulation result of one millionruns:");
        System.out.println("No. of successful escape:"+sucess);
        System.out.println("Success Rate P:"+sucess_rate);
            
    }
    

} 

class Mouse{
    int x,y;
       public static void Dice(Mouse mouse){
        double P = Math.random();
        if(P<0.2)//N
            mouse.x--;
    
        if(P>=0.2&&P<0.5)//E
            mouse.y++;
            
        if(P>=0.5&&P<0.8)//S
            mouse.x++;
            
        if(P>=0.8&&P<1)//W
           mouse.y--;
          
        
    }
}

    class Maze{
        int N=7;               
        boolean isSafe(int maze[][], int x, int y)
        {
        // if (x, y outside maze) return false
        return (x >= 0 && x < N && y >= 0
                && y < N && maze[x][y] == 0);
        }

    }




