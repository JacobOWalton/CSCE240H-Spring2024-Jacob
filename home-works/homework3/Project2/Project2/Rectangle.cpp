/*
 * Rectangle.cpp
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
#include "Rectangle.h"

Rectangle::Rectangle() {

	cout << "Hi";


}


Rectangle::Rectangle(string n) {

	cout << "Hi";

}

string Rectangle::getArea(string str) {

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

    if (word2 == "" or wordLast == "")
    {
        output = "Not enough information to calculate Rectangle";
        return output;
    }
    else
    {
        output = "RECTANGLE AREA " + to_string((stoi(word2) * stoi(wordLast)));
        return output;
    }

}

string Rectangle::getPerimeter(string str) {

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
    if (word2 == "" or wordLast == "")
    {
        output = "Not enough information to calculate Rectangle";
        return output;
    }
    else
    {
        output = "RECTANGLE PERIMETER " + to_string(((2 * stoi(word2)) + (2 * stoi(wordLast))));
        return output;
    }

}