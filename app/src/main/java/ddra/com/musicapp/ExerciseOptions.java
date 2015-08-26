package ddra.com.musicapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ExerciseOptions extends ActionBarActivity implements OnFragmentInteractionListener {

    int iType;

    Theory theory[];

    SeekBar sb;

    TextView sbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get exercise type number selected
        iType = getIntent().getIntExtra(ExerciseSelection.MESSAGE_EXERSEL, -1);

        //set activity title and labels
        setLabels(iType);

        setFragment (iType, savedInstanceState);

        setupSeekBar();

        theory = createTheory (iType);

        createList(theory);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exercise_options, menu);
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

    //set labels and title
    public void setLabels (int iType){
        String sType;
        //translate type into words
        switch (iType){
            case 0:
                sType = "Intervals";
                break;
            case 1:
                sType = "Scales";
                break;
            case 2:
                sType = "Chords";
                break;
            default:
                sType = "ERROR";
        }

        //set ActionBar title
        setTitle(sType + " Training Options");
        setContentView(R.layout.activity_exercise_options);

        //set selection label
        TextView t = (TextView) findViewById (R.id.exerOps_txt_selectLbl);
        t.setText(sType + " Selection");
    }

    public void setFragment (int iType, Bundle savedInstanceState) {
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout

        if (findViewById(R.id.exerOps_fLay_fragmentHolder) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            FragmentManager fm = getSupportFragmentManager();

            Fragment designated = null;

            switch (iType){
                case 0:
                    designated = new ExerciseOptions_Intervals();
                case 1:
                    Fragment designated2 = new ExerciseOptions_Scales();
                    fm.beginTransaction().add(R.id.exerOps_fLay_fragmentHolder, designated2).commit();
                    break;
                case 2:
                designated = new ExerciseOptions_Chords();
                    break;
            }
            fm.beginTransaction().add(R.id.exerOps_fLay_fragmentHolder, designated).commit();

        }
    }

    public void setupSeekBar () {
        //set up TextView for SeekBar Listener
        sbProgress = (TextView) findViewById(R.id.exerOps_txt_sbProgress);
        sbProgress.setText("inf.");

        //set up seekbar to listener
        sb = (SeekBar)findViewById(R.id.exerOps_sb_qNum);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStartTrackingTouch (SeekBar seekbar) {}
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0)
                    sbProgress.setText ("inf.");
                else
                    sbProgress.setText ("" + i);
            }
        });
    }

    public Theory[] createTheory(int iType){
        int numOf = 0;
        Theory theo[];
        switch (iType) {
            case 0: //intervals
                numOf = DATABASE.intervalName.length;
                int stepArray[] = new int[1];
                theo = new Theory[numOf];
                for (int i = 0; i < numOf; i++) {
                    stepArray[0] = i;
                    theo[i] = new Theory (DATABASE.intervalName[i], DATABASE.intervalAbbre[i], stepArray);
                } break;
            case 1: //scales
                numOf = DATABASE.scaleName.length;
                theo = new Theory[numOf];
                for (int i = 0; i < numOf; i++) {
                    theo[i] = new Theory (DATABASE.scaleName[i], null, DATABASE.scaleSteps[i]);
                } break;
            case 2: //chords
                numOf = DATABASE.chordName.length;
                theo = new Theory[numOf];
                for (int i = 0; i < numOf; i++) {
                    theo[i] = new Theory (DATABASE.chordName[i], DATABASE.chordAbbre[i], DATABASE.chordSteps[i]);
                } break;
            default: //ERROR
                theo = new Theory[0];
        }
        return theo;
    }

    public void createList (Theory type[]) {
        LinearLayout listbox = (LinearLayout) findViewById(R.id.exerOps_lLay_list);
        int numOf = type.length;
        if (type[0].abbre != null) {
            for (int i = 0; i < numOf; i++) {
                CheckBox listSelection = new CheckBox(this);
                listSelection.setText(type[i].name + " (" + type[i].abbre + ")");
                listSelection.setId(i);
                listbox.addView(listSelection);
            }
        } else {
            for (int i = 0; i < numOf; i++) {
                CheckBox listSelection = new CheckBox(this);
                listSelection.setText(type[i].name);
                listSelection.setId(i);
                listbox.addView(listSelection);
            }
        }
    }

    public void selectAll (View v) {
        int counter = 0;
        for (int i = 0; i < theory.length; i++){
            CheckBox cb = (CheckBox) findViewById(i);
            if (cb.isChecked())
                counter++;
        }

        if (counter < theory.length){
            for (int i = 0; i < theory.length; i++){
                CheckBox cb = (CheckBox)findViewById(i);
                cb.setChecked(true);
            }
        } else {
            for (int i = 0; i < theory.length; i++){
                CheckBox cb = (CheckBox)findViewById(i);
                cb.setChecked(false);
            }
        }
    }

    public boolean validateOptions () {
        boolean isOneChecked = false;
        for (int i = 0; i < theory.length && !isOneChecked; i++){
            CheckBox cb = (CheckBox) findViewById(i);
            if (cb.isChecked())
                isOneChecked = true;
        }
        if (!isOneChecked){
            TextView lbl = (TextView)findViewById(R.id.exerOps_txt_selectLbl);
            lbl.setTextColor(Color.RED);
        } else {
            TextView lbl = (TextView)findViewById(R.id.exerOps_txt_selectLbl);
            lbl.setTextColor(getResources().getColor(android.R.color.secondary_text_light));
        }
        return isOneChecked;
    }

    public void goToExercise (View v){
        if (validateOptions()) {
            Intent startExer = new Intent (this, Quiz.class);
            //selected options passed to Quiz activity

            //pass quiz type
            startExer.putExtra ("iType", iType);

            //pass number of questions
            startExer.putExtra ("qNum", sb.getProgress());

            //pass list of chosen intervals/scales/chords
            List <Theory> passedTheory = new ArrayList<Theory>();
            for (int i = 0; i < theory.length; i++ ){
                CheckBox cb = (CheckBox) findViewById(i);
                if (cb.isChecked())
                    passedTheory.add (theory[i]);
            }
            Theory passedTheoryFinal[] = new Theory [passedTheory.size()];
            passedTheoryFinal = passedTheory.toArray(passedTheoryFinal);
            startExer.putExtra ("theoryList", passedTheoryFinal);

            //pass different options depending on quiz type selected
            switch (iType){
                case 0:
                    startExer.putExtra ("type", determineType());
                case 1:
                    startExer.putExtra ("direction", determineDirection ());
                    break;
                case 2:
                    startExer.putExtra ("inv", determineInversions());
                    startExer.putExtra ("voicing", determineVoicing());
                    break;
            }
            startActivity (startExer);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(ExerciseOptions.this).create();
            alertDialog.setTitle("Settings Invalid");
            alertDialog.setMessage("At least one option under each category must be selected!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    //harmonic = 0, melodic = 1
    public int determineType () {
        RadioButton rb;
        rb = (RadioButton) findViewById(R.id.intervalOps_rb_melo);
        if (rb.isChecked())
            return 0;
        return 1;
    }

    //ascending = 0, descending = 1, any = 2
    public int determineDirection (){
            RadioButton rb;
            rb = (RadioButton) findViewById(R.id.intervalOps_rb_ascend);
            if (rb.isChecked())
                return 0;
            rb = (RadioButton) findViewById(R.id.intervalOps_rb_descend);
            if (rb.isChecked())
                return 1;
        return 2;
    }

    //inversion = 0, no inversions = 1
    public int determineInversions (){
        CheckBox cb;
        cb = (CheckBox) findViewById(R.id.chordOps_cb_inv);
        if (cb.isChecked())
            return 0;
        return 1;
    }

    //closed = 0, open = 1, any = 2
    public int determineVoicing (){
        RadioButton rb;
        rb = (RadioButton) findViewById(R.id.chordOps_rb_cd);
        if (rb.isChecked())
            return 0;
        rb = (RadioButton) findViewById(R.id.chordOps_rb_op);
        if (rb.isChecked())
            return 1;
        return 2;
    }

    public void onFragmentMessage (String TAG, Object data){
    }

}
