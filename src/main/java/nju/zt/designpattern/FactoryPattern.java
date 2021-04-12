package nju.zt.designpattern;

import java.sql.SQLOutput;

/**
 * @ClassName FactoryPattern
 * @Description //工厂模式
 * @Author zt
 * @Date 2021/1/31 17:21
 * @Version 1.0
 **/
public class FactoryPattern {
    interface Shape {
        void draw();
    }

    static class Rectangle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw a Rectangle");
        }
    }

    static class Circle implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw a circle");
        }
    }

    static class Square implements Shape {
        @Override
        public void draw() {
            System.out.println("Draw a square");
        }
    }

    static class ShapeFactory {
        public Shape getShape(String shape) {
            if (shape == "Rectangle") {
                return new Rectangle();
            } else if (shape == "Circle") {
                return new Circle();
            } else if (shape == "Square") {
                return new Square();
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();
        factory.getShape("Circle").draw();
        factory.getShape("Rectangle").draw();
        factory.getShape("Square").draw();
    }
}