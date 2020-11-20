package ui;

import model.Key;
import model.WorkSpace;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.Vector;

public class WorkSpaceGUI extends JFrame {
    WorkSpace workSpace;
    TextUI textUI;
    CipherTextUI cipherTextUI;
    KeyTable keyTable;
    SavedKeysGUI savedKeysGUI;
    JsonWriter writer = new JsonWriter("./data/workspace.json");
    JsonReader reader = new JsonReader("./data/workspace.json");

    public WorkSpaceGUI() {
        super("myFrame");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createWorkspace();

        MenuGUI menuBar = new MenuGUI(this);
        setJMenuBar(menuBar);

        JPanel topPanel = initializePageStartPanel();
        add(topPanel, BorderLayout.PAGE_START);

        JPanel centrePanel = initializeCentrePanel();
        add(centrePanel);

        JPanel bottomPanel = initializeBottomPanel();
        add(bottomPanel, BorderLayout.PAGE_END);

        keyTable = new KeyTable(this);
        add(keyTable, BorderLayout.LINE_END);

        pack();
        System.out.println(this.getSize());
        setVisible(true);
    }

    private JPanel initializeBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        JLabel picture = initializePicture();
        bottomPanel.add(picture);
        JTextArea explanation = initializeExplanationText();
        bottomPanel.add(explanation);
        return bottomPanel;
    }

    private JTextArea initializeExplanationText() {
        JTextArea explanation = new JTextArea(10,10);
        explanation.setEditable(false);
        explanation.setLineWrap(true);
        explanation.setText("This is a tool to encode and try to decode a simple"
                + "substitution cipher\n"
                + "Type in or copy and paste the text you want to encode/decode into the  \n"
                + "the input area. Then, you will see that all the symbols that appear in \n"
                + "the text are in the table on the right in the \"Input symbols\" column. \n"
                + "For every symbol, type in a symbol you want to encode/decode it with \n"
                + "into the \"Output column\" and hit Enter. The output text will be updated \n"
                + "so that the encoded/decoded symbol will be substituted as you indicated. \n"
                + "If a symbol does not have an output symbol that can substitute it, it will \n"
                + "appear as \"-\" in the output text.\n"
                + "\n"
                + "The picture on the right is an example of Caesar cipher, which is an example of\n"
                + "a simple substitution cipher that we are doing here. In Caesar cipher, encryption\n"
                + "occurs by simple shift of the alphabet letters. Picture from Wikimedia Commons.");
        return explanation;
    }

    private JLabel initializePicture() {
        File image = new File("data/Caesar_Shift_Cipher_Wheel.png");
        BufferedImage caesar = null;
        try {
            caesar = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = caesar.getScaledInstance(170,210,
                Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(dimg);
        return new JLabel(icon);
    }

    private JPanel initializeCentrePanel() {
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel,BoxLayout.X_AXIS));

        //centre panel left part
        JPanel centrePanelLeft = new JPanel();
        centrePanelLeft.setLayout(new BoxLayout(centrePanelLeft, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Input text");
        textUI = new TextUI(this);
        centrePanelLeft.add(label1);
        centrePanelLeft.add(textUI);
        centrePanel.add(centrePanelLeft);

        //centre panel right part
        JPanel centrePanelRight = new JPanel();
        centrePanelRight.setLayout(new BoxLayout(centrePanelRight, BoxLayout.Y_AXIS));
        JLabel label2 = new JLabel("Output text");
        cipherTextUI = new CipherTextUI(this);
        centrePanelRight.add(label2);
        centrePanelRight.add(cipherTextUI);
        centrePanel.add(centrePanelRight);
        return centrePanel;
    }

    private JPanel initializePageStartPanel() {
        savedKeysGUI = new SavedKeysGUI(this);
        Button saveKeyButton = new Button("Save current key", this, "save");
        JLabel savedKeyLabel = new JLabel("Select Key");

        Button clearButton = new Button("Clear workspace", this, "clear");
        Button chooseKey = new Button("Choose selected key", this, "choose");

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.X_AXIS));
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(savedKeyLabel);
        topPanel.add(savedKeysGUI);
        topPanel.add(saveKeyButton);
        topPanel.add(chooseKey);
        topPanel.add(clearButton);
        return topPanel;
    }

    private void createWorkspace() {
        workSpace = new WorkSpace();
    }

    public static void main(String[] args) {
        new WorkSpaceGUI();
    }

    public Vector<Vector> produceKeyVector() {
        Set<Character> keySet = workSpace.getText().getKey().getKeySet();
        Vector<Vector> vector = new Vector<>();
        for (Character c: keySet) {
            Vector<Character> row = new Vector<>();
            row.add(c);
            Character value = workSpace.getText().getKey().getValue(c);
            row.add(value);
            vector.add(row);
        }
        return vector;
    }

    public void saveData() {
        try {
            saveWorkSpace();
            JOptionPane.showMessageDialog(this, "File was saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File was not found!");
        }
    }

    // EFFECTS: saves workspace to json file
    private void saveWorkSpace() throws FileNotFoundException {
        writer.open();
        writer.write(workSpace);
        writer.close();
    }

    public void loadPreviousData() throws IOException {
        workSpace = reader.read();

        keyTable.updateKeyTableUI(produceKeyVector());
        workSpace.getText().encryptText();

        String text = workSpace.getText().printText();
        textUI.textArea.setText(null);
        textUI.textArea.insert(text, 0);

        String ciphertext = workSpace.getText().printCiphertext();
        cipherTextUI.ciphertextArea.setText(null);
        cipherTextUI.ciphertextArea.insert(ciphertext, 0);

        savedKeysGUI.clear();
        for (Key k: workSpace.getSavedKeys()) {
            String keyName = k.getName();
            savedKeysGUI.keyList.addItem(keyName);
        }
        savedKeysGUI.savedKeysSoFar = workSpace.getSavedKeys().size() + 1;
    }

    public void clear() {
        workSpace.getSavedKeys().clear();
        workSpace.getText().addText("");
        workSpace.getText().getKey().clear();
        keyTable.updateKeyTableUI(produceKeyVector());
        workSpace.getText().encryptText();

        String text = workSpace.getText().printText();
        textUI.textArea.setText(null);
        textUI.textArea.insert(text, 0);

        String ciphertext = workSpace.getText().printCiphertext();
        cipherTextUI.ciphertextArea.setText(null);
        cipherTextUI.ciphertextArea.insert(ciphertext, 0);
        savedKeysGUI.clear();
    }
}
