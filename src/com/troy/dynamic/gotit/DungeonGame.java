package com.troy.dynamic.gotit;

import java.util.Arrays;

/**
 * Date 03/21/2016
 * @author Tushar Roy
 *
 * The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists 
 * of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight 
 * his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, 
he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms 
are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal 
path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)		-3		3
-5			-10		1
10			30		-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room 
where the princess is imprisoned.

Solution:
Notice that minimum health required to approach any cell is 1, MIN_REQUIRED_HEALTH=1
1) Create health table which has min health required approaching current cell that will have path to end cell i.e. you  will
	have enough energy to move to next cell
2) Start from the end cell, propagate to first cell. e.g. 
	if next cell value is 6 and current cell value is 1 then min health required for current cell is 5 since 
	5 + 1(cur cell boost)=6
	

	
 * 
 * Time complexity O(n^2)
 * Space complexity O(n^2)
 *
 * https://leetcode.com/problems/dungeon-game/
 */
public class DungeonGame {
	final int MIN_REQUIRED_HEALTH=1;
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        int[][] health = new int[dungeon.length][dungeon[0].length];

        int m = dungeon.length - 1;
        int n = dungeon[0].length - 1;
        print(dungeon);

        //end cell, min health required
        health[m][n] = Math.max(MIN_REQUIRED_HEALTH - dungeon[m][n] , MIN_REQUIRED_HEALTH);

        //fill the last column
        for (int i = m - 1; i >= 0; i--) {
            health[i][n] = Math.max(health[i + 1][n] - dungeon[i][n], MIN_REQUIRED_HEALTH);
        }

        //fill the last row
        for (int i = n - 1; i >= 0; i--) {
            health[m][i] = Math.max(health[m][i+1] - dungeon[m][i], MIN_REQUIRED_HEALTH);
        }

        //fill intermediate cells
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                health[i][j] = Math.min(Math.max(health[i + 1][j] - dungeon[i][j], MIN_REQUIRED_HEALTH), 
                						Math.max(health[i][j + 1] - dungeon[i][j], MIN_REQUIRED_HEALTH));
                print(health);
            }
        }

        return health[0][0];
    }
    
    public void print(int[][] a){
    	for (int i = 0; i < a.length; i++) {
			System.out.println(Arrays.toString(a[i]));
		}

		System.out.println("-------------------");
    }

    public static void main(String args[]) {
        DungeonGame dg = new DungeonGame();
        int[][] dungeon1 = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(dg.calculateMinimumHP(dungeon1));
        
        //int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -30}};
        //System.out.println(dg.calculateMinimumHP(dungeon));
    }
}
