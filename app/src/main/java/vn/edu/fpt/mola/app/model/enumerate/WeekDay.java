package vn.edu.fpt.mola.app.model.enumerate;

/**
 * Created by phuctran93 on 9/27/2016.
 */

public enum WeekDay {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(4),
    WEDNESDAY(8),
    THURSDAY(16),
    FRIDAY(32),
    SATURDAY(64);

    WeekDay(int id) {
        this.id = id;
    }

    int id;

    public int getValue() {
        return id;
    }
}
