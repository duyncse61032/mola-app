package vn.edu.fpt.mola.app.entity;

import java.util.ArrayList;
import java.util.List;


public class Wrapper<T> {

    private List<T> data;

    public Wrapper() {
        data = new ArrayList<T>();
    }

    public Wrapper(List<T> items) {
        this.data = items;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}