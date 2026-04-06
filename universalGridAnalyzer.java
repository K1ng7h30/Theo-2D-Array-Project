import java.util.Scanner;

public class universalGridAnalyzer 
{
    private int[][] grid;

    public universalGridAnalyzer(int [][] g)
    {
        
        grid =g;
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        int [][] g = 
        {
            {5, 3, 8, 2, 7, 1},
            {4, 9, 6, 3, 8, 5},
            {2, 7, 4, 9, 1, 6},
            {8, 1, 5, 3, 9, 2},
            {6, 4, 7, 7, 2, 5},
            {3, 9, 1, 6, 4, 7}
        };
        universalGridAnalyzer table = new universalGridAnalyzer(g);

        int choice =-1;
        while(choice!=0)
        {
            System.out.println("1. Display Grid");
            System.out.println("2. Row/Column Sums");
            System.out.println("3. Max/Min");
            System.out.println("4. Frequency Check");
            System.out.println("5. Row/Column Comparison"); 
            System.out.println("6. Pattern Detection");
            System.out.println("7. Transform Grid");
            System.out.println("8. Subgrid Analysis");
            System.out.println("9. Boundary & Diagonals");
            System.out.println("10. Validation Check"); 
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();

            if (choice == 1) 
            {
                table.displayGrid();
            } 
            else if (choice == 2) 
            {
                table.rowColumnSums();
            } 
            else if (choice == 3) 
            {
                table.globalMaxMin();
            } 
            else if (choice == 4) 
            {
                table.frequencyCheck(10);
            } 
            else if (choice == 5) 
            {
                table.rowColumnComparison(); 
            } 
            else if (choice == 6) 
            {
                int status = table.patternDetection();
                if (status != -1) 
                {
                    System.out.println("Row " + status + " is strictly increasing.");
                }           
                else 
                {
                    System.out.println("No strictly increasing rows found.");
                }
            } 
            else if (choice == 7) 
            {
                table.transformGrid();
            } 
            else if (choice == 8) 
            {
                table.subgridAnalysis();
            } 
            else if (choice == 9) 
            {
                table.boundaryAndDiagonals();
            } 
            else if (choice == 10) 
            {
                table.validationCheck();
            } 
            else if (choice == 0) 
            {
                System.out.println("Exit");
            } 
            else 
            {
                System.out.println("Invalid choice! Try again.");
            }
        }
        
        scanner.close();
    }

    // display
    public  void displayGrid()
    {
        for (int[] row : grid) 
        {
            for (int val : row) 
            {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    // algorithm 1
    public void rowColumnSums()
    {
        int rowSum[] = new int [grid.length];
        int colSum[] = new int [grid[0].length];

        for(int r=0; r<grid.length; r++)
        {
            for(int c=0; c<grid[0].length; c++)
            {
                rowSum[r] += grid[r][c];
                colSum[c] += grid[r][c];
            }
        }

        for(int r=0; r<rowSum.length; r++)
        {
            System.out.println("Sum of row " + r +" is " + rowSum[r]);
        }
        for(int c=0; c<colSum.length; c++)
        {
            System.out.println("Sum of col " + c +" is " + colSum[c]);
        }
    }

    // algorithm 2
    public void globalMaxMin()
    {
        int max = grid[0][0];
        int min = grid[0][0];

        int maxR=0, maxC=0;
        int minR=0, minC=0;

        for(int r=0; r<grid.length; r++)
        {
            for(int c=0; c<grid[0].length; c++)
            {
                if(grid[r][c] > max)
                {
                    maxR=r;
                    maxC=c;
                }
                if(grid[r][c] < min)
                {
                    minR=r;
                    minC=c;
                }
            }
        }
        System.out.println("The max value is at (" + maxR +", " + maxC + ").");
        System.out.println("The min value is at (" + minR +", " + minC + ").");
    }

    //algorithm 3
    public void frequencyCheck(int threshold)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the target value: ");

        int target = input.nextInt();
        int targetCount = 0;
        int thresholdCount = 0;

        for (int r = 0; r < grid.length; r++) 
        {
            for (int c = 0; c < grid[r].length; c++) 
            {
            if (grid[r][c] == target) 
            {
                targetCount++;
            }
            if (grid[r][c] > threshold) 
            {
                thresholdCount++;
            }
            }
        }
        System.out.println("Matches for [" + target + "]: " + targetCount);
        System.out.println("Values above [" + threshold + "]: " + thresholdCount);
    }

    //algorithm 4
    public void rowColumnComparison()
    {
        int rowSum[] = new int [grid.length];
        int colSum[] = new int [grid[0].length];

        for(int r=0; r<grid.length; r++)
        {
            for(int c=0; c<grid[0].length; c++)
            {
                rowSum[r] += grid[r][c];
                colSum[c] += grid[r][c];
            }
        }

        int maxRow = rowSum[0];
        int rowIndex =0;
        for(int r=0; r<rowSum.length;r++)
        {
            if(rowSum[r]>maxRow)
            {
                maxRow = rowSum[r];
                rowIndex = r;
            }
        }
        System.out.println("Row with the highest sum is row " + rowIndex);
        
        int minCol = colSum[0];
        int colIndex =0;
        for(int c=0; c<colSum.length;c++)
        {
            if(colSum[c]<minCol)
            {
                minCol = colSum[c];
                colIndex = c;
            }
        }
        System.out.println("Column with the lowest sum is column " + colIndex);


    }

    //algorithim 5
    public int patternDetection()
    {
        for (int r = 0; r < grid.length; r++) 
        {
        boolean isIncreasing = true;
        for (int c = 0; c < grid[r].length - 1; c++) 
        {
            if (grid[r][c] >= grid[r][c + 1]) 
            {
                isIncreasing = false;
            }
        }
        if (isIncreasing) 
        {
            return r;
        }
        }
        return -1;
    }

    //algorithm 6
    public void transformGrid()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Transformation Menu");
        System.out.println("1. Rotate Row Right");
        System.out.println("2. Swap Two Rows");
        System.out.println("3. Reverse a Column");
        System.out.print("Select transformation: ");
        int subChoice = input.nextInt();

        if (subChoice == 1) 
        {
            System.out.print("Enter row index to rotate: ");
            int r = input.nextInt();
            int last = grid[r][grid[r].length - 1];
            for (int c = grid[r].length - 1; c > 0; c--) 
            {
                grid[r][c] = grid[r][c - 1];
            }
            grid[r][0] = last;
        }

        else if (subChoice == 2) 
        {
            System.out.print("Enter first row: ");
            int r1 = input.nextInt();
            System.out.print("Enter second row: ");
            int r2 = input.nextInt();
            int[] temp = grid[r1];
            grid[r1] = grid[r2];
            grid[r2] = temp;
        } 
        else if (subChoice == 3) 
        {
            System.out.print("Enter column index to reverse: ");
            int c = input.nextInt();
            int top = 0;
            int bottom = grid.length - 1;
            while (top < bottom) 
            {
                int temp = grid[top][c];
                grid[top][c] = grid[bottom][c];
                grid[bottom][c] = temp;
                top++;
                bottom--;
            }
        }

    }

    //algorithm 7
    public void subgridAnalysis()
    {
        int size = grid.length; 
        int mid = size / 2;
        int quadrant = 1;

        for (int startRow = 0; startRow < size; startRow += mid) 
        {
            for (int startCol = 0; startCol < size; startCol += mid) 
            {
                int sum = 0;
                int count = 0;

                for (int r = startRow; r < startRow + mid; r++) 
                    {
                        for (int c = startCol; c < startCol + 1 * mid; c++) 
                        {
                            sum += grid[r][c];
                            count++;
                        }
                    }       

                double average = (double) sum / count;
                System.out.println("Quadrant " + quadrant + " Sum: " + sum + " | Avg: " + average);
                quadrant++;
            }
        }
    }

    //algorithm 8
    public void boundaryAndDiagonals()
    {
        int boundarySum = 0;
        int diagonalSum = 0;
        int lastIdx = grid.length - 1;

        for (int r = 0; r < grid.length; r++) 
            {
                for (int c = 0; c < grid[r].length; c++) 
                {
                    if (r == 0 || r == lastIdx || c == 0 || c == lastIdx) 
                    {
                        boundarySum += grid[r][c];
                    }
                    if (r == c || r + c == lastIdx) 
                    {
                        diagonalSum += grid[r][c];
                    }
                }
            }

        System.out.println("Sum of Boundary: " + boundarySum);
        System.out.println("Sum of Diagonals: " + diagonalSum);
    }

    //algorithm 9
    public boolean validationCheck()
    {
        boolean status = false;
        for(int r=0; r<grid.length; r++)
        {
            for(int c=0; c<grid[0].length-1; c++)
            {
                if(grid[r][c] == grid[r][c+1])
                {
                    status= true;
                    System.out.println(status + "Duplicate found at row "+ r);

                }
            }
        }
        return status;

    }
}
 
