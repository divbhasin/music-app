package ddra.com.musicapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *Object class holding all specs from DATABASE relevant to options selected by user
 */
public class Theory implements Parcelable {

    String name;
    String abbre;
    int step[];

    public Theory (String name, String abbre, int step[]){
        this.name = name;
        this.abbre = abbre;
        this.step = step;
    }

    public Theory (Parcel in){
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags){
        dest.writeString (name);
        dest.writeString (abbre);
        dest.writeIntArray (step);
    }

    public void readFromParcel (Parcel in){
        name = in.readString ();
        abbre = in.readString();
        step = in.createIntArray();
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Theory createFromParcel(Parcel in) {
                    return new Theory(in);
                }

                public Theory[] newArray(int size) {
                    return new Theory[size];
                }
            };

}
