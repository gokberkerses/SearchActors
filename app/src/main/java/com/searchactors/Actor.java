package com.searchactors;


import com.google.gson.annotations.SerializedName;

public class Actor {

    @SerializedName("profile_path")
    private String profilePath ;
    @SerializedName("name")
    private String name ;
    @SerializedName("popularity")
    private String popularity ;

    public Actor(String profilePath, String name, String popularity) {
        this.profilePath = profilePath;
        this.name = name;
        this.popularity = popularity;
    }

    public Actor() {
        // for dummy actor, to be able to govern load more
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getName() {
        return name;
    }

    public String getPopularity() {
        return popularity;
    }


    //profile_path	:	/2daC5DeXqwkFND0xxutbnSVKN6c.jpg
    //adult	:	false
    //id	:	51329
    //	known_for		[3]
    //name	:	Bradley Cooper
    //popularity	:	6.431053
}
