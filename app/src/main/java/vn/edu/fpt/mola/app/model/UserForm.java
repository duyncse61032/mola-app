package vn.edu.fpt.mola.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.edu.fpt.mola.app.model.enumerate.Gender;


public class UserForm
{
    private String username;
    private String password;
    private String title;
    private String firstName;
    private String lastName;
    private String nameSuffix;
    private String displayName;
    private Gender gender;
    private Date birthday;
    private List<Language> learningLanguageList;
    private List<Language> teachingLanguageList;
    private List<Role> roleList;
    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getNameSuffix()
    {
        return nameSuffix;
    }

    public void setNameSuffix(String nameSuffix)
    {
        this.nameSuffix = nameSuffix;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public void setDisplayName(String displayName)
    {
        this.displayName = displayName;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public List<Language> getLearningLanguageList()
    {
        return learningLanguageList;
    }

    public void setLearningLanguageList(List<Language> learningLanguageList)
    {
        this.learningLanguageList = learningLanguageList;
    }

    public void addLearningLanguage(Language language) {
        if (this.learningLanguageList == null) {
            learningLanguageList = new ArrayList<>();
        }
        learningLanguageList.add(language);
    }

    public List<Language> getTeachingLanguageList()
    {
        return teachingLanguageList;
    }

    public void setTeachingLanguageList(List<Language> teachingLanguageList)
    {
        this.teachingLanguageList = teachingLanguageList;
    }

    public void addTeachingLanguageList(Language language) {
        if (this.teachingLanguageList == null) {
            teachingLanguageList = new ArrayList<>();
        }
        teachingLanguageList.add(language);
    }

    public List<Role> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    public void addRole(Role role) {
        if (this.roleList == null) {
            roleList = new ArrayList<>();
        }
        roleList.add(role);
    }
}
