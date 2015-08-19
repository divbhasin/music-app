package ddra.com.musicapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MelodicDictation extends ActionBarActivity {

    int mNumberOfNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Melodic Dictation Options");
        setContentView(R.layout.activity_melodic_dictation);

        SeekBar exercises = (SeekBar) findViewById(R.id.seek_bar);
        final TextView number = (TextView) findViewById(R.id.seek_bar_number);

        NumberPicker number_of_notes = (NumberPicker) findViewById(R.id.number_picker);
        initNumberPicker(number_of_notes);


        ScrollView view = (ScrollView) findViewById(R.id.scroll_view);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);

        initNotes(layout, view);

        exercises.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                number.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initNotes(LinearLayout l, ScrollView v) {
        DATABASE db = new DATABASE();
        for (int i = 0; i < db.intervalName.length; i++) {
            CheckBox cb = new CheckBox(this);
            cb.setText(db.intervalName[i] + " (" + db.intervalAbbre[i] + ") ");
            cb.setId(i);
            l.addView(cb);
        }
    }

    public void initNumberPicker(NumberPicker r) {
        r.setMaxValue(10);
        r.setMinValue(3);

        r.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int newVal, int oldVal) {
                mNumberOfNotes = newVal;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_melodic_dictation, menu);
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
