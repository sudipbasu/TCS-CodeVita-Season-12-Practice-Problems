#include <bits/stdc++.h>
using namespace std;

int main() {
    string input1, input2;
    getline(cin, input1);
    getline(cin, input2);
    int resultDistance = 0;
    vector<int> charValues;
    
    for (auto character : input1) {
        charValues.push_back((int)character);
    }
    
    sort(charValues.begin(), charValues.end());
    int totalDistance = 0;
    int previousChar = (int)input1[0];
    
    for (auto character : input2) {
        int targetValue = (int)character;
        int left = 0, right = charValues.size() - 1;
        int minDistance = INT_MAX;
        int nearestChar = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (charValues[mid] == targetValue) {
                nearestChar = previousChar;
                minDistance = 0;
                break;
            }
            else if (charValues[mid] < targetValue) {
                if (abs(charValues[mid] - targetValue) < minDistance) {
                    minDistance = abs(charValues[mid] - targetValue);
                    nearestChar = charValues[mid];
                }
                else if (abs(charValues[mid] - targetValue) == minDistance) {
                    if (abs(nearestChar - previousChar) > abs(charValues[mid] - previousChar)) {
                        nearestChar = charValues[mid];
                        minDistance = abs(charValues[mid] - previousChar);
                    }
                    else {
                        minDistance = abs(nearestChar - previousChar);
                    }
                }
                left = mid + 1;
            }
            else {
                if (abs(charValues[mid] - targetValue) < minDistance) {
                    minDistance = abs(charValues[mid] - targetValue);
                    nearestChar = charValues[mid];
                }
                else if (abs(charValues[mid] - targetValue) == minDistance) {
                    if (abs(nearestChar - previousChar) > abs(charValues[mid] - previousChar)) {
                        nearestChar = charValues[mid];
                        minDistance = abs(charValues[mid] - previousChar);
                    }
                    else {
                        minDistance = abs(nearestChar - previousChar);
                    }
                }
                right = mid - 1;
            }
        }
        previousChar = nearestChar;
        totalDistance += minDistance;
    }
    cout << totalDistance;
}
