package ddra.com.musicapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.media.SoundPool;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Quiz extends ActionBarActivity implements SoundPool.OnLoadCompleteListener, QuizOptionsFragment.OnFragmentInteractionListener{

    //TODO: add multiple threads

    Theory chosenTheory[];
    int iType;
    int maxQNum;
    int[] quizSpecs;

    int noteDelay;
    int currentTheoryIndex;
    int[] savedNotes;

    SoundPool sp;
    int spMaxStreamNum = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        loadFragment(2);

        sp = new SoundPool (spMaxStreamNum, AudioManager.STREAM_MUSIC, 0);
        sp.setOnLoadCompleteListener(this);

        iType = getIntent().getIntExtra("iType", 0);

        maxQNum = getIntent().getIntExtra ("qNum", 0);

        getConfigurations();

        generateQuestion();

        loadQuestion();
    }

    // Loads a specific fragment (keyboard or options) based on the id
    public void loadFragment(int id) {

        FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction transaction = fm.beginTransaction();

        if (id == 2) {
            QuizOptionsFragment fragment = QuizOptionsFragment.newInstance("QuizOptionsFragment", this.getLocalClassName());
            transaction.add(R.id.view_group_quiz, fragment).commit();
        }
        else {
            //TODO:Load keyboard fragment here
        }
    }

    public void getConfigurations () {
        //get list of selected theories for quiz
        Parcelable[] p = getIntent().getParcelableArrayExtra("theoryList");
        chosenTheory = new Theory [p.length];
        for (int i = 0; i < p.length; i++){
            chosenTheory[i] = (Theory)p[i];
        }

        //get selected options for quiz
        List<Integer> quizSpecsList = new ArrayList<>();
        switch (iType){
            case 0:
                quizSpecsList.add(getIntent().getIntExtra("direction", 0));
                quizSpecsList.add(getIntent().getIntExtra("type", 0));
                break;
            case 1:
                quizSpecsList.add(getIntent().getIntExtra("direction", 0));
                break;
            case 2:
                quizSpecsList.add(getIntent().getIntExtra("inv", 0));
                quizSpecsList.add (getIntent().getIntExtra("voicing", 0));
                break;
        }
        quizSpecs = new int [quizSpecsList.size()];
        Iterator<Integer> iterator = quizSpecsList.iterator();
        for (int i = 0; i < quizSpecs.length; i++)
            quizSpecs[i] = iterator.next();
    }

    public void generateQuestion(){
        currentTheoryIndex = (int) (Math.random()*chosenTheory.length);
        int direction = 0;
        noteDelay = 0;
        switch (iType){
            case 0: //INTERVALS
                noteDelay = quizSpecs[1];
                if (quizSpecs[0] == 2)
                    direction = (int) (Math.random() * 2);
                else
                    direction = quizSpecs[0];
                break;
            case 1: //SCALES
                noteDelay = 1;
                if (quizSpecs[0] == 2)
                    direction = (int) (Math.random() * 2);
                else
                    direction = quizSpecs[0];
                break;
            case 2:
                //TODO: Chord configurations algorithm
                break;
        }

        int chosenNotes[] = new int[chosenTheory[currentTheoryIndex].step.length + 1];
        if (direction == 0){
            chosenNotes[0] = (int)(Math.random() *   13);
        } else {
            chosenNotes[0] = (int)(Math.random() *   13) + 12;
        }
        if (direction == 0)
            for (int i = 0; i < chosenTheory[currentTheoryIndex].step.length; i++){
                chosenNotes[i + 1] = chosenNotes[i] + chosenTheory[currentTheoryIndex].step[i];
            }
        else
            for (int i = 0; i < chosenTheory[currentTheoryIndex].step.length; i++){
                chosenNotes[i + 1] = chosenNotes[i] - chosenTheory[currentTheoryIndex].step[chosenTheory[currentTheoryIndex].step.length - 1 - i];
            }
        savedNotes = chosenNotes;
    }

    public void loadQuestion(){
        for (int u : savedNotes){
            sp.load (this, DATABASE.noteID[u], 1);
        }
    }

    public void playQuestion(){
        for (int i = 1; i < savedNotes.length + 1; i++) {
            sp.play(i, 1, 1, 1, 1, 1);
            try {
                Thread.sleep(500 * noteDelay);
            } catch (Exception e){
                System.out.println ("ERROR");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLoadComplete(SoundPool soundPool, int soundid, int status) {
        if (soundid == savedNotes.length) {

           new Thread(new Runnable() {
               @Override
                public void run() {
                   playQuestion();
               }
           }).start();

            // new PlaySoundTask().execute(soundPool);

            /* new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!Thread.interrupted()) {
                        final SoundPool sound = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
                        sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                            @Override
                            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                                for (int i = 1; i < savedNotes.length + 1; i++) {
                                    sound.play(i, 1, 1, 1, 1, 1);
                                }
                            }
                        });
                       }
                    }
            }).start(); */

            //TODO: START TIMER
            //TODO: Add to another thread
        }
    }

    @Override
    public void onFragmentInteraction(int id) {
        loadFragment(id);
    }

    /* private class PlaySoundTask extends AsyncTask<String, Void, SoundPool> {

        @Override
        protected SoundPool doInBackground(String... params) {
            return new SoundPool;
        }

        protected void onPostExecute(SoundPool result) {
            for (int i = 1; i < savedNotes.length + 1; i++) {
                result.play(i, 1, 1, 1, 1, 1);
        }
    } */
}
