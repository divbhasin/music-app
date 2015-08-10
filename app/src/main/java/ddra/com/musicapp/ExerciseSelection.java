package ddra.com.musicapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ExerciseSelection extends ActionBarActivity {
    public final static String MESSAGE_EXERSEL = "ddra.com.musicapp.MESSAGE_EXERSEL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_selection, menu);
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

    public void goToExerciseOptions (View v) {
        Intent exerOps = new Intent (this, ExerciseOptions.class);
        String typeSelected;
        switch (v.getId()) {
            case R.id.exerSel_btn_intervals:
                typeSelected = "Intervals";
                break;
            case R.id.exerSel_btn_scales:
                typeSelected = "Scales";
                break;
            case R.id.exerSel_btn_chords:
                typeSelected = "Chords";
                break;
            case R.id.exerSel_btn_meloDict:
                typeSelected = "Melodic Dictation";
                break;
            case R.id.exerSel_btn_rhythDict:
                typeSelected = "Rhythmic Dictation";
                break;
            default:
                typeSelected = "ERROR";
        }
        exerOps.putExtra (MESSAGE_EXERSEL, typeSelected);
        startActivity (exerOps);
    }

    public void goToMelodicDictationScreen(View v) {
        Intent go = new Intent(this, MelodicDictation.class);
        startActivity(go);
    }
}
