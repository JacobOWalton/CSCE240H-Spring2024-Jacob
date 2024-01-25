
#include <iostream>
#include <fstream>
#include <string>
#include <limits>
#include <algorithm>
#include <cmath>

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
    //cout << word1;
    //cout << "\n";
    //cout << word2;
    //cout << "\n";
    //cout << word3;
    //cout << "\n";
    //cout << wordLast;
    //cout << "\n";
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
        cout << "That is an invalid input, ending program.";
        return 0;
    }

    ifstream myfile;
    myfile.open("input.txt");
    string shape1;
    string shape2;
    string shape3;
    ifstream f("input.txt");
    string s;


    for (int i = 1; i <= 3; i++) {
        getline(f, s);

        if (i == 1)
        {
            shape1 = s;

        }

        if (i == 2)
        {

            shape2 = s;

        }

        if (i == 3)
        {
            shape3 = s;

        }

    }
    myfile.close();

    ofstream outfile;
    outfile.open("output.txt");
    //cout << "\n";
    outfile << removeDupWord(shape1, type);
    outfile << "\n";
    //cout << "\n";
    outfile << removeDupWord(shape2, type);
    outfile << "\n";
    //cout << "\n";
    outfile << removeDupWord(shape3, type);
    outfile << "\n";

    
    outfile.close();
    /*
    outfile.open("output.txt");
    if (mystring == "add")
    {
        outfile << "The result of adding " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << to_string(int1 + int2);
    }

    if (mystring == "subtract")
    {
        outfile << "The result of subtracting " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << to_string(int1 - int2);
    }

    if (mystring == "multiply")
    {
        outfile << "The result of multiplying " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << to_string(int1 * int2);
    }

    if (mystring == "divide")
    {
        outfile << "The result of dividing " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << to_string((double)int1 / (double)int2);
    }
    outfile.close();
    */
    return 0;
}
