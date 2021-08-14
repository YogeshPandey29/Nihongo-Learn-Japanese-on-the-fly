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

public class ColorsActivity extends AppCompatActivity {

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

        wordsList.add(new Word("Red", "赤/レッド (Aka/Reddo)", R.drawable.red, R.raw.aka));
        wordsList.add(new Word("Orange", "橙色/オレンジ (Daidaiiro/Orenji)", R.drawable.orange, R.raw.orenji));
        wordsList.add(new Word("Yellow", "黄色/イエロー (Kiiro/iero-)", R.drawable.yellow, R.raw.kiiro));
        wordsList.add(new Word("Green", "緑/グリーン (Midori/Guri-n)", R.drawable.green, R.raw.midori));
        wordsList.add(new Word("Blue", "青/ブルー (Ao/Buru-)", R.drawable.blue, R.raw.ao));
        wordsList.add(new Word("Purple", "紫/パープル (Murasaki/Pa-puru)", R.drawable.purple, R.raw.murasaki));
        wordsList.add(new Word("Pink", "桃色/ピンク (Momoiro/Pinku)", R.drawable.pink, R.raw.pinku));
        wordsList.add(new Word("Brown", "茶色/ブラウン (Chairo/Buraun)", R.drawable.brown, R.raw.chairo));
        wordsList.add(new Word("Black", "黒/ブラック (Kuro/Burakku)", R.drawable.black, R.raw.kuro));
        wordsList.add(new Word("Grey", "灰色/グレイ (Haiiro/Gurei)", R.drawable.grey, R.raw.hairo));
        wordsList.add(new Word("White", "白/ホワイト (Shiro/Howaito)", R.drawable.white, R.raw.shiro));
        wordsList.add(new Word("Silver", "銀/シルバー (Gin/Shiruba-)", R.drawable.silver, R.raw.gin));
        wordsList.add(new Word("Gold", "金/ゴールド (Kin/Go-rudo)", R.drawable.gold, R.raw.kin));
        wordsList.add(new Word("Light Blue", "水色 (Mizuiro)", R.drawable.lightblue, R.raw.mizuiro));
        wordsList.add(new Word("Beige", "ベージュ (Haiiro/Gurei)", R.drawable.beige, R.raw.hairo));
        wordsList.add(new Word("Light Green", "黄緑 (asamidori)", R.drawable.lightgreen, R.raw.asamidori));
        wordsList.add(new Word("Rainbow Color", "虹色 (nijiiro)", R.drawable.rainbow, R.raw.nijiiro));
        wordsList.add(new Word("Copper", "あかがねいろ (akagane iro)", R.drawable.copper, R.raw.akagane_iro));
        wordsList.add(new Word("Bronze", "せいどういろ (seidou iro)", R.drawable.bronze, R.raw.seidou_iro));
        wordsList.add(new Word("Colorful", "カラフル (karafuru)", R.drawable.colorful, R.raw.karafuru));
        wordsList.add(new Word("Transparent/Clear", "とうめい (toumei)", R.drawable.transparent, R.raw.toumei));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.colors_bg_color);
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
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, clickedWord.getAudioResourceId());
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