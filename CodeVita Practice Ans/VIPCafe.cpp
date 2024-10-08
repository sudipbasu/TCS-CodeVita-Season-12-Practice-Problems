#include <bits/stdc++.h>
using namespace std;

int findMaxIndex(vector<int>& values, int targetIndex) {
    int maxIndex = -1;
    int maxValue = -1;
    int size = values.size();

    for (int i = 0; i < size; i++) {
        if (maxValue < values[i]) {
            maxIndex = i;
            maxValue = values[i];
        }
    }

    if (maxIndex == targetIndex) return 0;

    values[maxIndex] = -1;

    for (int i = 0; i < maxIndex; i++) {
        if (values[i] > 0) values[i]++;
    }

    return 1 + findMaxIndex(values, targetIndex);
}

int main() {
    int testCases, arraySize;
    cin >> arraySize;
    vector<int> values(arraySize);
    for (auto &element : values) cin >> element;

    int targetIndex;
    cin >> targetIndex;
    targetIndex--;

    cout << 1 + findMaxIndex(values, targetIndex) << endl;
    return 0;
}
