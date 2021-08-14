package com.example.nihongo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TriviaActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.japan_trivia);

        TextView headerText = (TextView) findViewById(R.id.heading_text);
        headerText.setText("Japan: Land of the Rising Sun");

        TextView paraText = (TextView) findViewById(R.id.paragraph_text);
        paraText.setText("Japan (JPN) (Nippon or Nihon) also known as the \"Land of the Rising Sun\" is an island nation lying off the east coast of the Asian continent. Japan comprises of a total of 4 islands stretching from northeast to southwest direction through a massive distance of about 2400 kms in the western-north Pacific Ocean. The 4 islands comprising Japan\\'s geography are named as: Honshu (Honshū),  Hokkaido (Hokkaidō), Kyushu (Kyūshū), and Shikoku.\n" +
                "The largest of these islands is the island of Honshu, which is followed by the islands of Hokkaido, Kyushu and Shikoku. Presently, the national capital city of Japan is Tokyo. However, the city of Kyoto has been the historical capital of the country from 794 to 1868 AD.\n" +
                "The Japanese landscape is rugged, with more than four-fifths of the land surface consisting of mountains. There are many active and dormant volcanoes, including Mount Fuji (Fuji-san), which, at an elevation of 12,388 feet (3,776 metres), is Japan’s highest mountain.\n" +
                "Japan is also considered to be one of the most technologically advanced and financially stable country among the world\\'s giants. Apart from this, Japan has also been successful in maintaining and nourishing their culture and tradition all the way along in a most careful and respectful manner.\n" +
                "Even in the countryside, however, the impact of rapid Westernization is evident in many aspects of Japanese life. The agricultural regions are characterized by low population densities and well-ordered rice fields and fruit orchards, whereas the industrial and urbanized belt along the Pacific coast of Honshu is noted for its highly concentrated population, heavy industrialization, and environmental pollution.\n" +
                "Talking about the presence and role of Japan among in international affairs, and among various other nations, Japan is a great power and a member of numerous international organizations, including the United Nations (since 1956), the OECD, and the Group of Seven. Although it has renounced its right to declare war, the country maintains Self-Defense Forces that rank as one of the world's strongest militaries. " +
                "After World War II, Japan experienced record growth in an economic miracle, becoming the second-largest economy in the world by 1990. As of 2021, the country's economy is the third-largest by nominal GDP and the fourth-largest by PPP.\n" +
                "The prevelance of religion in Japan is highly significant and forms the core of the Japanese culture and traditions." +
                "Upper estimates suggest that 84–96 percent of the Japanese population subscribe to Shinto as its indigenous religion.However, these estimates are based on people affiliated with a temple, rather than the number of true believers. Many Japanese people practice both Shinto and Buddhism; they can either identify with both religions or describe themselves as non-religious or spiritual.\n" +
                "The level of participation in religious ceremonies as a cultural tradition remains high, especially during festivals and occasions such as the first shrine visit of the New Year.Taoism and Confucianism from China have also influenced Japanese beliefs and customs.\n" +
                "Japanese writing uses kanji (Chinese characters) and two sets of kana (syllables based on cursive script and radical of kanji), as well as the Latin alphabet and Arabic numerals.English instruction was made mandatory in Japanese elementary schools in 2020.\n" +
                "As far as education is concerned, as per the survey conducted by international organizations in 2017, the Japanese population spends 3.3 percent of its annual GDP for the sake of quality education.\n" +
                "Approximately 60 percent of Japanese aged 25 to 34 have some form of tertiary education qualification, and bachelor's degrees are held by 30.4 percent of Japanese aged 25 to 64, the second most in the OECD after South Korea.\n" +
                "There are four seasons in Japan, spring, summer, fall, and winter. Each season has specific sceneries and experiences to offer. For each season, there are many different ways to enjoy places in Japan." +
                "Spring is generally from March to May in Japan. “Sakura” or cherry blossoms are a symbol of spring in Japan. Many people gather under sakura trees for “ohanami,” which means cherry blossom viewing, all over Japan." +
                "Wisteria, moss phlox and many other types of flowers other than sakura also bloom in the spring. These flowers become a tourist attraction but they are also is there as a guided path.\n" +
                "Summer is usually from June to August. Japan is surrounded by the sea, so you can enjoy swimming in various places. You can swim in the sea near Tokyo, but remote the islands of Okinawa are also recommended to enjoy a relaxing time.\n" +
                "Once the rainy season called “tsuyu”, which lasts from June to late July is over, the temperature rapidly increases. This is the season for summer festivals and firework displays happening everywhere in Japan. You can enjoy watching festivals or take part in dancing at other festivals as well.\n" +
                "Moreover if you are planning to visit Japan sometime in the future or like exploring different cultures and customs, then this application is just what you need to learn the basics of the language, and" +
                "know about the famous places to visit and many more stuff that will surely amaze you out of the blue. There is much more about this nation than you can possibly imagine.");
    }

}
