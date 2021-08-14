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

public class CommonquestionsActivity extends AppCompatActivity {

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

        wordsList.add(new Word("What is your name?", "名前は何ですか。(Namae wa nan desu ka?)", R.drawable.questions_placeholder, R.raw.name_question));
        wordsList.add(new Word("I am <your name>", "<name> と申します。/  私は (name) です。(Watashi wa <name> desu)", R.drawable.questions_placeholder,R.raw.name_ans));
        wordsList.add(new Word("Where are you from?", "出身はどこですか。(Shusshin wa doko desu ka?)", R.drawable.questions_placeholder, R.raw.where_are_you_from));
        wordsList.add(new Word("I am from India", "私はインド出身です (Watashi wa Indo shusshin desu)", R.drawable.questions_placeholder, R.raw.watashi_wa_indo_shusshin_desu));
        wordsList.add(new Word("Where do you live?", "どこに住んでいますか。 (Doko ni sunde imasu ka?)", R.drawable.questions_placeholder, R.raw.doko_nii_sunde_imasu_ka));
        wordsList.add(new Word("I live in <location>", "<Location> に住んでいます。(<location> ni sundeimasu)", R.drawable.questions_placeholder, R.raw.ni_sunde_imasu));
        wordsList.add(new Word("What is your job / occupation?", "仕事は何ですか。 (Shigoto wa nan desu ka?)", R.drawable.questions_placeholder, R.raw.shigoto_wa_nan_desu_ka));
        wordsList.add(new Word("I do <your job>", "(job) をやっています。(Game wo yatteimasu)", R.drawable.questions_placeholder, R.raw.game_wo_yatteimasu));
        wordsList.add(new Word("Can you speak Japanese?", "日本語を話せますか。 (Nihongo wo hanasemasu ka?)", R.drawable.questions_placeholder, R.raw.nihongo_wo_hanasemasu_ka));
        wordsList.add(new Word("Yes, I can speak", "はい、はなせます。(Hai, hanasemasu)", R.drawable.questions_placeholder, R.raw.hai_hanasemasu));
        wordsList.add(new Word("Do you like Japanese food?", "日本食が好きですか。 (Nihon shoku ga suki desuka?)", R.drawable.questions_placeholder, R.raw.nihon_shoku_gaa_suki_desuka));
        wordsList.add(new Word("I like it", "好きですよ。(Suki desu yo!)", R.drawable.questions_placeholder, R.raw.suki_desu_yo));
        wordsList.add(new Word("How are you?", "元気ですか。(Genki desu ka?)", R.drawable.questions_placeholder, R.raw.genki_desu_ka));
        wordsList.add(new Word("I am good!", "元気です！(Genki desu!)", R.drawable.questions_placeholder, R.raw.genki_desu));
        wordsList.add(new Word("What is this?", "これは何ですか。(Kore wa nan desu ka?)", R.drawable.questions_placeholder, R.raw.kore_wa_nan_desu_ka));
        wordsList.add(new Word("This is <object>", "これは <object> です。 (Kore wa <object> desu)", R.drawable.questions_placeholder, R.raw.kore_wa_computer_desu));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.questions_bg_color);
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
                    mMediaPlayer = MediaPlayer.create(CommonquestionsActivity.this, clickedWord.getAudioResourceId());
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