package com.troy.graph.gotit;

import java.util.HashMap;
import java.util.Map;

/**
 * Date 10/31/2016
 * @author Tushar Roy
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real 
 * number 
 * (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , 
where 
equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there 
is no contradiction. *


 * Solution
 * Do Flyod warshall algorithm initialized as values between equations. Do Flyod Warshall to create
 * all possible paths b/w two strings.
 *
 * Time complexity O(n * n * n) + O(m)
 * where n is total number of strings in equations and m is total number of queries
 *
 * Reference
 * https://leetcode.com/problems/evaluate-division/
 */
public class EvaluateDivison {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations.length == 0) {
            return new double[0];
        }
        Map<String, Integer> index = new HashMap<>();
        int count = 0;
        for (int i = 0; i < equations.length; i++) {
            String first = equations[i][0];
            String second = equations[i][1];
            if (!index.containsKey(first)) {
                index.put(first, count++);
            }
            if (!index.containsKey(second)) {
                index.put(second, count++);
            }
        }

        double graph[][] = new double[count][count];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                graph[i][j] = -1;
            }
        }

        for (int i = 0; i < equations.length; i++) {
            String first = equations[i][0];
            String second = equations[i][1];
            int i1 = index.get(first);
            int i2 = index.get(second);
            graph[i1][i1] = graph[i2][i2] = 1.0;
            graph[i1][i2] = values[i];
            graph[i2][i1] = 1/values[i];
        }

        for (int i = 0 ; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] != -1) {
                    continue;
                }
                for (int k = 0; k < graph.length; k++) {
                    if (graph[i][k] == -1 || graph[k][j] == -1) {
                        continue;
                    }
                    graph[i][j] = graph[i][k] * graph[k][j];
                }
            }
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String first = queries[i][0];
            String second = queries[i][1];
            if (!index.containsKey(first) || !index.containsKey(second)) {
                result[i] = -1;
            } else {
                int i1 = index.get(first);
                int i2 = index.get(second);
                result[i] = graph[i1][i2];
            }
        }

        return result;
    }
}
