package com.mentormateacademy.flashcardmobileclient.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.mentormateacademy.flashcardmobileclient.R;
import com.mentormateacademy.flashcardmobileclient.component.baseComponents.BaseActivity;
import com.mentormateacademy.flashcardmobileclient.data.singletons.DataHolder;
import com.mentormateacademy.flashcardmobileclient.database.helper.DatabaseRepository;
import com.mentormateacademy.flashcardmobileclient.models.Deck;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.deck_card_fragment.SingleShowCardFragment;
import com.mentormateacademy.flashcardmobileclient.ui.fragments.experimental.EditCardViewFragment;


public class CardActivity

        extends
            BaseActivity

        implements
            EditCardViewFragment.fragmentActionCallback
{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initFragment();
    }

    public void initFragment(){
        setToolbarTitle("Show Card");
        setFragment(SingleShowCardFragment.newInstance(), false);
    }

    public void initEditCardFragment(){
        setToolbarTitle("Edit Card");
        setFragment(EditCardViewFragment.newInstance(), false);
    }



    // Activity - Option Menu
    // =====================================================

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_card_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.edit_card) {
            initEditCardFragment();
        }

        if(item.getItemId() == R.id.delete_card) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("Are you sure").setTitle("Delete Flash Card");

            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    Bundle bundle = new Bundle();
                    bundle.putString("_id", String.valueOf(DataHolder.getData().getCardId()));

                    DatabaseRepository.getRepository(CardActivity.this).getCardRepository().delete(bundle);

                    // delete decks count
                    Bundle deckBundle = new Bundle();
                    deckBundle.putString("_id", String.valueOf(DataHolder.getData().getDeckId()));

                    Deck deckElement = DatabaseRepository.getRepository(CardActivity.this).getDeckRepository().readBy(deckBundle).get(0);
                    deckElement.setCardSize(deckElement.getCardSize() - 1);
                    DatabaseRepository.getRepository(CardActivity.this).getDeckRepository().update(deckElement, deckBundle);

                    DataHolder.getData().setCardSize(deckElement.getCardSize());

                    Intent openActivity = new Intent(CardActivity.this, CardListActivity.class);
                    startActivity(openActivity);
                }
            });

            alertDialog.setNegativeButton("Not Now", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });


            AlertDialog dialog = alertDialog.create();
            dialog.show();
        }

        return super.onOptionsItemSelected(item);
    }

    // Activity - Fragment Callback
    // =====================================================

    @Override
    public void onCardSaved() {
        initFragment();
    }
}
