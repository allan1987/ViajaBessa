package br.com.mnidersoft.viajabessa.model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.LinkedHashMap;

@Table(name = "TravelPackage")
public class TravelPackage extends Model implements Parcelable {

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_DESCRIPTION = "description";

    @Column(name = COLUMN_TITLE)
    public String title;

    @Column(name = COLUMN_PRICE)
    public Integer price;

    @Column(name = COLUMN_PHOTO)
    public String photo;

    @Column(name = COLUMN_DESCRIPTION)
    public String description;

    public TravelPackage() {
        super();
    }

    public TravelPackage(LinkedHashMap linkedHashMap) {
        super();
        this.title = linkedHashMap.get(COLUMN_TITLE).toString();
        this.price = Integer.valueOf(linkedHashMap.get(COLUMN_PRICE).toString());
        this.photo = linkedHashMap.get(COLUMN_PHOTO).toString();
        this.description = linkedHashMap.get(COLUMN_DESCRIPTION).toString();
    }

    public TravelPackage(Parcel in) {
        this.title = in.readString();
        this.price = in.readInt();
        this.photo = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeInt(this.price);
        dest.writeString(this.photo);
        dest.writeString(this.description);
    }

    public static final Parcelable.Creator<TravelPackage> CREATOR = new Parcelable.Creator<TravelPackage>() {
        public TravelPackage createFromParcel(Parcel source) {
            return new TravelPackage(source);
        }

        public TravelPackage[] newArray(int size) {
            return new TravelPackage[size];
        }
    };
}