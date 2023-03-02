/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Services;

/**
 *
 * @author Lenovo
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BadWordFilter {
    private List<String> badWords;

    public BadWordFilter() {
        badWords = new ArrayList<>();
        // Load bad words from a text file
        try {
            File file = new File("C:\\Users\\Lenovo\\Desktop\\badwords.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                badWords.add(word);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error loading bad words: " + e.getMessage());
        }
    }

    public boolean containsBadWord(String text) {
        for (String badWord : badWords) {
            if (text.toLowerCase().contains(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
