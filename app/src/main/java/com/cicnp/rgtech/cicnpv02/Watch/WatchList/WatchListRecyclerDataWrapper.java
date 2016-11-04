package com.cicnp.rgtech.cicnpv02.Watch.WatchList;

/**
 * Created by Kumar-PC on 7/23/2016.
 */
public class WatchListRecyclerDataWrapper
{
    public String uniqueVariable;
    public String name;
    public int imageID;
    public String secondaryInfo;

    public WatchListRecyclerDataWrapper(String name, int imageID, String secondaryInfo, String uniqueVariable)
    {
        this.name = name;
        this.imageID = imageID;
        this.secondaryInfo = secondaryInfo;
        this.uniqueVariable = uniqueVariable;
    }
}