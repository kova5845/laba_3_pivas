/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexey
 */
public class GraficData {
    List<Function> data = new ArrayList<>();

    public List<Function> getData() {
        return data;
    }

    public void setData(List<Function> data) {
        this.data = data;
    }
    
    public void addPoint(Function point){
        data.add(point);
    }
    
    public void clear(){
        data.clear();
    }
}
