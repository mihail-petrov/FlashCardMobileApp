package com.mentormateacademy.flashcardmobileclient.customComponents;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.mentormateacademy.demoapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CustomCallback implements ActionMode.Callback {

    private Map<String, ArrayList<String>> wordTree;
    private EditText contextEditText;

    public CustomCallback(EditText context){
        this.contextEditText = context;
        wordTree = new HashMap<>();
    }

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

            // check the content
            mapEditTextContent();

            int start = contextEditText.getSelectionStart();
            int end   = contextEditText.getSelectionEnd();

            // extract the word
            String word = contextEditText.getText().toString().substring(start, end);

            if (wordTree.containsKey(word)) {
                wordTree.get(word).add("b");
            }

            String result       = splitEditTextWords();
            Spanned htmlResult  = Html.fromHtml(result);
            contextEditText.setText(htmlResult);

            // TODO: Remove and wrap
            mode.finish();
            return true;
        }

        if (item.getItemId() == R.id.italic_item) {

                // check the content
                mapEditTextContent();

                int start = contextEditText.getSelectionStart();
                int end = contextEditText.getSelectionEnd();

                // extract the word
                String word = contextEditText.getText().toString().substring(start, end);

                if (wordTree.containsKey(word)) {
                    wordTree.get(word).add("i");
                }

                String result = splitEditTextWords();
                Spanned htmlResult = Html.fromHtml(result);
                contextEditText.setText(htmlResult);

                // TODO: Remove and wrap
                mode.finish();
                return true;
            }

        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    public void mapEditTextContent(){
        // split the word
        String[] words = contextEditText.getText().toString().split(" ");

        for (int i = 0; i < words.length; i++) {

            String wordElement = words[i];

            if(!wordTree.containsKey(words[i]))
            {
                wordTree.put(words[i], new ArrayList<String>());
            }
        }
    }

    public String splitEditTextWords() {

        // split the word
        String[] words = contextEditText.getText().toString().split(" ");

        // sample StringBuilder
        StringBuilder builder = new StringBuilder();

        // put every word into the HashMap
        for (int i = 0; i < words.length; i++) {

            String wordElement = words[i];

            if(!wordTree.containsKey(words[i])){ // The element does not exists INIT MODE
                wordTree.put(words[i], new ArrayList<String>());
            }
            else { // EDIT MODE

                // get element
                ArrayList<String> element = wordTree.get(words[i]);

                if(element.contains("b")){
                    wordElement = "<b>" + wordElement + "</b>";
                }

                if(element.contains("i")){
                    wordElement = "<i>" + wordElement + "</i>";
                }
            }

            builder.append(wordElement + " ");
        }

        return builder.toString();
    }

}
