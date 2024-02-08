/*
 * Shape.h
 *Code borrowed from: biplavs
 */

#ifndef SHAPE_H_
#define SHAPE_H_

#include <string>

using namespace std;

class Shape {
protected:
	string myname; // name of the class

public:
	Shape();
	Shape(string, string);
	static string getArea(string rectangle, string circle, string triangle);
	static string getPerimeter(string rectangle, string circle, string triangle);
	static void getErrorMessage();
	
};

#endif /* Shape_H_ */