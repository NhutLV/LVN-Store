package training.fpt.nhutlv.lvnstore.entities;

import io.realm.RealmObject;

/**
 * Created by NhutDu on 31/12/2016.
 */

public class RealmString extends RealmObject {
    public String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RealmString(String value) {
        this.value = value;
    }

    public RealmString() {
    }

    @Override
    public String toString() {
        return value;
    }
}
