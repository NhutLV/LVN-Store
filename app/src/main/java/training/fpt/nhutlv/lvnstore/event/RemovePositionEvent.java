package training.fpt.nhutlv.lvnstore.event;

import training.fpt.nhutlv.lvnstore.entities.AppInfo;

/**
 * Created by NhutDu on 31/12/2016.
 */

public class RemovePositionEvent {

    private int position;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    private AppInfo appInfo;

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public RemovePositionEvent(int position,boolean check) {
        this.position = position;
        this.check = check;
    }

    public RemovePositionEvent(int position, boolean check, AppInfo appInfo) {
        this.position = position;
        this.check = check;
        this.appInfo = appInfo;
    }
}
