#include <iostream>
using namespace std;

int main(int argc, char const *argv[])
{
    int totalLength, numDivisions;
    cin >> totalLength >> numDivisions;

    if (numDivisions == 0)
    {
        cout << totalLength << endl;
    }
    else
    {
        int remainingLength = totalLength - numDivisions;
        int myVar = remainingLength / (numDivisions + 1); // Changed variable name to myVar
        
        if (remainingLength % (numDivisions + 1) != 0)
        {
            myVar++; // Updated to use myVar
        }

        cout << myVar << endl; // Updated to use myVar
    }
}
