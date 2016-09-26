package vn.edu.fpt.mola.app.entity;

import java.io.Serializable;


public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private String title;

    public Role()
    {
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}