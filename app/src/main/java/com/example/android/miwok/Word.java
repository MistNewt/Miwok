package com.example.android.miwok;

/**
 * Created by Sudhanshu on 28-04-2017.
 */

/**
 * This contains the Miwok and English translations
 * of numbers.
 */

public class Word {
    //Miwok translation
    private String mMiwokTranlation;
    //Default translation, say, English
    private String mDefaultTranslation;
    //Media Resource default value
    private final int NO_MEDIA = -1;
    //Image Resource Id
    private int mImageResourceId = NO_MEDIA;
    //Sound resource id
    private int mSoundResourceId = NO_MEDIA;

    public Word(String englishTranslation, String miwokTranslation, int soundResourceId) {
        mMiwokTranlation = miwokTranslation;
        mDefaultTranslation = englishTranslation;
        mSoundResourceId = soundResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int soundResourceId) {
        mMiwokTranlation = miwokTranslation;
        mDefaultTranslation = defaultTranslation;
        mImageResourceId = imageResourceId;
        mSoundResourceId = soundResourceId;
    }

    public String getMiwokTranlation() {
        return mMiwokTranlation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_MEDIA;
    }

    public int getSoundResourceId() {
        return mSoundResourceId;
    }
}
