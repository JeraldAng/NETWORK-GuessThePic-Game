package server;

import model.Pictures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ServerModel {

    private ArrayList<Pictures> pic;
    private int cindex;

    public ServerModel() {
        pic = new ArrayList<>();
        cindex = 0;
        init();
    }

    private void init() {
        try {
            Scanner sc = new Scanner(new File("data.csv"));

            while (sc.hasNextLine()) {
                String[] temp = sc.nextLine().split(",");
                String word = temp[1];
                String[] imagepath = new String[4];

                for (int i = 0; i < 4; i++) {
                    imagepath[i] = temp[i+2];
                }
                pic.add(new Pictures(temp[0], word, imagepath));
            }

            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void next(){
        cindex = new Random().nextInt(pic.size());
    }

    public Pictures get(){
        return pic.get(cindex);
    }

    public ArrayList<Pictures> getPic() {
        return pic;
    }
}
