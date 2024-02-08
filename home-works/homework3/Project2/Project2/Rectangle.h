/*
 * Rectangle.h
 *Code borrowed from: biplavs
 */

#ifndef RECTANGLE_H_
#define RECTANGLE_H_

#include <string>

using namespace std;

class Rectangle {
protected:
	string myname; // name of the class

public:
	Rectangle();
	Rectangle(string);
	static string getArea(string str);
	static string getPerimeter(string str);

};

#endif /* Rectangle_H_ */
