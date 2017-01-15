package training.fpt.nhutlv.lvnstore.entities;

import java.util.Arrays;

import io.realm.RealmObject;

/**
 * Created by NhutDu on 14/01/2017.
 */

public class RealmArrayByte extends RealmObject{
    byte [] byteImage;

    public byte[] getByteImage() {
        return byteImage;
    }

    public void setByteImage(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public RealmArrayByte(byte[] byteImage) {
        this.byteImage = byteImage;
    }

    public RealmArrayByte() {
    }

    @Override
    public String toString() {
        return Arrays.toString(byteImage);
    }
}
