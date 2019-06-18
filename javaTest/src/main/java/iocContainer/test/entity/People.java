package iocContainer.test.entity;

public class People {

    private Food food;

    public People(){

    }

    public People(Food food){
        this.food = food;
    }

    public void foodColor(){
        food.color();
    }
}
