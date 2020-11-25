package console;

import model.Key;
import model.Text;
import model.WorkSpace;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DecoderEncoder {
    WorkSpace ws;
    boolean programOn;
    Scanner scanner;
    JsonWriter writer = new JsonWriter("./data/workspace.json");
    JsonReader reader = new JsonReader("./data/workspace.json");

    // MODIFIES: this
    // EFFECTS: constructs DecoderEncoder object and starts console interface by displaying main menu
    // and acting on user input
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
                encodeOptions();
            } else if (input.equals("2")) {
                decodeOptions();
            } else if (input.equals("3")) {
                programOn = false;
            } else {
                System.out.println("Sorry, I did not understand you, please try again.");
            }
        }
    }

    // EFFECTS: prints the main menue options
    private void printMainMenuOptions() {
        System.out.println("Would you like to encode or try to decode a text?");
        System.out.println("Type '1' if you would like to encode a text");
        System.out.println("Type '2' if you would like to decode a text");
        System.out.println("Type '3' to quit.");
    }

    // EFFECTS: prints options for encoding and acts on user input
    public void encodeOptions() {
        printEncodeOptions();
        String input;
        input = scanner.nextLine();
        if (input.equals("1")) {
            try {
                loadWorkSpace();
            } catch (IOException e) {
                System.out.println("Failed to load file!");
            }
            encodeText();
        } else if (input.equals("2")) {
            addTextToBeEncoded();
        } else if (input.equals("3")) {
            programOn = false;
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            encodeOptions();
        }
    }

    // EFFECTS: prints encode options
    private void printEncodeOptions() {
        System.out.println("Type '1' if you would like to load text to be encoded from file");
        System.out.println("Type '2' if you would like to load a new text");
        System.out.println("Type '3' to quit.");
    }

    // EFFECTS: prints decode options and acts on user input
    public void decodeOptions() {
        printDecodeOptions();
        String input;
        input = scanner.nextLine();
        if (input.equals("1")) {
            try {
                loadWorkSpace();
            } catch (IOException e) {
                System.out.println("Failed to load file!");
            }
            encodeText();
        } else if (input.equals("2")) {
            addCiphertextToBeDecoded();
        } else if (input.equals("3")) {
            programOn = false;
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            encodeOptions();
        }
    }

    // EFFECTS: prints decode options
    private void printDecodeOptions() {
        System.out.println("Type '1' if you would like to load text to be decoded from file");
        System.out.println("Type '2' if you would like to load a new text");
        System.out.println("Type '3' to quit.");
    }

    // EFFECTS: Adds user input as a text to workspace object and envokes options for it through encodeText()
    private void addTextToBeEncoded() {
        String input;
        System.out.println("Please, type text that you want to encode.");
        input = scanner.nextLine();
        Text text = new Text();
        text.addText(input);
        ws.setText(text);
        encodeText();
    }

    // EFFECTS: displays options available for text encoding and acts on the user input for these options
    private void encodeText() {
        instructionForTextEncoding();
        String input = scanner.nextLine();
        if (input.equals("1")) {
            addKeyValue();
            encodeText();
        } else if (input.equals("2")) {
            programOn = false;
        } else if (input.equals("3")) {
            saveFileOption();
            encodeText();
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            encodeText();
        }
    }


    // EFFECTS: Prints input text and ciphertext produced from the input text and the key used for
    // encoding options for encoding text menu
    private void instructionForTextEncoding() {
        System.out.println("Your text is: ");
        System.out.println(ws.getText().printText());
        System.out.println("Your encoded text is: ");
        ws.getText().encryptText();
        System.out.println(ws.getText().printCiphertext());
        System.out.println("Your key is: " + ws.getText().getKey().printKey());
        System.out.println("Please, select the following options:"
                + "\n Type '1' to add key-value pair"
                + "\n Type '2' to quit."
                + "\n Type '3' to save the current workspace to file.");
    }

    // EFFECTS: adds Key-Value to Text in Workspace with first user input being key and second
    // being value.
    private void addKeyValue() {
        ArrayList<Character> list = getCharacters("Please, type character, which you want to encode.",
                "Please, type character with which you want to encode it with.");
        Character keyInput = list.get(0);
        Character valueInput = list.get(1);
        ws.getText().getKey().addKeyValue(keyInput, valueInput);
        ws.setKey(ws.getText().getKey());
    }

    // MODIFIES: this
    // EFFECTS: sets input as ciphertext in Text and sets this Text in Workspace, then leads to
    // decoding options through decodeCiphertext();
    private void addCiphertextToBeDecoded() {
        String input;
        System.out.println("Please, type text that you want to decode.");
        input = scanner.nextLine();
        Text text = new Text();
        text.addCiphertext(input);
        ws.setText(text);
        decodeCiphertext();
    }

    // EFFECTS: prints options for decoding and acts on the user input on these options
    private void decodeCiphertext() {
        instructionsForTextDecoding();
        String input = scanner.nextLine();
        actOnInputForDecodingOptions(input);
    }


    // MODIFIES: this
    // EFFECTS: acts on user input for ciphertext decoding
    private void actOnInputForDecodingOptions(String input) {
        if (input.equals("1")) {
            addValueKey();
            decodeCiphertext();
        } else if (input.equals("2")) {
            addKeyToSaved();
        } else if (input.equals("3")) {
            clearCurrentKey();
            decodeCiphertext();
        } else if (input.equals("4")) {
            getSavedKey();
            decodeCiphertext();
        } else if (input.equals("5")) {
            saveFileOption();
            encodeText();
        } else {
            System.out.println("Sorry, I did not understand you, please try again.");
            decodeCiphertext();
        }
    }

    // EFFECTS: prints ciphertext and its decrypted version and the current key used for
    // decryption, then prints options for decoding
    private void instructionsForTextDecoding() {
        System.out.println("Your ciphertext is: " + ws.getText().printCiphertext());
        System.out.println("Your decrypted text is: ");
        ws.getText().decryptCiphertext();
        System.out.println(ws.getText().printText());
        System.out.println("Your key is: " + ws.getText().getKey().printKey());
        System.out.println("Please, select the following options:"
                + "\n Type '1' to add key-value pair"
                + "\n Type '2' to save the current key to saved"
                + "\n Type '3' to clear the current key"
                + "\n Type '4' if you want to switch to a saved key"
                + "\n Type '5' to save the current workspace to file.");
    }

    // EFFECTS: adds key-value pair to Text in Workspace with first user input being value,
    // and second input being key.
    private void addValueKey() {
        ArrayList<Character> list = getCharacters("Please, type character, which you want to decode.",
                "Please, type character, you think the character stands for");
        Character keyInput = list.get(1);
        Character valueInput = list.get(0);

        ws.getText().getKey().addKeyValue(keyInput, valueInput);
        ws.setKey(ws.getText().getKey());
    }

    // MODIFIES: Workspace
    // EFFECTS: adds the current key to saved keys in workspace.
    private void addKeyToSaved() {
        ws.addKeySetToSaved(ws.getText().getKey());
        decodeCiphertext();
    }

    private void clearCurrentKey() {
//        ws.setPreviousKey(ws.getKey());
        ws.getText().getKey().clear();
        ws.setKey(ws.getText().getKey());
    }

    // MODIFIES: this
    // EFFECTS: prints menu prompting the user to choose one of saved keys and sets the chosen key as
    // current key in workspace. If there are no saved keys, indicates that to the user.
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
            ws.getText().setKey(newKey);
            System.out.println("Your new key is: " + ws.getText().getKey().printKey());
        }
    }


    // EFFECTS: retrieves two string inputs from the user sequentially, prinitng a message before
    // each retrieval, converts the inputs to Character and outputs the two inputs
    // as ArrayList<Character>.
    private ArrayList<Character> getCharacters(String messageOne, String messageTwo) {
        System.out.println(messageOne);
        String input = scanner.nextLine();
        // the following line uses the method that I found in the following URL:
        // https://www.geeksforgeeks.org/array-get-method-in-java/
        Character key = (Character) Array.get(input.toCharArray(), 0);
        System.out.println(messageTwo);
        String input2 = scanner.nextLine();
        Character value = (Character) Array.get(input2.toCharArray(), 0);
        ArrayList<Character> list = new ArrayList<>();
        list.add(key);
        list.add(value);
        return list;
    }

    // EFFECTS: saves workspace to file, prints message if it was successful
    // and leads to encodeText() method
    private void saveFileOption() {
        try {
            saveWorkSpace();
            System.out.println("Work space saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        }
    }

    // EFFECTS: saves workspace to json file
    private void saveWorkSpace() throws FileNotFoundException {
        writer.open();
        writer.write(ws);
        writer.close();
    }

    // EFFECTS: loads work space from json file
    private void loadWorkSpace() throws IOException {
        ws = reader.read();
    }
}
