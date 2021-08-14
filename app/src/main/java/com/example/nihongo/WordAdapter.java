package com.example.nihongo;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    // resource id for the background color for this list of words

    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID japanese_text_view
        TextView japaneseTextView = (TextView) listItemView.findViewById(R.id.japanese_text_view);

        // set this text on the name TextView
        japaneseTextView.setText(currentWord.getJapaneseTranslation());

        // Find the TextView in the list_item.xml layout with the ID english_text_view
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);

        // set this text on the number TextView
        englishTextView.setText(currentWord.getEnglishTranslation());

        // find the ImageView in the list_item.xml layout with the ID image
        ImageView imageview = (ImageView) listItemView.findViewById(R.id.image);

        if (currentWord.hasImage()) {
            // after getting the imageview, set the image to the imageview by accessing the image via resource id
            imageview.setImageResource(currentWord.getImageResourceId());

            imageview.setVisibility(View.VISIBLE);
        }
        else {
            imageview.setVisibility(View.GONE);
        }

        // set theme (bg color) for this list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // find the color that the resource id maps to
        int colorId = ContextCompat.getColor(getContext(), mColorResourceId);

        // set the bg color of the text container view
        textContainer.setBackgroundColor(colorId);





        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
