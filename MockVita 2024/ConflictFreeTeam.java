import java.util.*;

public class ConflictFreeTeam {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of employees and conflicts
        int n = sc.nextInt();
        int c = sc.nextInt();

        // Graph to store conflicts (edges between employees)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read conflicts and build the graph (undirected)
        for (int i = 0; i < c; i++) {
            int u = sc.nextInt() - 1;  // Convert to 0-based index
            int v = sc.nextInt() - 1;  // Convert to 0-based index
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Read expertise levels
        int[] expertise = new int[n];
        for (int i = 0; i < n; i++) {
            expertise[i] = sc.nextInt();
        }

        // Track visited nodes to identify connected components
        boolean[] visited = new boolean[n];
        List<List<Integer>> components = new ArrayList<>();

        // Decompose the graph into connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, graph, visited, component);
                components.add(component);
            }
        }

        // Apply Bitmask DP on each connected component
        int maxExpertise = 0;
        for (List<Integer> component : components) {
            maxExpertise += getMaxExpertiseInComponent(component, expertise, graph);
        }

        // Output the maximum possible expertise
        System.out.println(maxExpertise);
    }

    // Depth-First Search to find connected components
    private static void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, component);
            }
        }
    }

    // Bitmask DP to find the maximum expertise in a component
    private static int getMaxExpertiseInComponent(List<Integer> component, int[] expertise, List<List<Integer>> graph) {
        int m = component.size();
        int maxMask = 1 << m;  // 2^m possible subsets
        int maxExpertise = 0;

        // Iterate over all possible subsets of the component
        for (int mask = 0; mask < maxMask; mask++) {
            if (isValidSubset(mask, component, graph)) {
                int currentExpertise = 0;
                for (int i = 0; i < m; i++) {
                    if ((mask & (1 << i)) != 0) {
                        currentExpertise += expertise[component.get(i)];
                    }
                }
                maxExpertise = Math.max(maxExpertise, currentExpertise);
            }
        }

        return maxExpertise;
    }

    // Check if a subset represented by the mask is valid (no conflicts)
    private static boolean isValidSubset(int mask, List<Integer> component, List<List<Integer>> graph) {
        int m = component.size();
        for (int i = 0; i < m; i++) {
            if ((mask & (1 << i)) != 0) {  // Employee i is in the subset
                for (int neighbor : graph.get(component.get(i))) {
                    int j = component.indexOf(neighbor);
                    if (j != -1 && (mask & (1 << j)) != 0) {  // Conflict detected
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
