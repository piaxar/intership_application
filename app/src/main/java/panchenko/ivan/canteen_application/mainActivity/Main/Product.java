package panchenko.ivan.canteen_application.mainActivity.Main;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by piaxar on 16.06.16.
 */
public class Product implements Parcelable {
    public static final Parcelable.Creator<Product> CREATOR
            = new Parcelable.Creator<Product>() {
        public Product createFromParcel(Parcel in) {
            Product product = new Product(in.readString());
            product.setId(in.readInt());
            product.setPrice(in.readInt());
            return product;
        }

        @Override
        public Product[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Product[size];
        }
    };
    private String name;
    private int price;
    private int id;

    public Product(String name) {
        this.name = name;
        Random random = new Random();
        price = random.nextInt(200);
        id = random.nextInt(10000);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(id);
        dest.writeInt(price);


    }
}