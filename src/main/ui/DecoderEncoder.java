package ui;

import model.Key;
import model.Text;
import model.WorkSpace;

import java.lang.reflect.Array;
import java.util.Scanner;

public class DecoderEncoder {
    WorkSpace ws;
    boolean programOn;
    Scanner scanner;

    public DecoderEncoder() {
        scanner = new Scanner(System.in);
        programOn = true;
        ws = new WorkSpace();
        String input;

        // the following while loop with if statement was inspired by CPSC210-Project-Demo
        while (programOn) {
            printOptions();
            input = scanner.nextLine();
            if (input.equals("1")) {
                encodeText();
            } else if (input.equals("2")) {
                decodeText();
            } else if (input.equals("3")) {
                programOn = false;
            } else {
                System.out.println("Sorry, I did not understand you, please try again.");
            }
        }
    }

    private void printOptions() {
        System.out.println("Would you like to encode or try to decode a text?");
        System.out.println("Type '1' if you would like to encode a text");
        System.out.println("Type '2' if you would like to decode a text");
        System.out.println("Type '3' to quit.");
    }

    private void encodeText() {
        String input;
        System.out.println("Please, type text that you want to encode.");
        input = scanner.nextLine();
        Text text = new Text();
        text.addText(input);
        ws.setText(text);
        workWithText();
    }

    private void textInstructions() {
        System.out.println("Your text is: ");
        System.out.println(ws.getText().printText());
        System.out.println("Your encoded text is: ");
        ws.getText().encryptText();
        System.out.println(ws.getText().printCiphertext());
        System.out.println("Your key is: " + ws.getText().getKey().printKey());
        System.out.println("Please, select the following options:"
                + "\n Type '1' to add key-value pair"
                + "\n Type '2' to quit.");
    }

    private void workWithText() {
        textInstructions();
        String input = scanner.nextLine();
        if (input.equals("1")) {
            addKeyValue();
            workWithText();
        } else if (input.equals("2")) {
            programOn = false;
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            workWithText();
        }
    }

    private void addKeyValue() {
        System.out.println("Please, type character, which you want to encode.");
        String input = scanner.nextLine();
        // the following line uses the method that I found in the following URL:
        // https://www.geeksforgeeks.org/array-get-method-in-java/
        Character key = (Character)Array.get(input.toCharArray(), 0);
        System.out.println("Please, add character you want to encode it with.");
        String input2 = scanner.nextLine();
        Character value = (Character) Array.get(input2.toCharArray(), 0);
        ws.getText().getKey().addKeyValue(key, value);
    }

    private void decodeText() {
        String input;
        System.out.println("Please, type text that you want to decode.");
        input = scanner.nextLine();
        Text text = new Text();
        text.addCiphertext(input);
        ws.setText(text);
        workWithCiphertext();
    }

    private void workWithCiphertext() {
        ciphertextInstructions();
        String input = scanner.nextLine();
        actOnInput(input);
    }

    private void ciphertextInstructions() {
        System.out.println("Your ciphertext is: " + ws.getText().printCiphertext());
        System.out.println("Your decrypted text is: ");
        ws.getText().decryptCiphertext();
        System.out.println(ws.getText().printText());
        System.out.println("Your key is: " + ws.getText().getKey().printKey());
        System.out.println("Please, select the following options:"
                + "\n Type '1' to add key-value pair"
                + "\n Type '3' to save the current key to saved"
                + "\n Type '4' to clear the current key"
                + "\n Type '5' if you want to switch to a saved key"
                + "\n Type '6' to print current key.");
    }

    private void actOnInput(String input) {
        if (input.equals("1")) {
            addValueKey();
            workWithCiphertext();
        }  else if (input.equals("2")) {
            ws.getText().encryptText();
            System.out.println(ws.getText().printText());
        } else if (input.equals("3")) {
            addKeyToSaved();
        } else if (input.equals("4")) {
            Key key = ws.getText().getKey();
            ws.setPreviousKey(key);
            ws.getText().getKey().clear();
            workWithCiphertext();
        } else if (input.equals("5")) {
            getSavedKey();
            workWithCiphertext();
        } else if (input.equals("6")) {
            ws.getText().getKey().printKey();
            workWithCiphertext();
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            workWithCiphertext();
        }
    }

    private void printDecryptedText() {
        ws.getText().decryptCiphertext();
        System.out.println(ws.getText().printText());
    }

    private void addKeyToSaved() {
        ws.addKeySetToSaved(ws.getText().getKey());
        workWithCiphertext();
    }

    private void getSavedKey() {
        if (ws.getSavedKeys().size() == 0) {
            System.out.println("Sorry, you do not have saved keys.");
            workWithCiphertext();
        } else {
            System.out.println("Here are your saved keys:");
            for (Key k : ws.getSavedKeys()) {
                System.out.println("Index: " + ws.getSavedKeys().indexOf(k) + ": " + k.printKey());
            }
            System.out.println(ws.getSavedKeys());
            System.out.println("Please, print index of key you want to use");
            int input = scanner.nextInt();
            Key newKey = ws.getSavedKeys().get(input);
            ws.getText().setKeyMap(newKey);
            System.out.println("Your new key is: " + ws.getText().getKey().printKey());
        }
    }


    private void addValueKey() {
        System.out.println("Please, type character, which you want to decode.");
        String input = scanner.nextLine();
        // the following line uses the method that I found in the following URL:
        // https://www.geeksforgeeks.org/array-get-method-in-java/
        Character value = (Character)Array.get(input.toCharArray(), 0);
        System.out.println("Please, add character you think this character means.");
        String input2 = scanner.nextLine();
        Character key = (Character) Array.get(input2.toCharArray(), 0);
        ws.getText().getKey().addKeyValue(key, value);
        ws.setKey(ws.getText().getKey());
    }
}
