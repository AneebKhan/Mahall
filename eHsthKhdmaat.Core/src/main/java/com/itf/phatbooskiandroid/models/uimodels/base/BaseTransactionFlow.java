
package com.itf.phatbooskiandroid.models.uimodels.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.itf.phatbooskiandroid.models.uimodels.common.Item;
import com.itf.phatbooskiandroid.models.uimodels.common.Result;

public class BaseTransactionFlow implements Parcelable
{

    @SerializedName("Item")
    @Expose
    private Item item;
    @SerializedName("Result")
    @Expose
    private Result result;

    public final static Creator<BaseTransactionFlow> CREATOR = new Creator<BaseTransactionFlow>() {
        @SuppressWarnings({
                "unchecked"
        })
        public BaseTransactionFlow createFromParcel(Parcel in) {
            return new BaseTransactionFlow(in);
        }

        public BaseTransactionFlow[] newArray(int size) {
            return (new BaseTransactionFlow[size]);
        }
    };

    protected BaseTransactionFlow(Parcel in) {
        this.item = ((Item) in.readValue((Item.class.getClassLoader())));
        this.result = ((Result) in.readValue((Result.class.getClassLoader())));
    }

    public BaseTransactionFlow() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void writeToParcel(Parcel dest, int flags) {
        //TODO: getting error "unable to marshal value com.itf.phatbooski.models.uimodels.base"
       /* dest.writeValue(item);
        dest.writeValue(result);*/
    }

    public int describeContents() {
        return 0;
    }
}
