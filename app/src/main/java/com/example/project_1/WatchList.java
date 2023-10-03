package com.example.project_1;

import java.util.LinkedList;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WatchList extends LinkedList<Watch> {
    @Inject
    WatchList(){
        // default constructor
    }




} // end WatchList