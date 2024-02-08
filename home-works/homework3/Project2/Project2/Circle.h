/*
 * Circle.h
 *Code borrowed from: biplavs
 */

#ifndef CIRCLE_H_
#define CIRCLE_H_

#include <string>

using namespace std;

class Circle {
protected:
	string myname; // name of the class

public:
	Circle();
	Circle(string);
	static string getArea(string str);
	static string getPerimeter(string str);

};

#endif /* Circle_H_ */
