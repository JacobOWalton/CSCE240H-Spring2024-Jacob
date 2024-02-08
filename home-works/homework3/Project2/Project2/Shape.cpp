/*
 * Shape.cpp
 *
 *  Created on: Feb 5, 2022
 *      Author: biplavs
 */

#include <iostream>
#include <fstream>
#include <string>
#include <limits>
#include <algorithm>
#include <cmath>
#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"
#include "Triangle.h"

Shape::Shape() {
	
	cout << "Hi";
	


}

Shape::Shape(string str, string type) {
	
	cout << "Hi";

}


string Shape::getArea(string rectangle, string circle, string triangle) {
    
	return Rectangle::getArea(rectangle) + "\n" + Circle::getArea(circle) + "\n" + Triangle::getArea(triangle);
	

}

string Shape::getPerimeter(string rectangle, string circle, string triangle) {

	return Rectangle::getPerimeter(rectangle) + "\n" + Circle::getPerimeter(circle) + "\n" + Triangle::getPerimeter(triangle);


}

void Shape::getErrorMessage() {
	cout << "That is an invalid input, ending program.";
}

