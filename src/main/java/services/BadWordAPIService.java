package Services;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BadWordAPIService {
    private List<String> badWords;

    public BadWordAPIService() {
        badWords = loadBadWords();
    }

    private List<String> loadBadWords() {
        List<String> words = new ArrayList<>();
        try {
            // Read JSON file from the classpath
            InputStream inputStream = getClass().getResourceAsStream("/BadWords.json");
            if (inputStream != null) {
                String content = new String(inputStream.readAllBytes());

                // Parse JSON content
                JSONObject json = new JSONObject(content);
                JSONArray badWordsArray = json.getJSONArray("badWords"); // Change to "badWords" lowercase

                // Extract bad words from JSON array
                for (int i = 0; i < badWordsArray.length(); i++) {
                    words.add(badWordsArray.getString(i));
                }
            } else {
                System.err.println("BadWords.json file not found in the classpath.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    public boolean containsBadWord(String text) {
        for (String word : badWords) {
            if (text.contains(word)) {
                return true;
            }
        }
        return false;
    }

    public String censorBadWords(String text) {
        for (String word : badWords) {
            // Create a regular expression to match the word regardless of case
            // The \\b ensures that the word is matched only if it's a whole word
            String regex = "\\b" + Pattern.quote(word) + "\\b";
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);

            // Replace occurrences of the word with asterisks
            StringBuffer stringBuffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(stringBuffer, "*".repeat(word.length()));
            }
            matcher.appendTail(stringBuffer);
            text = stringBuffer.toString();
        }
        return text;
    }
}