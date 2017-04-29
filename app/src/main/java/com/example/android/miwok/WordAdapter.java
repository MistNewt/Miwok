package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.example.android.miwok.R.mipmap.ic_launcher;

/**
 * Created by Sudhanshu on 28-04-2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColor;
    private MediaPlayer mMediaPlayer;

    private void releaseMediaResource() {
        if(mMediaPlayer!=null){
            // Release media player resources
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    //On completion listner
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaResource();
        }
    };

    public WordAdapter(Activity context, ArrayList<Word> word,int backgroundColor) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context,0,word);
        mBackgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final Word currentWord = getItem(position);
        // Get the miwok text TextView
        TextView miwokTranslation = (TextView) listItemView.findViewById(R.id.miwok_text);
        //Get the default text TextView
        TextView defaultTranslation = (TextView) listItemView.findViewById(R.id.default_text);
        // Reusing TextView for Miwok translation
        miwokTranslation.setText(currentWord.getMiwokTranlation());
        // Reusing TextView for Default translation
        defaultTranslation.setText(currentWord.getDefaultTranslation());

        //ImageView
        ImageView image = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            image.setImageResource(currentWord.getImageResourceId());
        }
        else {
            image.setVisibility(View.GONE);
        }
        //Setting background color
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mBackgroundColor);
        textContainer.setBackgroundColor(color);

        //Setting sound resources
        textContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Release previously allocated media resource
                releaseMediaResource();
                //Init new media resource
                mMediaPlayer = MediaPlayer.create(getContext(),currentWord.getSoundResourceId());
                mMediaPlayer.start();
                //Releasing resourece on completion
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        return listItemView;
    }
}
