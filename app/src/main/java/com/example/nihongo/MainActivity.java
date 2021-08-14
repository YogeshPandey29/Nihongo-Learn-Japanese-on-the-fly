package com.example.nihongo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // declaring a variable for custom action bar (in main activity)
    private Toolbar custom_action_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // finding the xml layout for the custom action bar using the id
        custom_action_bar = findViewById(R.id.customActionBar);

        // setting the custom action bar on the main activity
        setSupportActionBar(custom_action_bar);

        // Finding the view that shows the Trivia activity
        TextView triviaTextView = (TextView) findViewById(R.id.jpn_trivia);

        // Set a click listener on that view
        triviaTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent triviaIntent = new Intent(MainActivity.this, TriviaActivity.class);
                startActivity(triviaIntent);
            }
        });

        // Finding the view that shows the "Numbers" category
        TextView numbersTextView = (TextView) findViewById(R.id.numbers);

        // Set a click listener on that view
        numbersTextView.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the "numbers" View is clicked on
            @Override
            public void onClick (View view) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        //Finding the view that shows the "Colors" category
        TextView colorsTextView = (TextView) findViewById(R.id.colors);

        //Set a click listener on that view
        colorsTextView.setOnClickListener(new View.OnClickListener() {

            // The code in this method will be executed when the "colors" View is clicked on
            @Override
            public void onClick (View view) {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });

        //Finding the view that shows the "Family Members" category
        TextView familyMembersTextView = (TextView) findViewById(R.id.familyMembers);

        //Set a click listener on that view
        familyMembersTextView.setOnClickListener(new View.OnClickListener() {

            //The code in this method will be executed when the "family members" View is clicked on
            @Override
            public void onClick (View view) {
                Intent familyMembersIntent = new Intent(MainActivity.this, FamilymembersActivity.class);
                startActivity(familyMembersIntent);
            }
        });

        //Finding the view that shows the "Phrases" category
        TextView phrasesTextView = (TextView) findViewById(R.id.phrases);

        //Set a click listener on that view
        phrasesTextView.setOnClickListener(new View.OnClickListener() {

            //The code in this method will be executed when the "phrases" View is clicked on
            @Override
            public void onClick (View view) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });

        //Finding the view that shows the "Common Questions" category
        TextView commonQuestionsTextView = (TextView) findViewById(R.id.commonQuestions);

        //Set a click listener on that view
        commonQuestionsTextView.setOnClickListener(new View.OnClickListener() {

            //The code in this method will be executed when the "common questions" View is clicked on
            @Override
            public void onClick (View view) {
                Intent commonQuestionsIntent = new Intent(MainActivity.this, CommonquestionsActivity.class);
                startActivity(commonQuestionsIntent);
            }
        });

        //Finding the view that shows the "Common Occupations" category
        TextView commonOccupationsTextView = (TextView) findViewById(R.id.occupations);

        //Set a click listener on that view
        commonOccupationsTextView.setOnClickListener(new View.OnClickListener() {

            //The code in this method will be executed when the "common occupations" View is clicked on
            @Override
            public void onClick (View view) {
                Intent commonOccupationsIntent = new Intent(MainActivity.this, CommonoccupationsActivity.class);
                startActivity(commonOccupationsIntent);
            }
        });

        //Finding the view that shows the "Common Places" category
        TextView commonPlacesTextView = (TextView) findViewById(R.id.commonPlaces);

        //Set a click listener on that view
        commonPlacesTextView.setOnClickListener(new View.OnClickListener() {

            //The code in this method will be executed when the "common places" View is clicked on
            @Override
            public void onClick (View view) {
                Intent commonPlacesIntent = new Intent(MainActivity.this, TouristspotsActivity.class);
                startActivity(commonPlacesIntent);
            }
        });
    }

}