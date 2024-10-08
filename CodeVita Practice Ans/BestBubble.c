#include <stdio.h>

int descendingCount = 0, ascendingCount = 0;

void sortDescending(int dataArray[], int arraySize) {
    for (int step = 0; step < arraySize - 1; ++step) {
        int swapped = 0; // Track if a swap occurred
        for (int index = 0; index < arraySize - step - 1; ++index) {
            if (dataArray[index] < dataArray[index + 1]) {
                int temp = dataArray[index];
                dataArray[index] = dataArray[index + 1];
                dataArray[index + 1] = temp;
                descendingCount++;
                swapped = 1; // Set swapped to true
            }
        }
        if (!swapped) break; // Break if no swaps occurred
    }
}

void sortAscending(int dataArray[], int arraySize) {
    for (int step = 0; step < arraySize - 1; ++step) {
        int swapped = 0; // Track if a swap occurred
        for (int index = 0; index < arraySize - step - 1; ++index) {
            if (dataArray[index] > dataArray[index + 1]) {
                int temp = dataArray[index];
                dataArray[index] = dataArray[index + 1];
                dataArray[index + 1] = temp;
                ascendingCount++;
                swapped = 1; // Set swapped to true
            }
        }
        if (!swapped) break; // Break if no swaps occurred
    }
}

int main() {
    int arraySize;
    scanf("%d", &arraySize);
    
    int originalArray[arraySize], copyArray[arraySize];
    
    for (int i = 0; i < arraySize; i++) {
        scanf("%d", &originalArray[i]);
        copyArray[i] = originalArray[i];
    }
    
    sortDescending(originalArray, arraySize);
    sortAscending(copyArray, arraySize);
    
    if (descendingCount > ascendingCount) {
        printf("%d", ascendingCount);
    } else {
        printf("%d", descendingCount);
    }

    return 0;
}
