package net.ivanvega.myroomwordsample;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.ivanvega.myroomwordsample.repository.WordRepository;
import net.ivanvega.myroomwordsample.roomdata.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

   private WordRepository mRepository;

   private final LiveData<List<Word>> mAllWords;

   public WordViewModel (Application application) {
       super(application);
       mRepository = new WordRepository(application);
       mAllWords = mRepository.getAllWords();
   }

   LiveData<List<Word>> getAllWords() { return mAllWords; }

   public void insert(Word word) {
       mRepository.insert(word); }
}