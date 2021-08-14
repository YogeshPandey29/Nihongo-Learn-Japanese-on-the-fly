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

public class NumbersActivity extends AppCompatActivity {

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
                    }};


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

        wordsList.add(new Word("Zero", "れい、ゼロ、マル (rei, zero, maru)", R.drawable.zero, R.raw.rei));
        wordsList.add(new Word("One", "いち (ichi)", R.drawable.one, R.raw.ichi));
        wordsList.add(new Word("Two", "に (ni)", R.drawable.two, R.raw.nii));
        wordsList.add(new Word("Three", "さん (san)", R.drawable.three, R.raw.san));
        wordsList.add(new Word("Four", "し、よん (shi, yon)", R.drawable.four, R.raw.yon));
        wordsList.add(new Word("Five", "ご (go)", R.drawable.five, R.raw.go));
        wordsList.add(new Word("Six", "ろく (roku)", R.drawable.six, R.raw.roku));
        wordsList.add(new Word("Seven", "しち、なな (shichi, nana)", R.drawable.seven, R.raw.nana));
        wordsList.add(new Word("Eight", "はち (hachi)", R.drawable.eight, R.raw.hachi));
        wordsList.add(new Word("Nine", "く、きゅう (ku, kyuu)", R.drawable.nine, R.raw.kyuu));
        wordsList.add(new Word("Ten", "じゅう (juu)", R.drawable.ten, R.raw.juu));
        wordsList.add(new Word("Eleven", "じゅういち (juu-ichi)", R.drawable.eleven, R.raw.juu_ichi));
        wordsList.add(new Word("Twelve", "じゅうに (juu-ni)", R.drawable.twelve, R.raw.juu_nii));
        wordsList.add(new Word("Thirteen", "じゅうさん (juu-san)", R.drawable.thirteen, R.raw.juu_san));
        wordsList.add(new Word("Fourteen", "じゅうよん (juu-yon)", R.drawable.fourteen, R.raw.juu_yon));
        wordsList.add(new Word("Fifteen", "じゅうご (juu-go)", R.drawable.fifteen, R.raw.juu_go));
        wordsList.add(new Word("Sixteen", "じゅうろく (juu-roku)", R.drawable.sixteen, R.raw.juu_roku));
        wordsList.add(new Word("Seventeen", "じゅうなな (juu-nana)", R.drawable.seventeen, R.raw.juu_nana));
        wordsList.add(new Word("Eighteen", "じゅうはち (juu-hachi)", R.drawable.eighteen, R.raw.juu_hachi));
        wordsList.add(new Word("Ninteen", "じゅうきゅう (juu-kyu)", R.drawable.ninteen, R.raw.juu_kyu));
        wordsList.add(new Word("Twenty", "にじゅう (ni-juu)", R.drawable.twenty, R.raw.nii_juu));
        wordsList.add(new Word("Ninty Nine", "きゅうじゅうきゅう (kyuu-juu-kyuu)", R.drawable.nintynine, R.raw.kyuu_juu_kyuu));
        wordsList.add(new Word("Hundred", "ひゃく (hyaku)", R.drawable.hundred, R.raw.hyaku));
        wordsList.add(new Word("Thousand", "千 (sen)", R.drawable.thousand, R.raw.sen));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.numbers_bg_color);
        ListView listview = (ListView) findViewById(R.id.list);
        listview.setAdapter(customWordAdapter);

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
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, clickedWord.getAudioResourceId());
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.

                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                }

            }
        });
    }
        // This is the code for using Grid view (in case we don't wanna use List view, or depending upon the scenario)

        // GridView gridview = (GridView) findViewById(R.id.list);
        // gridview.setAdapter(items);


    /**
     * When the acticity is in  the background (stopped), we don't want to listen to the audio files.
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
            // If the media player is not null, then it may be currently playing a sound.
            if (mMediaPlayer != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mMediaPlayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mMediaPlayer = null;

                // abandon the audio focus after the audio resource has finished playing or the activity state is interrupted

                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
}