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

public class TouristspotsActivity extends AppCompatActivity {

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

        wordsList.add(new Word("Himeji Castle", "姫路城 (Himejijō)", R.drawable.himeji_castle, R.raw.himejijo));
        wordsList.add(new Word("Hiroshima Peace Memorial", "広島平和記念館 (Hiroshima heiwa kinen-kan)", R.drawable.hiroshima_memorial, R.raw.hiroshima_heiwa_kinen_kan));
        wordsList.add(new Word("Todaiji Temple", "東大寺 (Tōdaiji)", R.drawable.todaiji_temple, R.raw.todaiji));
        wordsList.add(new Word("Great Buddha of Kamakura", "鎌倉大仏 (Kamakura daibutsu)", R.drawable.buddha_kamakura, R.raw.kamakura_daibutsu));
        wordsList.add(new Word("Kiyomizu-dera", "清水寺 (Kiyomizudera)", R.drawable.kiyomizudera, R.raw.kiyomizudera));
        wordsList.add(new Word("Jigokudani Monkey Park", "地獄谷野猿公邸 (Jigokudani yaen kōtei)", R.drawable.jigokudani, R.raw.jigokudani_yaen_kotei));
        wordsList.add(new Word("Tokyo Tower", "東京タワー (Tokyo Tawa)", R.drawable.tokyo_tower, R.raw.tokyo_tawa));
        wordsList.add(new Word("Mount Fuji", "富士山 (Fujisan)", R.drawable.fujisan, R.raw.fujisan));
        wordsList.add(new Word("Tokyo Disneyland", "東京ディズニーランド (Tōkyō dizunīrando)", R.drawable.disneyland_tokyo, R.raw.tokyo_dizunirando));
        wordsList.add(new Word("Tokyo Skytree", "東京スカイツリー (Tōkyō sukaitsurī)", R.drawable.tokyo_skytree, R.raw.tokyo_sukaitsree));
        wordsList.add(new Word("Lake Ashinoko", "芦ノ湖 (Ashinoko)", R.drawable.lake_ashinoko, R.raw.ashinoko));
        wordsList.add(new Word("Lake Kawaguchi", "河口湖 (Kawaguchiko)", R.drawable.lake_kawaguchi, R.raw.kawaguchi));
        wordsList.add(new Word("Shikisai no Oka", "四季彩の丘 (Shikisai no Oka)", R.drawable.shikisai_no_oka, R.raw.shikisai_no_oka));
        wordsList.add(new Word("Fushimi Inari Taisha Shrine", "伏見稲荷大社 (Fushimiinaritaisha)", R.drawable.fushimi_inari_shrine, R.raw.fushimiinari));
        wordsList.add(new Word("Inasayama Park", "稲佐山公園 (Inasayama kōen)", R.drawable.inasayama_park, R.raw.inasayama_park));
        wordsList.add(new Word("Osaka Castle", "大阪城 (Ōsakajō)", R.drawable.osaka_castle, R.raw.osaka_castle));
        wordsList.add(new Word("Inokashira Park", "井の頭公園 (Inogashirakōen)", R.drawable.inokashira_park, R.raw.inokashira_park));
        wordsList.add(new Word("Ghibli Museum", "ジブリ美術館 (Jiburi bijutsukan)", R.drawable.placeholder_img, R.raw.ghibli_museum));
        wordsList.add(new Word("Tokyo National Museum", "東京国立博物館 (Tōkyōkokuritsuhakubutsukan)", R.drawable.tokyo_museum, R.raw.tokyo_museum));
        wordsList.add(new Word("Akihabara", "秋葉原 (Akihabara)", R.drawable.akihabara, R.raw.akihabara));
        wordsList.add(new Word("Roppongi", "六本木 (Roppongi)", R.drawable.roppongi, R.raw.roppongi));
        wordsList.add(new Word("Asakusa", "浅草 (Asakusa)", R.drawable.asakusa, R.raw.asakusa));
        wordsList.add(new Word("Tsukiji Hongan Ji", "築地本願寺 (Tsukiji honganji)", R.drawable.tsukiji_hongan_ji, R.raw.tsukiji_honganji));
        wordsList.add(new Word("Dotonbori", "道頓堀 (Dōtonbori)", R.drawable.dotonbori, R.raw.dotonbori));
        wordsList.add(new Word("Hozenji", "法前寺 (Hō zen tera)", R.drawable.hozenji, R.raw.hozenji));
        wordsList.add(new Word("Namba Yasaka Shrine", "難波八阪神社 (Nanbayasakajinja)", R.drawable.namba_yasaka_shrine, R.raw.namba_yasaka_shrine));
        wordsList.add(new Word("Tsutenkaku Tower", "通天閣 (Tsūtenkaku)", R.drawable.tsutenkaku_tower, R.raw.tsutenkaku_tower));
        wordsList.add(new Word("Abeno Harukas", "あべのハルカス (Abeno harukasu)", R.drawable.abeno_harukas, R.raw.abeno_harukas));
        wordsList.add(new Word("Nakanoshima Park", "中之島公園 (Nakanoshima kōen)", R.drawable.nakanoshima_park, R.raw.nakanoshima_park));
        wordsList.add(new Word("Umeda Sky Building", "梅田スカイビル (Umeda sukaibiru)", R.drawable.umeda_sky_building, R.raw.umeda_skybuilding));
        wordsList.add(new Word("Fukuoka Castle", "福岡城 (Fukuoka shiro)", R.drawable.fukuoka_castle, R.raw.fukuoka_castle));
        wordsList.add(new Word("Sumiyoshi-jinja Shrine", "筑前神社 (Chikuzen jinja)", R.drawable.sumiyoshi, R.raw.sumiyoshi_shrine));
        wordsList.add(new Word("Kyoto Imperial Palace", "京都御所 (Kyōto Gose)", R.drawable.kyoto_imperial_palace, R.raw.kyoto_palace));
        wordsList.add(new Word("Kyoto Railway Museum", "京都鉄道博物館 (Kyōto tetsudō hakubutsukan)", R.drawable.kyoto_railway_museum, R.raw.kyoto_railway_museum));
        wordsList.add(new Word("Nara Park", "奈良公園 (Narakōen)", R.drawable.nara_park, R.raw.nara_park));
        wordsList.add(new Word("Shin-Yakushi-Ji", "新薬師寺 (Shin'yakushiji)", R.drawable.shin_yakushiji, R.raw.shin_yakushiji));
        wordsList.add(new Word("Taketomi Island", "竹富島 (Taketomijima)", R.drawable.taketomi_island, R.raw.taketomi_island));
        wordsList.add(new Word("Island Shrine of Itsukushima", "厳島神社 (Itsukushimajinsha)", R.drawable.itsukushima_shrine, R.raw.itsukushima_island));
        wordsList.add(new Word("Atsuta Shrine", "熱田神宮 (Atsuta Jingū)", R.drawable.atsuta_shrine, R.raw.atsuta_shrine));
        wordsList.add(new Word("Ninja Museum of Igaryu", "伊賀流忍者博物館 (Iga ryūninjahakubutsukan)", R.drawable.igaryu_ninja_museum, R.raw.igaryu_ninja_museum));
        wordsList.add(new Word("Chureito Pagoda", "忠霊塔 (Chūreitō)", R.drawable.chureito_pagoda, R.raw.chureito_pagoda));
        wordsList.add(new Word("Fujiyoda Sengen Shrine", "藤代田千現神社 (Fuji Daita Sengen jinja)", R.drawable.fujiyoda_shrine, R.raw.fujiyoda));
        wordsList.add(new Word("Asahiyama Zoo", "旭川旭山動物園 (Asahikawa asahiyamadōbutsuen)", R.drawable.asahiyama_zoo, R.raw.asahiyama_zoo));
        wordsList.add(new Word("Matsumoto Castle", "松本城 (Matsumoto shiro)", R.drawable.matsumoto_castle, R.raw.matsumoto_castle));


        WordAdapter customWordAdapter = new WordAdapter(this, wordsList, R.color.touristspots_bg_color);
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
                    mMediaPlayer = MediaPlayer.create(TouristspotsActivity.this, clickedWord.getAudioResourceId());
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