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

public class PhrasesActivity extends AppCompatActivity {

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

        wordsList.add(new Word("Yes", "はい (hai)", R.drawable.phrases_placeholder, R.raw.hai));
        wordsList.add(new Word("No", "いいえ (iie)", R.drawable.phrases_placeholder, R.raw.iie));
        wordsList.add(new Word("Hello", "こんにちは (konnichiwa)", R.drawable.phrases_placeholder, R.raw.konnichiwa));
        wordsList.add(new Word("Goodbye", "じゃね (ja ne / sayonara / mata ne)", R.drawable.phrases_placeholder, R.raw.mata_ne));
        wordsList.add(new Word("Thank You", "ありがとう (arigatou gozaimasu)", R.drawable.phrases_placeholder, R.raw.arigatou));
        wordsList.add(new Word("I'm Sorry", "ごめんなさい (gomen nasai)", R.drawable.phrases_placeholder, R.raw.gomen));
        wordsList.add(new Word("Excuse Me / Sorry", "すみません (sumimasen)", R.drawable.phrases_placeholder, R.raw.sumimasen));
        wordsList.add(new Word("I / Myself", "私 (watashi)", R.drawable.phrases_placeholder, R.raw.watashi));
        wordsList.add(new Word("You / Your", "あなた (anata)", R.drawable.phrases_placeholder, R.raw.anata));
        wordsList.add(new Word("This", "これ (kore)", R.drawable.phrases_placeholder, R.raw.kore));
        wordsList.add(new Word("That", "それ (sore)", R.drawable.phrases_placeholder, R.raw.sore));
        wordsList.add(new Word("He", "彼 (kare)", R.drawable.phrases_placeholder, R.raw.kare));
        wordsList.add(new Word("She", "彼女 (kanojo)", R.drawable.phrases_placeholder, R.raw.kanojo));
        wordsList.add(new Word("They", "彼ら (karera)", R.drawable.phrases_placeholder, R.raw.karera));
        wordsList.add(new Word("Today", "今日 (kyou)", R.drawable.phrases_placeholder, R.raw.kyou));
        wordsList.add(new Word("Tomorrow", " 明日 (ashita)", R.drawable.phrases_placeholder, R.raw.ashita));
        wordsList.add(new Word("Yesterday", "昨日 (kinou)", R.drawable.phrases_placeholder, R.raw.kinou));
        wordsList.add(new Word("Now", "今 (ima)", R.drawable.phrases_placeholder, R.raw.ima));
        wordsList.add(new Word("Before", "前に (mae ni)", R.drawable.phrases_placeholder, R.raw.mae));
        wordsList.add(new Word("After", "後で (ato de)", R.drawable.phrases_placeholder, R.raw.aato));
        wordsList.add(new Word("Good Morning", "おはようございます (ohayou gozaimasu)", R.drawable.phrases_placeholder, R.raw.ohayou));
        wordsList.add(new Word("Good Afternoon", "こんにちは (konnichiwa)", R.drawable.phrases_placeholder, R.raw.konnichiwa));
        wordsList.add(new Word("Good Evening", "こんばんは (konbanwa)", R.drawable.phrases_placeholder, R.raw.konbanwa));
        wordsList.add(new Word("Good Night", "おやすみなさい (oyasumi / oyasuminasai)", R.drawable.phrases_placeholder, R.raw.oyasuminasai));
        wordsList.add(new Word("Long time no see!", "久しぶり (hisashiburi)", R.drawable.phrases_placeholder, R.raw.hisashiburi));
        wordsList.add(new Word("See you later", "じゃあまたね (ja matane)", R.drawable.phrases_placeholder, R.raw.mata_ne));
        wordsList.add(new Word("I'm home", "ただいま (tadaima)", R.drawable.phrases_placeholder, R.raw.tadaima));
        wordsList.add(new Word("Welcome Back!", "お帰りなさい (okaeri / okaerinasai)", R.drawable.phrases_placeholder, R.raw.okaeri));
        wordsList.add(new Word("Please excuse me! (when you leave)", "しすれいします (shitsurei shimasu)", R.drawable.phrases_placeholder, R.raw.shitsurei_shimasu));
        wordsList.add(new Word("Thank you for your hard work!", "おつかれさまでした (otsukaresama deshita)", R.drawable.phrases_placeholder, R.raw.otsukaresama));
        wordsList.add(new Word("I am going", "いてきます (ittekimasu)", R.drawable.phrases_placeholder, R.raw.ittekimasu));
        wordsList.add(new Word("Go and come back", "行ってらっしゃい (itterasshai)", R.drawable.phrases_placeholder, R.raw.itterashai));
        wordsList.add(new Word("I don't understand", "わかりません (wakarimasen)", R.drawable.phrases_placeholder, R.raw.wakarimasen));
        wordsList.add(new Word("It is good", "いいですよ。 (ii desu yo)", R.drawable.phrases_placeholder, R.raw.ii_desu_yoo));
        wordsList.add(new Word("It is bad", "だめです。(dame desu)", R.drawable.phrases_placeholder, R.raw.dame_desu));
        wordsList.add(new Word("Great / I am glad", "良かった (yokatta)", R.drawable.phrases_placeholder, R.raw.yokatta));
        wordsList.add(new Word("You're welcome", "どういたしまして (dou itashimashite)", R.drawable.phrases_placeholder, R.raw.dou_itashimashite));
        wordsList.add(new Word("I speak a little japanese", "少し日本語を話します (sukoshi nihongo wo hanashimasu)", R.drawable.phrases_placeholder, R.raw.sukoshi_nihongo_wo_hanashimasu));
        wordsList.add(new Word("Let's meet again", "また会いましょう (mata aimashou)", R.drawable.phrases_placeholder, R.raw.mata_aimashou));
        wordsList.add(new Word("Congratulations!", "おめでとう (omedeto)", R.drawable.phrases_placeholder, R.raw.omedeto));
        wordsList.add(new Word("Happy Birthday!", "誕生日おめでとう (tanjobi omedeto)", R.drawable.phrases_placeholder, R.raw.tanjobi_omedeto));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.phrases_bg_color);
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
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, clickedWord.getAudioResourceId());
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