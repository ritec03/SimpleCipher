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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public DecoderEncoder() {
        scanner = new Scanner(System.in);
        programOn = true;
        ws = new WorkSpace();
        String input;

        // the following while loop with if statement was inspired by CPSC210-Project-Demo
        while (programOn) {
            printMainMenuOptions();
            input = scanner.nextLine();
            if (input.equals("1")) {
                addTextToBeEncoded();
            } else if (input.equals("2")) {
                addCiphertextToBeDecoded();
            } else if (input.equals("3")) {
                programOn = false;
            } else {
                System.out.println("Sorry, I did not understand you, please try again.");
            }
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void printMainMenuOptions() {
        System.out.println("Would you like to encode or try to decode a text?");
        System.out.println("Type '1' if you would like to encode a text");
        System.out.println("Type '2' if you would like to decode a text");
        System.out.println("Type '3' to quit.");
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void addTextToBeEncoded() {
        String input;
        System.out.println("Please, type text that you want to encode.");
        input = scanner.nextLine();
        Text text = new Text();
        text.addText(input);
        ws.setText(text);
        encodeText();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void encodeText() {
        instructionForTextEncoding();
        String input = scanner.nextLine();
        if (input.equals("1")) {
            addKeyValue();
            encodeText();
        } else if (input.equals("2")) {
            programOn = false;
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            encodeText();
        }
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void instructionForTextEncoding() {
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void addCiphertextToBeDecoded() {
        String input;
        System.out.println("Please, type text that you want to decode.");
        input = scanner.nextLine();
        Text text = new Text();
        text.addCiphertext(input);
        ws.setText(text);
        decodeCiphertext();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void decodeCiphertext() {
        instructionsForTextDecoding();
        String input = scanner.nextLine();
        actOnInputForDecodingOptions(input);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void instructionsForTextDecoding() {
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void actOnInputForDecodingOptions(String input) {
        if (input.equals("1")) {
            addValueKey();
            decodeCiphertext();
        }  else if (input.equals("2")) {
            ws.getText().encryptText();
            System.out.println(ws.getText().printText());
        } else if (input.equals("3")) {
            addKeyToSaved();
        } else if (input.equals("4")) {
            clearCurrentKey();
            decodeCiphertext();
        } else if (input.equals("5")) {
            getSavedKey();
            decodeCiphertext();
        } else if (input.equals("6")) {
            System.out.println(ws.getText().getKey().printKey());
            decodeCiphertext();
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            decodeCiphertext();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void addKeyToSaved() {
        ws.addKeySetToSaved(ws.getText().getKey());
        decodeCiphertext();
    }

    private void clearCurrentKey() {
        ws.setPreviousKey(ws.getKey());
        ws.getText().getKey().clear();
        ws.setKey(ws.getText().getKey());
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void getSavedKey() {
        if (ws.getSavedKeys().size() == 0) {
            System.out.println("Sorry, you do not have saved keys.");
            decodeCiphertext();
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
}
