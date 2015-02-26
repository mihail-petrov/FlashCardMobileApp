package com.mentormateacademy.flashcardmobileclient.component.customComponents.editText;

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


    private String formattedText;

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

        // get content from editText element
        String editTextContent = editTextContext.getText().toString().trim();
        String[] words = editTextContent.split(" ");

        //
        if (item.getItemId() == R.id.bold_item) {

            styleWordNode(editTextContent, words, "b");
            return false;
        }

        if (item.getItemId() == R.id.italic_item) {

            styleWordNode(editTextContent, words, "i");
            return false;
        }

        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }


    /**
     * CONTROL METHODS
     =================================================================*/

    /**
     * @param content
     * @param words
     * @param styleElement
     */
    private void styleWordNode(String content, String[] words, String styleElement) {

        // map words
        mapEditTextContent(content, words);

        // start
        int start = editTextContext.getSelectionStart();
        int end = editTextContext.getSelectionEnd();

        // extract the word
        String word = content.substring(start, end);

        // change style of the element
        setStyleAttributeToWordNode(word, styleElement);

        // commit result
        formattedText = setWordNodeStyleAttribute(words);
        Spanned htmlResult = Html.fromHtml(formattedText);

        editTextContext.setText(htmlResult);
    }

    /**
     *
     * @param wordNode
     * @param styleElement
     */
    private void setStyleAttributeToWordNode(String wordNode, String styleElement) {

        if (wordTree.containsKey(wordNode)) {

            ArrayList<String> styleAttributes = wordTree.get(wordNode);

            if (styleAttributes.contains(styleElement)) {
                styleAttributes.remove(styleElement);
            } else {
                styleAttributes.add(styleElement);
            }
        }
    }

    /**
     * @param content
     * @param words
     */
    private void mapEditTextContent(String content, String[] words) {

        if (editTextCash == null || !content.equals(editTextCash)) {
            map(words);

            // cash the input text into a local variable
            editTextCash = content;
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

    /**
     *
     * @return
     */
    private String setWordNodeStyleAttribute(String[] words) {

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

    public String getFormattedText(){

//        String lineFormat = this.formattedText.replaceAll("\n", "</br>");
//        return lineFormat;
        return this.formattedText;
    }
}
