package persistence;

import model.Key;
import model.Text;
import model.WorkSpace;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Represents reader that reads WorkSpace JSON data from file
 *
 * CITATION: The code in this class has been based on CPSC210-Project-Demo and
 *  * JsonSerializationDemo projects.
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads workspace from file and returns workspace object. Throws IOE exception
    // if an error occurs during reading the file.
    public WorkSpace read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkSpace(jsonObject);

    }

    // EFFECTS: parses workspace jsonObject and returns it
    private WorkSpace parseWorkSpace(JSONObject jsonObject) {
        WorkSpace ws = new WorkSpace();
        Text t = parseText((JSONObject) jsonObject.get("text"));
        Key k = parseKey((JSONObject) jsonObject.get("key"));
//        Text previousText = parseText((JSONObject) jsonObject.get("previousText"));
//        Key previousKey = parseKey((JSONObject) jsonObject.get("previousKey"));
//        JSONArray jsonArray = jsonObject.getJSONArray("savedTexts");
//        parseTexts(jsonArray, ws);
        JSONArray jsonArray1 = jsonObject.getJSONArray("savedKeys");
        parseKeys(jsonArray1, ws);
        ws.setKey(k);
        ws.setText(t);
//        ws.setPreviousKey(previousKey);
//        ws.setPreviousText(previousText);
        return ws;
    }

    // EFFECTS: parses saveKeys jsonArray and adds keys to saved in workspace
    private void parseKeys(JSONArray jsonArray1, WorkSpace ws) {
        for (Object o: jsonArray1) {
            JSONObject jsonObject = (JSONObject) o;
            Key k = parseKey(jsonObject);
            ws.addKeySetToSaved(k);
        }

    }

//    private void parseTexts(JSONArray jsonArray, WorkSpace ws) {
//        for (Object o : jsonArray) {
//            JSONObject jsonObject = (JSONObject) o;
//            Text t = parseText(jsonObject);
//            ws.addTextToSaved(t);
//        }
//    }

    //EFFECTS: parses text jsonObject and returns it
    private Text parseText(JSONObject text) {
        Text t = new Text();
        Key key = parseKey((JSONObject) text.get("key"));
        t.setKeyMap(key);
        JSONArray jsonText = (JSONArray) text.get("text");
        ArrayList<Character> txt = new ArrayList<>();
        int length = jsonText.length();
        for (int i = 0; i < length; i++) {
            String c = (String) jsonText.get(i);
            char[] a = c.toCharArray();
            Character ch = (Character) Array.get(a, 0);
            txt.add(ch);
        }
        JSONArray jsonCiphertext = (JSONArray) text.get("ciphertext");
        ArrayList<Character> ctxt = new ArrayList<>();
        ctxt = parseCiphertextField(length, jsonCiphertext);
        t.addText(txt);
        t.addCiphertext(ctxt);
        return t;
    }

    // EFFECTS: parses ciphertext field in text JSONObject and returns ciphertext
    // as ArrayList<Character>
    private ArrayList<Character> parseCiphertextField(int length,
                                      JSONArray jsonCiphertext) {
        ArrayList<Character> text = new ArrayList<>();
        int clength = jsonCiphertext.length();
        for (int i = 0; i < clength; i++) {
            String c = (String) jsonCiphertext.get(i);
            char[] a = c.toCharArray();
            Character ch = (Character) Array.get(a, 0);
            text.add(ch);
        }
        return text;
    }

    // EFFECTS: parses key jsonObject and returns it
    private Key parseKey(JSONObject key) {
        Key k = new Key();
        HashSet<Character> sureList = new HashSet<>();
//        JSONArray jsonArray = key.getJSONArray("sureList");
//        parseSureList(sureList, jsonArray);
//        for (Character c : sureList) {
//            k.addKeyToSure(c);
//        }

        // the following way to parse hash map was inspired by
        // answer on URL: https://www.edureka.co/community/7982/iterate-over-a-jsonobject
        HashMap<Character,Character> keyMapFromJson = new HashMap<>();
        JSONObject jsonKeyMap = (JSONObject) key.get("keyMap");
        JSONArray keyMapNames = jsonKeyMap.names();
        if (keyMapNames == null) {
            Key nullKey = new Key();
            return nullKey;
        }
        for (int i = 0; i < keyMapNames.length(); i++) {
            String keyString = keyMapNames.getString(i);
            char[] keyCharacterArray = keyString.toCharArray();
            Character keyCharacter = (Character) Array.get(keyCharacterArray,0);

            String valueString = jsonKeyMap.getString(keyString);
            char[] valueCharacterArray = valueString.toCharArray();
            Character valueCharacter = (Character) Array.get(valueCharacterArray, 0);
            keyMapFromJson.put(keyCharacter, valueCharacter);
        }
        k.setWholeKeySet(keyMapFromJson);
        return k;
    }
//
//    private HashSet<Character> parseSureList(HashSet<Character> sureList, JSONArray jsonArray) {
//        for (Object o: jsonArray) {
//            JSONObject jsonObject = (JSONObject) o;
//            String c = jsonObject.getString("c");
//            char[] a = c.toCharArray();
//            Character ch = (Character) Array.get(a, 0);
//            sureList.add(ch);
//        }
//        return sureList;
//    }
}


