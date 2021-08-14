package com.example.nihongo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilymembersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    // handles audio focus while playing an audio resource

    private AudioManager mAudioManager;

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayerInstance();
        }
    };

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayerInstance();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // instantiating the AudioManager object to request audio focus

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // an ArrayList which stores the elements of Word data type

        final ArrayList<Word> wordsList = new ArrayList<Word>();

        // adding the elements of the word data type (to compose an array of both english as well as japanese words)

        wordsList.add(new Word("Family Member", "かぞく (kazoku)", R.drawable.familymembers, R.raw.family_member));
        wordsList.add(new Word("Grandfather", "そふ (sofu san)", R.drawable.grandfather, R.raw.sofu_san));
        wordsList.add(new Word("Grandmother", "そぼ (sobo san)", R.drawable.grandmother, R.raw.sobo_san));
        wordsList.add(new Word("Father", "ちち (chichi / otou san)", R.drawable.father, R.raw.otou_san));
        wordsList.add(new Word("Mother", "はは (haha / okaa san)", R.drawable.mother, R.raw.okaa_san));
        wordsList.add(new Word("Uncle", "おじ (oji san)", R.drawable.uncle, R.raw.ojii_san));
        wordsList.add(new Word("Aunt", "おば (oba san)", R.drawable.aunt, R.raw.obaa_san));
        wordsList.add(new Word("Parents", "りょうしん (ryoushin)", R.drawable.parents, R.raw.ryoushin));
        wordsList.add(new Word("Brothers", "きょうだい (kyoudai)", R.drawable.brothers, R.raw.kyoudai));
        wordsList.add(new Word("Sisters", "しまい (shimai)", R.drawable.sisters, R.raw.shimai));
        wordsList.add(new Word("Elder Brother", "あに (ani / oni san)", R.drawable.elderbrother, R.raw.onii_san));
        wordsList.add(new Word("Younger Brother", "おとうと (otouto san)", R.drawable.youngerbrother, R.raw.otouto_san));
        wordsList.add(new Word("Elder Sister", "あね (ane / onee san)", R.drawable.eldersister, R.raw.onee_san));
        wordsList.add(new Word("Younger Sister", "いもうと (imouto san)", R.drawable.youngersister, R.raw.imouto_san));
        wordsList.add(new Word("Married Couple", "ふうふ (fuufu)", R.drawable.marriedcouple, R.raw.fuufu));
        wordsList.add(new Word("Husband", "しゅじん (shujin)", R.drawable.husband, R.raw.shujin));
        wordsList.add(new Word("Wife", "かない (kanai)", R.drawable.wife, R.raw.kanai));
        wordsList.add(new Word("Cousin (Male/Female)", "いとこ (itoko)", R.drawable.elderbrother, R.raw.itoko));
        wordsList.add(new Word("Children", "こども (kodomo)", R.drawable.brothers, R.raw.kodomo));
        wordsList.add(new Word("Son", "むすこ (musuko san)", R.drawable.elderbrother, R.raw.musuko_san));
        wordsList.add(new Word("Daughter", "むすめ (musume / ojou san)", R.drawable.eldersister, R.raw.ojou_san));
        wordsList.add(new Word("Nephew", "おい (oi)", R.drawable.youngerbrother, R.raw.oi));
        wordsList.add(new Word("Niece", "めい (mei)", R.drawable.youngersister, R.raw.mei));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.familymembers_bg_color);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(customWordAdapter);

        // This is the code for using Grid view (in case we don't wanna use List view, or depending upon the scenario)

        // GridView gridview = (GridView) findViewById(R.id.list);
        // gridview.setAdapter(items);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the word object which the user clicks on (with the help of position parameter)
                Word clickedWord = wordsList.get(position);

                releaseMediaPlayerInstance();

                // request audio focus

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // check if audio focus request has been granted

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    // now audio focus has been gained by the system, so now we can play the audio resource

                    //play the corresponding media file for the clicked word (by passing the res id of the media file for the corresponding clicked word)
                    mMediaPlayer = MediaPlayer.create(FamilymembersActivity.this, clickedWord.getAudioResourceId());
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }

            }
        });

    }

    /**
     * When the activity is in  the background (stopped), we don't want to listen to the audio files.
     * Hence we implement stop() method to release the media player resources as soon as the activity switches to background (stops)
     */

    @Override
    protected void onStop() {
        super.onStop();

        releaseMediaPlayerInstance();
    }



    /**
     * Clean up the media player by releasing its resources.
     */

    private void releaseMediaPlayerInstance() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            mMediaPlayer = null;

            // abandon the audio focus after the audio resource has finished playing or the activity state is interrupted

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}