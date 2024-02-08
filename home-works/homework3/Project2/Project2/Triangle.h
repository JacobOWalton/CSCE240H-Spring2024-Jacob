/*
 * Triangle.h
 *Code borrowed from: biplavs
 */

#ifndef TRIANGLE_H_
#define TRIANGLE_H_

#include <string>

using namespace std;

class Triangle {
protected:
	string myname; // name of the class

public:
	Triangle();
	Triangle(string);
	static string getArea(string str);
	static string getPerimeter(string str);

};

#endif /* Triangle_H_ */
