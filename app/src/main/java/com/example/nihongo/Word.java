package com.example.nihongo;

public class Word {

    // set to -1 as this value is beyond the scope of any valid image resource id

    private static final int NO_IMAGE_PROVIDED = -1;

    // Variable for english translation of the word

    private String mEnglishTranslation;

    // Variable for the japanese translation of the same word

    private String mJapaneseTranslation;

    // Variable for getting the image resource id (int data type)

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    //Variable to store the resource id of audio files

    private int mAudioResourceId;

    // Constructor for initializing the values,and used for creating a word object

    public Word (String englishTranslation, String japaneseTranslation, int audioResourceId) {
        mEnglishTranslation = englishTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mImageResourceId = audioResourceId;
    }

    //Additional constructor with an additional image resource id variable

    public Word (String englishTranslation, String japaneseTranslation, int imageResourceId, int audioResourceId) {
        mEnglishTranslation = englishTranslation;
        mJapaneseTranslation = japaneseTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    // getter method for getting the english translation of the word
    public String getEnglishTranslation () {
        return mEnglishTranslation;
    }

    // getter method for getting the japanese translation of the word
    public String getJapaneseTranslation () {
        return mJapaneseTranslation;
    }

    // getter method for getting the image resource id
    public int getImageResourceId () {
        return mImageResourceId;
    }

    //getter method for getting accessing the resource id for the audio files
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    // method to check whether an image is present in the ImageView
    // returns true if image is present in ImageView otherwise false

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
