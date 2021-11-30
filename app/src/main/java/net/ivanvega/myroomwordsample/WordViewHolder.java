package net.ivanvega.myroomwordsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.ivanvega.myroomwordsample.roomdata.Word;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private WordViewHolder(View itemView,
                           View.OnClickListener onItemClickListener) {
        super(itemView);
        wordItemView = itemView.findViewById(R.id.textView);
        wordItemView.setOnClickListener(onItemClickListener);
    }

    public void bind(String text, Word current) {
        wordItemView.setText(text);
        wordItemView.setTag(current);
    }

    public static WordViewHolder create(ViewGroup parent, AdapterView.OnClickListener onItemClickListener) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent,
                        false);

        return new WordViewHolder(view, onItemClickListener);
    }
}