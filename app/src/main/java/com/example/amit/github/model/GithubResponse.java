
package com.example.amit.github.model;


public class GithubResponse {

    public Integer id;
    public String name;
    public String full_name;
    public Owner owner;
    public Boolean _private;
    public String htmlUrl;
    public String description;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public Owner getOwner() {
        return owner;
    }

    public Boolean get_private() {
        return _private;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getDescription() {
        return description;
    }
}
