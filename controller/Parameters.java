package controller;

public class Parameters {
    public int speedofeating=5;
    public int speedofmoving=5;
    public int speedoffeeding =5;
    public int speedofgettingresource=5;

    public void increasespeedofgettingresource(){
        if(speedofgettingresource<=15) {
            speedofgettingresource++;
        }
    }
    public void decreasespeedofgettingresource(){
        if(speedofgettingresource>=1)
            speedofgettingresource--;
    }
    public void increasespeedofeating(){
        if(speedofeating<=10)
            speedofeating++;

    }
    public void decreasespeedofeating(){
        if(speedofeating>=1)
            speedofeating--;
    }
    public void increasespeedofmoving(){
        if(speedofmoving<=10)
            speedofmoving++;
    }
    public void decreasespeedofmoving(){
        if(speedofmoving>=1)
            speedofmoving--;
    }
    public void increasespeedoffeeding(){
        if(speedoffeeding <=10)
            speedoffeeding++;
    }
    public void decreasespeedoffeeding(){
        if(speedoffeeding >=1)
            speedoffeeding--;
    }
}
