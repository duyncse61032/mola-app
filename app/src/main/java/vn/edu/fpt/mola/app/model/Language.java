package vn.edu.fpt.mola.app.model;

import java.io.Serializable;

public class Language implements Serializable
{
    private static final long serialVersionUID = 1L;
    private long id;
    private String englishName;
    private String nativeName;

    public Language()
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

    public String getEnglishName()
    {
        return this.englishName;
    }

    public void setEnglishName(String englishName)
    {
        this.englishName = englishName;
    }

    public String getNativeName()
    {
        return this.nativeName;
    }

    public void setNativeName(String nativeName)
    {
        this.nativeName = nativeName;
    }
}