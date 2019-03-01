package com.company;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        //File file = new File("b_lovely_landscapes.txt");
        //File file = new File("c_memorable_moments.txt");
        //File file = new File("d_pet_pictures.txt");
        File file = new File("e_shiny_selfies.txt");
        Scanner S = new Scanner(file);
        int n = S.nextInt();
        int currentSize=0;
        int currentScore;
        int lastScore = 0;
        int number;
        int iteracion=0;
        String output;

        char orientation;
        int tagCount;
        String tag;
        LinkedList<Picture> collection = new LinkedList<Picture>();

        for(int i = 0; i<n; i++){
            S.nextLine();
            orientation = S.next().charAt(0);
            tagCount = S.nextInt();
            collection.add(new Picture(i,tagCount));
            collection.getLast().setOrientation(orientation);
            for (int j = 0; j<tagCount; j++){
                tag = S.next();
                collection.getLast().setTags(tag);
            }
        }

        while (true){
            currentScore = 0;
            currentSize = collection.size();
            output = "";
            number = 0;
            System.out.println(iteracion);
            iteracion++;

            for (int i=0; i < currentSize-1; i++){

                collection.get(i).commonTags(collection.get(i+1));
                if(collection.get(i).merge(collection.get(i+1),(int)Math.floor(iteracion/4))){
                    collection.remove(i+1);
                    currentSize = collection.size();

                    if(i < currentSize-1){
                        collection.get(i).commonTags(collection.get(i+1));
                    }
                }
                if(!collection.get(i).getVertical()) {
                    number++;
                    output += collection.get(i).getId() + "\n";
                }
                currentScore= currentScore + collection.get(i).getScore();

            }
            if (currentScore > lastScore){
                lastScore = currentScore;
                BufferedWriter writer = new BufferedWriter(new FileWriter(String.format(file.getName().charAt(0) + "_%s.txt",Integer.toString(lastScore))));
                writer.write(Integer.toString(number) + "\n" + output);
                writer.close();
            }
            Collections.shuffle(collection);


        }


    }
}
