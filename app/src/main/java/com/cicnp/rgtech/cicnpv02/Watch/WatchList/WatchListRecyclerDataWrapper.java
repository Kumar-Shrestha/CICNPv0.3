package com.cicnp.rgtech.cicnpv02.Watch.WatchList;

/**
 * Created by Kumar-PC on 7/23/2016.
 */
public class WatchListRecyclerDataWrapper
{
    public String uniqueVariable;
    public String name;
    public String imageUrl;
    public String secondaryInfo;

    public WatchListRecyclerDataWrapper(String name, String imageUrl, String secondaryInfo, String uniqueVariable)
    {
        this.name = name;
        this.imageUrl = imageUrl;
        this.secondaryInfo = secondaryInfo;
        this.uniqueVariable = uniqueVariable;
    }
}