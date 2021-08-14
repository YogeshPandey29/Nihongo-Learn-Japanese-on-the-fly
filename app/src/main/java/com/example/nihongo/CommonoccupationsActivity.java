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

public class CommonoccupationsActivity extends AppCompatActivity {

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

        wordsList.add(new Word("Doctor", "いしゃ (ishi)", R.drawable.doctor, R.raw.ishi));
        wordsList.add(new Word("Nurse", "かんごし (kango shi)", R.drawable.nurse, R.raw.kango_shi));
        wordsList.add(new Word("Dentist", "しかい (shikai)", R.drawable.dentist, R.raw.shikai));
        wordsList.add(new Word("Teacher", "きょうし / せんせい (kyoushi / sensei)", R.drawable.teacher, R.raw.sensei));
        wordsList.add(new Word("Scientist", "かがくしゃ (kagaku sha)", R.drawable.scientist, R.raw.kagaku_shaa));
        wordsList.add(new Word("Driver", "うんてんしゅ (Unten shu)", R.drawable.driver, R.raw.unten_shu));
        wordsList.add(new Word("Soccer Player", "サッカーせんしゅ (sakka-senshu)", R.drawable.soccerplayer, R.raw.sakka_senshu));
        wordsList.add(new Word("Photographer", "しゃしんか (shashin ka)", R.drawable.photographer, R.raw.shashin_kaa));
        wordsList.add(new Word("Artist", "げいじゅつか (geijutsu ka)", R.drawable.artist, R.raw.geijutsu_kaa));
        wordsList.add(new Word("Architect", "けんちくか (kenchiku ka)", R.drawable.architect, R.raw.kenchiku_kaa));
        wordsList.add(new Word("Politician", "せいじか (seiji ka)", R.drawable.politician, R.raw.seiji_kaa));
        wordsList.add(new Word("Policeman", "けいさつかん (keisatsu kan)", R.drawable.policeman, R.raw.keisatsu_kan));
        wordsList.add(new Word("Judge", "さいばんかん (saiban kan)", R.drawable.judge, R.raw.saiban_kan));
        wordsList.add(new Word("Lawyer", "べんごし (bengo shi)", R.drawable.lawyer, R.raw.bengo_shi));
        wordsList.add(new Word("Firefighter", "しょうぼうし (shoubou shi)", R.drawable.firefighter, R.raw.shoubou_shi));
        wordsList.add(new Word("Soldier", "へいし (hei shi)", R.drawable.soldier, R.raw.hei_shi));
        wordsList.add(new Word("Actor", "はいゆう (haiyuu)", R.drawable.actor, R.raw.haiyuu));
        wordsList.add(new Word("Actress", "じょゆう (joyuu)", R.drawable.actress, R.raw.joyuu));
        wordsList.add(new Word("Fisherman", "りょうし (ryou shi)", R.drawable.fisherman, R.raw.ryou_shi));
        wordsList.add(new Word("Barber", "りはつし (rihatsu shi)", R.drawable.barber, R.raw.rihatsu_shi));
        wordsList.add(new Word("Engineer", "エンジニア (enjinia)", R.drawable.engineer, R.raw.enjinia));
        wordsList.add(new Word("Professor", "きょうじゅ (kyouju)", R.drawable.professor, R.raw.kyouju));
        wordsList.add(new Word("Carpenter", "だいく (daiku)", R.drawable.carpenter, R.raw.daiku));
        wordsList.add(new Word("Detective", "たんてい (tantei)", R.drawable.detective, R.raw.tantei));
        wordsList.add(new Word("Journalist", "ジャーナリスト (ja-narisuto)", R.drawable.journalist, R.raw.janarisuto));
        wordsList.add(new Word("Farmer", "のうみん (noumin)", R.drawable.farmer, R.raw.noumin));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.occupations_bg_color);
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
                    mMediaPlayer = MediaPlayer.create(CommonoccupationsActivity.this, clickedWord.getAudioResourceId());
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