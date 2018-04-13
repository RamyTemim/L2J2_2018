package com.app.game.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Grid {
    private ArrayList arrayList;

    public Grid() {
        this.arrayList = new ArrayList(  );
    }

    public Grid(ArrayList ar) {
        this.arrayList = ar;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }


}
