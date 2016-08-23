
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lamp
 */
class Organism implements Comparable<Organism> {
        
    ArrayList<Integer> moves = new ArrayList<>();
    int posx=300;
    int posy=300;
    
    int goalx=posx+200;
    int goaly=posy-100;
    
    int gensize;
    double grade=Double.MAX_VALUE;
    
    public Organism(int n){
        gensize=n;
        loadMoves();
    }
    
    public Organism(ArrayList<Integer> genes){
        moves=genes;
    }
    
    private void loadMoves() {
        for(int k = 0;k<gensize;k++){
            Random rand = new Random();

            int  n = rand.nextInt(9) + 0;
            moves.add(n);
        }
    }
        
        public void executeMoves() {
            grade=0;
        for(Integer m : moves){
            switch(m){
                
                case 0:
                    //line from current pos down
                    StdDraw.line(posx, posy, posx, posy-20);
                    posy=posy-20;
                    break;
                case 1:
                    //line from current pos left
                    StdDraw.line(posx, posy, posx-20, posy);
                    posx=posx-20;
                    break;
                case 2:
                    //line from current pos up
                    StdDraw.line(posx, posy, posx, posy+20);
                    posy=posy+20;
                    break;
                case 3:
                    //line from current pos right
                    StdDraw.line(posx, posy, posx+20, posy);
                    posx=posx+20;
                    break;
                case 4:
                    //line from current pos left up
                    StdDraw.line(posx, posy, posx-20, posy+20);
                    posx=posx-20;
                    posy=posy+20;
                    break;
                case 5:
                    //line from current pos right up
                    StdDraw.line(posx, posy, posx+20, posy+20);
                    posx=posx+20;
                    posy=posy+20;
                    break;
                case 6:
                    //line from current pos left up
                    StdDraw.line(posx, posy, posx-20, posy-20);
                    posx=posx-20;
                    posy=posy-20;
                    break;
                case 7:
                    //line from current pos right up
                    StdDraw.line(posx, posy, posx+20, posy-20);
                    posx=posx+20;
                    posy=posy-20;
                    break; 
                case 8:
                    //do nothing
                    break;
            }
                    double dist = Math.sqrt((posx-goalx)*(posx-goalx)+(posy-goaly)*(posy-goaly));
                    grade+=dist;
        }
        
        StdDraw.setPenRadius(0.022);
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.point(posx, posy);
        

    }



    @Override
    public int compareTo(Organism o) {
        return Double.compare(this.grade, o.grade);
    }
}
