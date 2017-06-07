package com.troy.multiarray;

/**
 * http://www.geeksforgeeks.org/divide-and-conquer-set-6-tiling-problem/
 * 
 * Given a n by n board where n is of form 2k where k >= 1 (Basically n is a power of 2 with minimum value as 2). 
 * The board has one missing cell (of size 1 x 1). Fill the board using L shaped tiles. A L shaped tile is a 2 x 2 
 * square with one cell of size 1×1 missing.
 * 
 * 
 * Test cases
 * Size of matrix is at least 2 and power of 2 always
 * Missing point could be on edges
 * Missing point could be in any of 4 quadrants
 */

class Position{
    int x;
    int y;
    Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
}

public class TilingProblem {

    char tileCount = 'a';
    public char[][] fit(int size, Position missingPosition){
        char matrix[][] = new char[size][size];
        matrix[missingPosition.x][missingPosition.y] = 'X';
        fit(matrix, new Position(0,0), matrix.length, missingPosition);
        return matrix;
    }
    
    private void fit(char matrix[][], Position topLeft,
            int size, Position missingPosition){
        if(size == 2){
            updateMatrix(matrix, topLeft, missingPosition);
            return;
        }
        Position alreadyFilledQuadrantPosition = determineQuadrant(topLeft, size, missingPosition);
        updateMatrix(matrix, new Position(topLeft.x + size/2-1, topLeft.y + size/2-1), alreadyFilledQuadrantPosition);
        for(int i=0 ; i < 2; i++){
            for(int j=0; j < 2; j++){
                Position newMissingPosition = new Position(topLeft.x + size/2 -1+i, topLeft.y + size/2 -1 + j);
                if(newMissingPosition.equals(alreadyFilledQuadrantPosition)){
                    fit(matrix, new Position(topLeft.x + i*size/2, topLeft.y + j*size/2) , size/2, missingPosition);
                }else{
                    fit(matrix, new Position(topLeft.x + i*size/2, topLeft.y + j*size/2) , size/2, newMissingPosition);
                }
            }
        }
    }
    
    private Position determineQuadrant(Position topLeft, int size, Position missingPosition){
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                if(missingPosition.x >= topLeft.x + i*size/2 && missingPosition.x <= topLeft.x + i*size/2 + size/2-1 &&
                        missingPosition.y >= topLeft.y + j*size/2 && missingPosition.y <= topLeft.y + j*size/2 + size/2 -1){
                        return new Position(topLeft.x+size/2 -1 +i, topLeft.y + size/2 - 1 + j);
                }
            }
        }
        throw new IllegalArgumentException("Something went wrong in determining quadrant");
    }
    
    private void updateMatrix(char matrix[][], Position topLeft, Position missingPosition){
        for(int i=topLeft.x; i < topLeft.x + 2; i++){
            for(int j=topLeft.y; j < topLeft.y + 2; j++){
                if(i == missingPosition.x && j == missingPosition.y){
                    continue;
                }
                matrix[i][j] = tileCount;
            }
        }
        tileCount++;
    }
    
    public static void main(String args[]){
        TilingProblem tp = new TilingProblem();
        Position p = new Position(5,6);
        char matrix[][] = tp.fit(8, p);
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[0].length ; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
