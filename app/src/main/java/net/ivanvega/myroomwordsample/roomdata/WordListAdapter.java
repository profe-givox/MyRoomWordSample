package net.ivanvega.myroomwordsample.roomdata;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import net.ivanvega.myroomwordsample.WordViewHolder;

public class WordListAdapter extends ListAdapter<Word, WordViewHolder> {
    private AdapterView.OnClickListener onItemClickListener;


     public void setOnItemClickListerner
             (AdapterView.OnClickListener onClickListener){
         this.onItemClickListener = onClickListener;

     }

    public WordListAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback,
                           View.OnClickListener onItemClickListener ) {
        super(diffCallback);
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WordViewHolder.create(parent, this.onItemClickListener);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word current = getItem(position);
        holder.bind(current.getWord(), current);
    }

     public  static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem, @NonNull Word newItem) {
            return oldItem.getWord().equals(newItem.getWord());
        }
    }
}