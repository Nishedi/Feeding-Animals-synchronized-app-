package controller;

import java.util.ArrayList;

public class Sensors {
    private ArrayList<Integer> stateoffood;
    private ArrayList<Integer> stamina;
    private ArrayList<Boolean> isfeeded;
    public Sensors(){
        stateoffood=new ArrayList<>();
        stamina= new ArrayList<>();
        isfeeded=new ArrayList<>();
    }
    public void addanimal(){
        stateoffood.add(65);
        stamina.add(10);
        isfeeded.add(false);
    }

    public void removeanimal(){
        stateoffood.remove(stateoffood.size()-1);
        stamina.remove(stamina.size()-1);
        isfeeded.remove(isfeeded.size()-1);
    }

    public void setstamina(int number, int value){
        stamina.set(number, value);
    }

    public Integer getstamina(int number){
        return stamina.get(number);
    }

    public synchronized int checkorsetstamina(int number, int action){
        int x =getstamina(number);
        if(action==1){//checkstamina
            return x;
        }
        if(action==0){//setstamina
            setstamina(number, x-1);
        }
        return 0;
    }

    public void settrueIsfeeded(int i) {//na tym obiekcie
        isfeeded.set(i,true);
    }
    public void setfalseIsfeeded(int i){
        isfeeded.set(i, false);
    }
    public Boolean getIsfeeded(int i){
        return isfeeded.get(i);
    }

    public int feedorconsume(int number, int direction){
        synchronized (this){
            if(direction==1)//feeding
            {
                settrueIsfeeded(number);
                setStateoffood(number, getStateofFood(number)+1);
            }
            if(direction==0)//consuming
            {
                if(getStateofFood(number)>0){
                    if(getIsfeeded(number)==false)
                       setStateoffood(number, getStateofFood(number)-1);
                }
            }
            return getStateofFood(number);
        }
    }


    public Integer getStateofFood(int i){
        return  stateoffood.get(i);
    }

    public void setStateoffood(int i, int value){
        stateoffood.set(i, value);
    }
    public int numberofanimals(){
        return stateoffood.size();
    }
}
