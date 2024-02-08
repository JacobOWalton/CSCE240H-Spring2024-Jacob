
#include <iostream>
#include <fstream>
#include <string>
#include <limits>
#include <algorithm>
#include <cmath>
#include "Shape.h"

using namespace std;

string removeDupWord(string str, string type)
{
    
    //Parts of this following code are borrowed from GeeksforGeek
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
    //transform(word1.begin(), word1.end(), word1.begin(), ::tolower);;
    if (type == "AREA")
    {
        //Next line borrowed from tutorialspoint
        transform(word1.begin(), word1.end(), word1.begin(), ::tolower);;
        if (word1 == "rectangle")
        {
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
        else if (word1 == "circle")
        {
            if (wordLast == "")
            {
                output = "Not enough information to calculate Circle";
                return output;
            }
            else
            {
                output = "CIRCLE AREA " + to_string((pow(stoi(wordLast),2) * 3.145));
                return output;
            }
        }
        else if (word1 == "triangle")
        {
            if (word2 == "" or word3 == "" or wordLast == "")
            {
                output = "Not enough information to calculate Triangle";
                return output;
            }
            else
            {
                output = "TRIANGLE AREA " + to_string( sqrt( pow(stoi(word2),2) - pow((stoi(word3)/2),2)) );
                return output;
            }
        }
    }
    if (type == "PERIMETER")
    {   
        
        //Next line borrowed from tutorialspoint
        transform(word1.begin(), word1.end(), word1.begin(), ::tolower);;
        if (word1 == "rectangle")
        {
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
        else if (word1 == "circle")
        {
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
        else if (word1 == "triangle")
        {
            if (word2 == "" || word3 == "" || wordLast == "")
            {
                output = "Not enough information to calculate Triangle";
                return output;
            }
            else
            {
                output = "TRIANGLE PERIMETER " + to_string(stoi(word2) + stoi(word3) + stoi(wordLast));
                return output;
            }
        }
    }
    
}

int main() {
    string type = "";
    cout << "1 for AREA, 2 for PERIMETER";
    //Parts of this code were borrowed from the cplusplus.com forum
    string input;
    cin >> input;
    if (input == "1")
    {
        type = "AREA";
    }
    else if (input == "2")
    {
        type = "PERIMETER";
    }
    else
    {
        Shape::getErrorMessage();
        return 0;
    }

    ifstream myfile;
    myfile.open("input.txt");
    string rectangle;
    string circle;
    string triangle;
    ifstream f("input.txt");
    string s;


    for (int i = 1; i <= 3; i++) {
        getline(f, s);

        if (i == 1)
        {
            rectangle = s;

        }

        if (i == 2)
        {

            circle = s;

        }

        if (i == 3)
        {
            triangle = s;

        }

    }
    myfile.close();
    ofstream outfile;
    outfile.open("output.txt");
    //cout << "\n";
    //Shape(shape1, type);
    /*
    outfile << removeDupWord(rectangle, type);
    outfile << "\n";
    //cout << "\n
    outfile << removeDupWord(circle, type);
    outfile << "\n";
    //cout << "\n";
    outfile << removeDupWord(triangle, type);
    outfile << "\n";
    */
    //outfile.close();


    if (type == "AREA")
    {
        outfile << Shape::getArea(rectangle, circle, triangle);
    }

    if (type == "PERIMETER")
    {
        outfile << Shape::getPerimeter(rectangle, circle, triangle);
    }
    outfile.close();
    return 0;
}
