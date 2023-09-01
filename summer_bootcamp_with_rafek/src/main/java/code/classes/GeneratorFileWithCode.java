package code.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GeneratorFileWithCode {

    public void generateFile(String recoveryCode){
        try{
            File file = new File("./files/rcode.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            }else{
                System.out.println("File already exists.");
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(recoveryCode);
            fileWriter.close();

        }catch (IOException e){
            System.out.println("Problem with file");
            e.printStackTrace();
        }
    }

    public String getRecoveryCodeFromFile(){
        try{
            File file = new File("./files/rcode.txt");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()){
                return scanner.nextLine();
            }
        }catch (FileNotFoundException e){
            System.out.println("Problem with reading file");
            e.printStackTrace();
        }
        return null;
    }
}
