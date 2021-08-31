import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.ArrayList;
import java.util.Scanner;

public class HighScore {
    //call both Arrays
    public ArrayList<String> names = new ArrayList<>();
    public ArrayList<Integer> scores= new ArrayList<>();
    public ArrayList<String> data = new ArrayList<>();
    public int indexHighscore = 100;
    //standard values array + write Highscore file
    public void NewArray() {
        try {
            BufferedWriter hscore = new BufferedWriter(new FileWriter("HiScores.txt"));
                for (int i = 0; i < 5; i++) {
                    names.add("AAA");
                    scores.add(0);
                    hscore.write(names.get(i)+" "+scores.get(i));
                    hscore.newLine();
                }
            hscore.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //get the hiscores from the text file
    public ArrayList<String> ReadScoreFileNames() {
        data.clear();
        try {
            FileInputStream hscore = new FileInputStream("HiScores.txt");
            Scanner myReader = new Scanner(hscore);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
        }catch(IOException e){
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        return data;
    }


    public void WriteScore(int Score){
        System.out.println(indexHighscore);
        System.out.println(names);
        System.out.println(scores);
        ArrayList<String> namesTemp = new ArrayList<>();
        ArrayList<Integer> scoresTemp = new ArrayList<>();
        int j = 0;
        for (int i=0; i<5;i++){
            if (indexHighscore == i){
                namesTemp.add("Tim");
                scoresTemp.add(Score);
            }else{
                namesTemp.add(names.get(j));
                scoresTemp.add(scores.get(j));
                j++;
            }
        }
        System.out.println(namesTemp);
        System.out.println(scoresTemp);
        try {
            BufferedWriter hscore = new BufferedWriter(new FileWriter("HiScores.txt"));
            for (int i = 0; i < 5; i++) {
                hscore.write(namesTemp.get(i)+" "+scoresTemp.get(i));
                hscore.newLine();
            }
            hscore.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
    // check if hiscore file is present and read from it
    public ArrayList<String> ReadScore(){
        //check if the highscore file exists
        File hscore = new File("HiScores.txt");
        try {
            if (hscore.createNewFile()) {
                System.out.println("File needs to be created");
                NewArray();
            } else {
//                System.out.println("file exists");
                data = ReadScoreFileNames();
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
    // get the induvidual score and saperate from the string
    public ArrayList<Integer> GetScore(){
        data = ReadScore();
        scores.clear();
        for (int i=0;i<data.size();i++){
            String induvidualData = data.get(i);
            String[] split = induvidualData.split(" ");
            names.add(split[0]);
            scores.add(Integer.parseInt(split[1]));
        }
        return scores;
    }
    //compare gained score to the high scores
    public Boolean CheckScore(int achievedScore){
        scores = GetScore();
        boolean highscoreCheck = false;

        for (int i = 0; i<scores.size();i++){
            int comparedScore = scores.get(i);
            if (comparedScore < achievedScore){
                highscoreCheck = true;
                indexHighscore = i;
                if(highscoreCheck){
                    break;
                }
            }
        }
        return highscoreCheck;
    }
}
