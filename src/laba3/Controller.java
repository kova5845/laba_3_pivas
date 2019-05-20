/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba3;

import java.util.List;

/**
 *
 * @author alexey
 */
public class Controller {
    private final static double H = 0.1;
    MainDialog mainDialog;
    GraficData graficData;

    public Controller(MainDialog mainDialog) {
        this.mainDialog = mainDialog;
        graficData = new GraficData();
    }
    
    public List<Function> getData(){
        return graficData.getData();
    }
    
    public void setData(List<Function> data){
        graficData.setData(data);
    }
    
    public void addPoint(Function point){
        graficData.addPoint(point);
    }
    
    public void firstFunction(double lowX, double heightX, int scale){
        graficData.clear();
        for(double i = lowX/scale; i < heightX/scale; i += H){
            graficData.addPoint(new Function(Math.round(i * 10000d) / 10000d,
                    Math.round(i * 10000d) / 10000d + 1));
            System.out.println(graficData.data.size());
        }
    }
    
    public List<Function> getFirstFunction(){
        return graficData.getData();
    }
    
    
}
