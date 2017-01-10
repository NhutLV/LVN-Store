package training.fpt.nhutlv.lvnstore.event;

/**
 * Created by NhutDu on 09/01/2017.
 */

public class SettingEvent {

    private String mType;
    private String mContent;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public SettingEvent(String type, String content) {
        mType = type;
        mContent = content;
    }
}
