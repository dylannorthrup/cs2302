package test;

import data.GeometricObject;

public class Hexagon extends GeometricObject implements Comparable {
  private double side;
  
  @Override
  public int compareTo(Object arg0) {
    double myArea = this.getPerimeter();
    double theirArea = ((Hexagon) arg0).getPerimeter();
    if(myArea > theirArea) {
      return 1;
    } else if (myArea < theirArea) {
      return -1;
    }
    return 0;
  }

  @Override
  public double getArea() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getPerimeter() {
    // TODO Auto-generated method stub
    return 0;
  }

}
