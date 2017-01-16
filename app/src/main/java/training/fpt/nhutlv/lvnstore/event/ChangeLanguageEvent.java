package training.fpt.nhutlv.lvnstore.event;

/**
 * Created by NhutDu on 16/01/2017.
 */

public class ChangeLanguageEvent {

    private boolean misChange;
    private int mLanguage;
    private String mName;

    public boolean isMisChange() {
        return misChange;
    }

    public void setMisChange(boolean misChange) {
        this.misChange = misChange;
    }

    public int getLanguage() {
        return mLanguage;
    }

    public void setLanguage(int language) {
        mLanguage = language;
    }

    public String getName() {
        return mName;
    }

    public ChangeLanguageEvent(boolean misChange, int language, String name) {
        this.misChange = misChange;
        mLanguage = language;
        mName = name;
    }

    public ChangeLanguageEvent(int language, boolean misChange) {
        mLanguage = language;
        this.misChange = misChange;
    }

    public void setName(String name) {
        mName = name;
    }

    public ChangeLanguageEvent() {
    }
}
