package com.mentormateacademy.flashcardmobileclient.customComponents;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.mentormateacademy.flashcardmobileclient.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CustomCallback implements ActionMode.Callback {

    /**
     * PROPERTIES
     * =================================================================
     */

    private Map<String, ArrayList<String>> wordTree;
    private EditText editTextContext;

    private String editTextCash = null;

    /**
     * CONSTRUCTORS
     * =================================================================
     */

    public CustomCallback(EditText context) {
        this.editTextContext = context;
        wordTree = new HashMap<>();
    }

    /**
     * PUBLIC METHODS
     * =================================================================
     */

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.removeItem(android.R.id.selectAll);
        menu.removeItem(android.R.id.cut);
        menu.removeItem(android.R.id.copy);
        menu.removeItem(android.R.id.paste);

        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(R.menu.item_context, menu);
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

        if (item.getItemId() == R.id.bold_item) {

            styleWordNode("b");
            return true;
        }

        if (item.getItemId() == R.id.italic_item) {

            styleWordNode("i");
            return true;
        }

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
//        mode.finish();
    }


    /**
     * CONTROL METHODS
     =================================================================*/

    private void styleWordNode(String styleElement) {

        mapEditTextContent();

        int start = editTextContext.getSelectionStart();
        int end = editTextContext.getSelectionEnd();

        // extract the word
        String word = editTextContext.getText().toString().substring(start, end);

        if (wordTree.containsKey(word)) {
            wordTree.get(word).add(styleElement);
        }

        String result = splitEditTextWords();
        Spanned htmlResult = Html.fromHtml(result);
        editTextContext.setText(htmlResult);
    }

    /**
     *
     */
    private void mapEditTextContent() {

        String editTextContent = editTextContext.getText().toString().trim();

        // This is a new text information and the algoritum neads to map
        // the whole code for the first time
        if (editTextCash == null) {

            String[] words = editTextContent.split(" ");
            map(words);

            // cash the input text into a local variable
            editTextCash = editTextContent;
        } else {
            boolean areEquals = editTextContent.equals(editTextCash);

            if (!areEquals) {
                String[] words = editTextContent.split(" ");
                map(words);

                // cash the input text into a local variable
                editTextCash = editTextContent;
            }
        }
    }

    /**
     * @param words
     */
    private void map(String[] words) {
        // map the elements
        for (String word : words) {
            if (!wordTree.containsKey(word)) {
                wordTree.put(word, new ArrayList<String>());
            }
        }
    }


    private String splitEditTextWords() {

        // split the word
        String[] words = editTextContext.getText().toString().trim().split(" ");

        // sample StringBuilder
        StringBuilder builder = new StringBuilder();

        // put every word into the HashMap
        for (String word : words) {

            String wordElement = word;

            ArrayList<String> element = wordTree.get(word);

            if (element.contains("b")) {
                wordElement = "<b>" + wordElement + "</b>";
            }

            if (element.contains("i")) {
                wordElement = "<i>" + wordElement + "</i>";
            }

            builder.append(wordElement).append(" ");
        }

        return builder.toString();
    }


}
