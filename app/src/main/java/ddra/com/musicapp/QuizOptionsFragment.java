package ddra.com.musicapp;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuizOptionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuizOptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizOptionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String FRAG_TYPE = "quiz_fragment";
    private static final String PARENT = "common_quiz_selection_activity";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizOptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizOptionsFragment newInstance(String param1, String param2) {
        QuizOptionsFragment fragment = new QuizOptionsFragment();
        Bundle args = new Bundle();
        args.putString(FRAG_TYPE, param1);
        args.putString(PARENT, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public QuizOptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(FRAG_TYPE);
            mParam2 = getArguments().getString(PARENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz_options, container, false);
        Button mButton = (Button)v.findViewById(R.id.hello_blank_fragment);
        mButton.setOnClickListener((View view) -> {
            onButtonPressed(mButton.getId());
        });
        return v;
    }

    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int id);
    }

}
