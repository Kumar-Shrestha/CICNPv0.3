package com.cicnp.rgtech.cicnpv02.SearchBlacklist.SearchResult;

/**
 * Created by Kumar-PC on 7/23/2016.
 */
public class SearchResultRecyclerDataWrapper
{
    public String name;
    public String imageUrl;
    public String citizenshipNumber;

    public SearchResultRecyclerDataWrapper(String name, String imageUrl, String citizenshipNumber)
    {
        this.name = name;
        this.imageUrl = imageUrl;
        this.citizenshipNumber = citizenshipNumber;
    }
}