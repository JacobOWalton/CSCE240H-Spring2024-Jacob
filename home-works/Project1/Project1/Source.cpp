// Your First C++ Program

#include <iostream>
#include <fstream>
#include <string>
#include <limits>


int main() {
    std::ifstream myfile;
    myfile.open("input.txt");
    std::string mystring;
    int int1;
    int int2;
    std::ifstream f("input.txt");
    std::string s;


    for (int i = 1; i <= 3; i++) {
        std::getline(f, s);

        if (i == 1)
        {
            mystring = s;

        }

        if (i == 2)
        {

            int1 = stoi(s);

        }

        if (i == 3)
        {
            int2 = stoi(s);

        }

    }
    myfile.close();
    std::ofstream outfile;
    outfile.open("output.txt");
    if (mystring == "add")
    {
        outfile << "The result of adding " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << std::to_string(int1 + int2);
    }

    if (mystring == "subtract")
    {
        outfile << "The result of subtracting " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << std::to_string(int1 - int2);
    }

    if (mystring == "multiply")
    {
        outfile << "The result of multiplying " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << std::to_string(int1 * int2);
    }

    if (mystring == "divide")
    {
        outfile << "The result of dividing " + std::to_string(int1) + " and " + std::to_string(int2) + " is below. \n";
        outfile << std::to_string((double)int1 / (double)int2);
    }
    outfile.close();
    return 0;
}
