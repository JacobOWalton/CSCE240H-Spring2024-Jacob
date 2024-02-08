/*
 * Circle.cpp
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
#include "Circle.h"

Circle::Circle() {

	cout << "Hi";


}

Circle::Circle(string n) {

	cout << "Hi";

}

string Circle::getArea(string str) {
    string word = "";
    string end1 = "";
    string word1 = "";
    string word2 = "";
    string word3 = "";
    string wordLast = "";
    string output = "";
    int i = 1;
    for (auto x : str)
    {
        if (x == ' ')
        {
            if (i == 1)
            {
                //cout << "hi1";
                word1 = (word + end1);
                word = "";
                i++;
            }
            else if (i == 2)
            {
                //cout << "hi2";
                word2 = (word + end1);
                word = "";
                i++;
            }
            else if (i == 3)
            {
                //cout << "hi3";
                word3 = (word + end1);
                word = "";
                i++;
            }
            else if (i > 4)
            {
                cout << "you have extra variables that will discarded";
            }

        }
        else
        {
            word = word + x;
        }
    }
    wordLast = (word + end1);
    transform(word1.begin(), word1.end(), word1.begin(), ::tolower);;

    if (wordLast == "")
    {
        output = "Not enough information to calculate Circle";
        return output;
    }
    else
    {
        output = "CIRCLE AREA " + to_string((pow(stoi(wordLast), 2) * 3.145));
        return output;
    }

}

string Circle::getPerimeter(string str) {
    string word = "";
    string end1 = "";
    string word1 = "";
    string word2 = "";
    string word3 = "";
    string wordLast = "";
    string output = "";
    int i = 1;
    for (auto x : str)
    {
        if (x == ' ')
        {
            if (i == 1)
            {
                //cout << "hi1";
                word1 = (word + end1);
                word = "";
                i++;
            }
            else if (i == 2)
            {
                //cout << "hi2";
                word2 = (word + end1);
                word = "";
                i++;
            }
            else if (i == 3)
            {
                //cout << "hi3";
                word3 = (word + end1);
                word = "";
                i++;
            }
            else if (i > 4)
            {
                cout << "you have extra variables that will discarded";
            }

        }
        else
        {
            word = word + x;
        }
    }
    wordLast = (word + end1);
    transform(word1.begin(), word1.end(), word1.begin(), ::tolower);;

    if (wordLast == "")
    {
        output = "Not enough information to calculate Circle";
        return output;
    }
    else
    {
        output = "CIRCLE PERIMETER " + to_string(((stoi(wordLast) * 2) * 3.145));
        return output;
    }

}