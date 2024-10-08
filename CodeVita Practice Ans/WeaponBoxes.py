#Time Limit Exceeded

def is_triangular(num):
    # Check if num is a triangular number
    # T_n = n * (n + 1) / 2
    n = int((2 * num) ** 0.5)
    return n * (n + 1) // 2 == num

def weapon_boxes(weights, N, K):
    total_cost = 0
    triangular_numbers = set()
    
    # Find all triangular numbers up to 10^5
    for i in range(1, 10**5 + 1):
        triangular_num = i * (i + 1) // 2
        if triangular_num > 10**5:
            break
        triangular_numbers.add(triangular_num)
    
    # Main simulation loop
    while True:
        # Select the first N boxes
        selected_boxes = weights[:N]
        remaining_boxes = weights[N:]
        
        # Track how many cycles the same box remains un-shifted
        unshifted_count = 0
        
        while len(selected_boxes) > 1:
            if selected_boxes[0] > selected_boxes[1]:
                selected_boxes.append(selected_boxes[1])
            else:
                selected_boxes.append(selected_boxes[0])
            selected_boxes = selected_boxes[2:]
            unshifted_count += 1
        
        # Check if we should stop
        if unshifted_count >= K:
            break
        
        # Calculate the cost
        for weight in selected_boxes:
            if weight not in triangular_numbers:
                total_cost += weight
        
        # Prepare for the next cycle
        weights = remaining_boxes + selected_boxes
        
    return total_cost

# Read input
import sys
input = sys.stdin.read
data = input().split()
weights = list(map(int, data[:-2]))
N = int(data[-2])
K = int(data[-1])

# Compute and print the result
result = weapon_boxes(weights, N, K)
print(result)
