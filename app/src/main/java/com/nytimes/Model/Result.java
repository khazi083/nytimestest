package com.nytimes.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Result implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
    private String url;
    private String countType;
    private String column;
    private String section;
    private String byline;
    private String title;
    private String _abstract;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    private String source;

    private String geoFacet;
    private List<Medium> media = null;

    public Result(Parcel in) {
        this.url = in.readString();
        this.countType = in.readString();
        this.column = in.readString();
        this.section = in.readString();
        this.byline = in.readString();
        this.title = in.readString();
        this._abstract = in.readString();
        this.publishedDate = in.readString();
        this.source = in.readString();
        this.geoFacet = in.readString();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCountType() {
        return countType;
    }

    public void setCountType(String countType) {
        this.countType = countType;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstract() {
        return _abstract;
    }

    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(String geoFacet) {
        this.geoFacet = geoFacet;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.countType);
        dest.writeString(this.column);
        dest.writeString(this.section);
        dest.writeString(this.byline);
        dest.writeString(this.title);
        dest.writeString(this._abstract);
        dest.writeString(this.publishedDate);
        dest.writeString(this.source);
        dest.writeString(this.geoFacet);
    }
}
