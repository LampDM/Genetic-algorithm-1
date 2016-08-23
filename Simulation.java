/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lamp
 */
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.*;

class Simulation {

    ArrayList<Organism> orgs = new ArrayList<>();

    double best = Double.MAX_VALUE;
    int numOrgs;
    int genNum;
    double avgDist = Double.MAX_VALUE;

    public Simulation(int n) {

        System.out.println("Simulation started - v0.01");
        genNum = 0;
        numOrgs = n;

        StdDraw.setCanvasSize(600, 600);

        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);

        firstGen();

        while (!(avgDist < 13.0)) {

            for (Organism org : orgs) {

                StdDraw.setPenRadius(0.022);
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.point(org.posx, org.posy);
                StdDraw.setPenColor(StdDraw.YELLOW);
                StdDraw.point(org.goalx, org.goaly);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(0.0005);

                org.executeMoves();

            }

            StdDraw.setCanvasSize(600, 600);

            StdDraw.setXscale(0, 600);
            StdDraw.setYscale(0, 600);

            setAvgDist();
            recombine();

        }

    }

    private void firstGen() {
        for (int k = 0; k < numOrgs; k++) {
            orgs.add(new Organism(32));
        }
    }

    private void recombine() {
        genNum++;
        Collections.sort(orgs);

        ArrayList<Organism> nextGen = new ArrayList<>();

        int start = 0;

        while (!(nextGen.size() == numOrgs)) {

            try {
                nextGen.add(new Organism(mate(orgs.get(start).moves, orgs.get(start + 1).moves)));
                nextGen.add(new Organism(mate(orgs.get(start + 1).moves, orgs.get(start).moves)));
                start++;

            } catch (Exception e) {
                start = 0;
            }

        }

        orgs = nextGen;
        System.out.println("New GEN: " + genNum);
        System.out.println("Average dist: " + avgDist);

    }

    private ArrayList<Integer> mate(ArrayList<Integer> moves, ArrayList<Integer> moves0) {
        ArrayList<Integer> nmoves = new ArrayList<>();
        ArrayList<Integer> giver = moves;

        //Get split points
        Random rand = new Random();

        int n1 = rand.nextInt(32 - 2) + 2;
        int n2 = rand.nextInt(32 - 2) + 2;

        for (int k = 0; k < 32; k++) {

            if (k == n1 || k == n2) {
                if (giver == moves) {
                    giver = moves0;
                } else {
                    giver = moves;
                }
            }

            //Mutation chance
//            int mut = rand.nextInt(201) + 1;
//            if (mut == 13) {
//                nmoves.add(rand.nextInt(8));
//            } else {
//                nmoves.add(giver.get(k));
//            }
            
            nmoves.add(giver.get(k));

        }

        return nmoves;
    }

    private void setAvgDist() {
        double sum = 0;

        for (Organism org : orgs) {
            sum = sum + org.grade;
        }

        avgDist = sum / orgs.size();
    }

}
