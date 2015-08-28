package ddra.com.musicapp;

import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends ActionBarActivity {
    Theory chosenTheory[];
    int iType;
    int maxQNum;
    List<Integer> quizSpecs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        iType = getIntent().getIntExtra("iType", 0);

        maxQNum = getIntent().getIntExtra ("qNum", 0);

        Parcelable[] p = getIntent().getParcelableArrayExtra("theoryList");
        chosenTheory = new Theory [p.length];
        for (int i = 0; i < p.length; i++){
            chosenTheory[i] = (Theory)p[i];
        }

        quizSpecs = new ArrayList<>();
        switch (iType){
            case 0:
                quizSpecs.add(getIntent().getIntExtra("type", 0));
            case 1:
                quizSpecs.add(getIntent().getIntExtra("direction", 0));
                break;
            case 2:
                quizSpecs.add(getIntent().getIntExtra("inv", 0));
                quizSpecs.add (getIntent().getIntExtra("voicing", 0));
                break;
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
}
