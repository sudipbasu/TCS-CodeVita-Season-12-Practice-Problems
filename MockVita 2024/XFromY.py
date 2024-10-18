INF = 4e18
def f(i, j, s, r, x, y, y1, dp): 
    if i > j:
        return 0 
    if dp[i][j] != -1:
        return dp[i][j]    
    mini = 1e9 
    for k in range(i, j+1):
        if y1.find(x[i:k+1]) != -1: 
            mini = min(mini, r + f(k+1, j, s, r, x, y, y1, dp))
        if y.find(x[i:k+1]) != -1: 
            mini = min(mini, s + f(k+1, j, s, r, x, y, y1, dp))
        dp[i][j] = mini
    return mini
def solve(s, r, x, y): 
    y1 = y[::-1]
    dp = [[-1 for _ in range(len(x))] for _ in range(len(x))] 
    return f(0, len(x)-1, s, r, x, y, y1, dp)
    
x = input() 
y = input()
s, r = map(int, input().split()) 
ans = solve(s, r, x, y)
print(ans)