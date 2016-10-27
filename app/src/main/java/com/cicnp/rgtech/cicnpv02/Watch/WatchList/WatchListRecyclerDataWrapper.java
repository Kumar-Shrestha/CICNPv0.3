package com.cicnp.rgtech.cicnpv02.Watch.WatchList;

/**
 * Created by Kumar-PC on 7/23/2016.
 */
public class WatchListRecyclerDataWrapper
{
    public String name;
    public int imageID;
    public String priority;

    public WatchListRecyclerDataWrapper(String name, int imageID, String priority)
    {
        this.name = name;
        this.imageID = imageID;
        this.priority = priority;
    }
}