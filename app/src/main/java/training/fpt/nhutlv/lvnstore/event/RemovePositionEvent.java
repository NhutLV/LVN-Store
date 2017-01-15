package training.fpt.nhutlv.lvnstore.event;

import training.fpt.nhutlv.lvnstore.entities.AppInfo;

/**
 * Created by NhutDu on 31/12/2016.
 */

public class RemovePositionEvent {

    private int position;
    private boolean check;
    private int tab;

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

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public RemovePositionEvent(AppInfo appInfo, boolean check, int tab) {
        this.appInfo = appInfo;
        this.check = check;
        this.tab = tab;
    }

    public RemovePositionEvent(int position, boolean check, AppInfo appInfo) {
        this.position = position;
        this.check = check;
        this.appInfo = appInfo;
    }

    public RemovePositionEvent(int position, boolean check, int tab, AppInfo appInfo) {
        this.position = position;
        this.check = check;
        this.tab = tab;
        this.appInfo = appInfo;
    }
}
