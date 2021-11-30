package net.ivanvega.myroomwordsample;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.ivanvega.myroomwordsample.roomdata.Word;
import net.ivanvega.myroomwordsample.roomdata.WordListAdapter;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    ActivityResultLauncher<Intent> lancher
            = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode()  == RESULT_OK) {
                    Word word = new Word (
                            result.getData().getStringExtra(NewWordActivity.EXTRA_REPLY));
                    mWordViewModel.insert(word);
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final WordListAdapter adapter =
                new WordListAdapter(new WordListAdapter.WordDiff(),
                        view -> {
                            String wordClicked = ((Word) view.getTag()).getWord();
                            Log.d("CLICKPALABRA",  wordClicked);

                            Toast.makeText(MainActivity.this,
                                     wordClicked,
                                    Toast.LENGTH_SHORT).show();
                        });

        recyclerView.setAdapter(adapter);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mWordViewModel.getAllWords().observe(this, words -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(words);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
            lancher.launch(intent);
        });
    }
}