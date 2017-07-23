package com.example.OldModel;

/**
 * Created by Thomas on 01.10.2014.
 */
public class Rules
{
    /**
     * The maximum totalLength of one row/column.
     */
    public final static int maxLengthOfRow = 10;
    public static final int MAX_LOAD_DEFAULT = 8;
    private final int rows;
    private final int columns;
    RuleVariant ruleVariant;

    private int lengthFactor;
    private int timeLimitInS;
    private boolean timeLimit;
    /**
     * Points can be achieved diagonally.
     */
    private boolean diagonal = false;
    /**
     * The number of dices.
     */
    private int numberOfDices;
    /**
     * The minimum of same dices for points.
     */
    private int minXOfAKind;
    /**
     * The points for a straight. The index is the totalLength of the straight.
     */
    private int[][] straightPoints;
    /**
     * Points for x of a kind. The first index is the X, the second index is the value of the dice.
     */
    private int[][] xOfAKindPoints;
    /**
     * The minimum following dices for a straight.
     */
    private int minStraight;

    private int allowedStrikes;
    private int pointLimit;
    private int gameEndPoints;
    private double skillLoadFactor;
    private GameLength gameLength;

    /**
     * Creates standard Rules:
     * 6 Dices.
     * No Diagonal.
     * No Full House.
     * No Straight.
     * 3-10 XofAKind.  (1 Points)
     */
    public Rules()
    {
        this.numberOfDices = 6;
        this.minStraight = this.numberOfDices + 1;
        this.minXOfAKind = 3;
        this.skillLoadFactor = 1;
        this.columns = 5;
        this.rows = 5;

        this.allowedStrikes = 2;
        this.lengthFactor = 5;
        this.initStraightPoints(2);
        this.initXOfAKindPoints(this.minXOfAKind, maxLengthOfRow, 1, 2);

        this.pointLimit = 45;
        this.gameLength = GameLength.Normal;
    }

    public static Rules makeRules(RuleVariant ruleVariant)
    {
        Rules rules = new Rules();
        switch (ruleVariant)
        {
            case Baden_Baden:
                rules.setDiagonalActive(false);
                rules.setMinStraight(rules.numberOfDices + 1);
                rules.setMinXOfAKind(3);
                rules.setPointLimit(45);
                rules.setSkillLoadFactor(1);
                break;
            case Basel:
                rules.setDiagonalActive(true);
                rules.setMinStraight(rules.numberOfDices + 1);
                rules.setMinXOfAKind(3);
                rules.setPointLimit(90);
                rules.setSkillLoadFactor(2);
                break;
            case Las_Vegas:
                rules.setDiagonalActive(true);
                rules.setMinStraight(3);
                rules.setMinXOfAKind(3);
                rules.setPointLimit(270);
                rules.setSkillLoadFactor(5);
                break;
            case Macao:
                rules.setDiagonalActive(true);
                rules.setMinStraight(3);
                rules.setMinXOfAKind(Math.max(rules.rows, rules.columns) + 1);
                rules.setPointLimit(70);
                rules.setSkillLoadFactor(1.5);
                break;
            case Atlantic_City:
                rules.setDiagonalActive(false);
                rules.setMinStraight(3);
                rules.setMinXOfAKind(3);
                rules.setPointLimit(70);
                rules.setSkillLoadFactor(1.5);
                break;
            case Monte_Carlo:
                rules.setDiagonalActive(false);
                rules.setMinStraight(3);
                rules.setMinXOfAKind(Math.max(rules.rows, rules.columns) + 1);
                rules.setPointLimit(40);
                rules.setSkillLoadFactor(1);
                break;
        }

        rules.setRuleVariant(ruleVariant);
        return rules;
    }

    public double getSkillLoadFactor()
    {
        return skillLoadFactor;
    }

    public void setSkillLoadFactor(double skillLoadFactor)
    {
        this.skillLoadFactor = skillLoadFactor;
    }

    public boolean isDiagonal()
    {
        return diagonal;
    }

    public void setDiagonal(boolean diagonal)
    {
        this.diagonal = diagonal;
    }

    public int getAllowedStrikes()
    {
        return allowedStrikes;
    }

    public void setAllowedStrikes(int allowedStrikes)
    {
        this.allowedStrikes = allowedStrikes;
    }

    public RuleVariant getRuleVariant()
    {
        return ruleVariant;
    }

    public void setRuleVariant(RuleVariant ruleVariant)
    {
        this.ruleVariant = ruleVariant;
    }

    public boolean isTimeLimit()
    {
        return timeLimit;
    }

    public void setTimeLimit(boolean timeLimit)
    {
        this.timeLimit = timeLimit;
    }

    public int getTimeLimitInS()
    {
        return timeLimitInS;
    }

    public void setTimeLimitInS(int timeLimitInS)
    {
        this.timeLimitInS = timeLimitInS;
    }

    @Override
    public String toString()
    {
        return "Rules{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", lengthFactor=" + lengthFactor +
                ", diagonal=" + diagonal +
                ", numberOfDices=" + numberOfDices +
                ", minXOfAKind=" + minXOfAKind +
                ", minStraight=" + minStraight +
                ", pointLimit=" + pointLimit +
                ", gameEndPoints=" + gameEndPoints +
                '}';
    }

    public int getGameEndPoints()
    {
        return gameEndPoints;
    }

    public void setGameEndPoints(int gameEndPoints)
    {
        this.gameEndPoints = gameEndPoints;
    }

    public int getLengthFactor()
    {
        return lengthFactor;
    }

    public void setLengthFactor(int lengthFactor)
    {
        this.lengthFactor = lengthFactor;
    }

    public int getColumns()
    {
        return columns;
    }

    public int getRows()
    {
        return rows;
    }

    /**
     * Initializes the points for x of a kind.
     *
     * @param min    Minimum number of same dices for points.
     * @param max    Maximum number of same dices for points.
     * @param points Points multiplicator depending on the value of the dice.
     * @param factor The increase factor for each more same dice.
     */
    private void initXOfAKindPoints(int min, int max, int points, int factor)
    {
        this.xOfAKindPoints = new int[max + 1][this.numberOfDices + 1];
        int f = 1;
        for (int x = 0; x < max + 1; x++)
        {
            for (int dice = 0; dice < this.numberOfDices + 1; dice++)
            {
                int p;
                if (x < min || dice == 0)
                {
                    p = 0;
                }
                else
                {
                    p = points * dice * f;
                }
                this.xOfAKindPoints[x][dice] = p;
            }

            // increase factor
            f *= factor;
        }

        int[][] xPoints = {{0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 45, 20, 25, 30, 35, 40},
                {0, 155, 55, 75, 95, 115, 135},
                {0, 1000, 400, 500, 600, 700, 800}
        };

        xOfAKindPoints = xPoints;
    }


    /**
     * Initialize the straight points array. The first straight gives "points" points, for each dice more the points are doubled.
     *
     * @param points The first active straight gives "points" points, for each dice more the points are doubled.
     */
    public void initStraightPoints(int points)
    {
        this.straightPoints = new int[this.numberOfDices + 1][this.numberOfDices + 1];
        int factor = 1;
        for (int j = 0; j < this.numberOfDices + 1; j++)
        {
            for (int i = 0; i < this.numberOfDices + 1; i++)
            {
                //if (this.minStraight <= i)
                {
                    this.straightPoints[j][i] = points * factor;
                    // double the factor
                    factor *= 2;
                }
                //else
                {
                    //this.straightPoints[i] = 0;
                }
            }
        }


        int[][] sPoints = {{0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 30, 15, 20, 25, 0, 0},
                {0, 120, 40, 80, 0, 0, 0},
                {0, 500, 250, 0, 0, 0, 0}
        };

        straightPoints = sPoints;
    }

    public int getMinXOfAKind()
    {
        return this.minXOfAKind;
    }

    public void setMinXOfAKind(int minXOfAKind)
    {
        this.minXOfAKind = minXOfAKind;
    }

    public int getMaxLengthOfRow()
    {
        return maxLengthOfRow;
    }

    public int getXOfAKindPoints(int x, int value)
    {
        return this.xOfAKindPoints[x][value];
    }

    public boolean isDiagonalActive()
    {
        return diagonal;
    }

    public void setDiagonalActive(boolean b)
    {
        this.diagonal = b;
    }

    public int getMinStraight()
    {
        return this.minStraight;
    }

    public void setMinStraight(int minStraight)
    {
        this.minStraight = minStraight;
    }

    public int getStraightPoints(int length, int value)
    {
        return this.straightPoints[length][value];
    }

    public int getPoints(int length, int value, PointType type)
    {
        switch (type)
        {
            case Straight:
                return this.getStraightPoints(length, value);
            case XOfAKind:
                return this.getXOfAKindPoints(length, value);
        }

        return 0;
    }

    public int getPointLimit()
    {
        return pointLimit;
    }

    public void setPointLimit(int pointLimit)
    {
        this.pointLimit = pointLimit;
    }

    public void setStraightPoints(int[][] straightPoints)
    {
        this.straightPoints = straightPoints;
    }

    public void setxOfAKindPoints(int[][] xOfAKindPoints)
    {
        this.xOfAKindPoints = xOfAKindPoints;
    }

    public GameLength getGameLength()
    {
        return gameLength;
    }

    public void setGameLength(GameLength gameLength)
    {
        this.gameLength = gameLength;
    }

    public void calculateGameEndPointsAndStrikes()
    {
        this.gameEndPoints = lengthFactor * pointLimit;
        switch (gameLength)
        {
            case Short:
                allowedStrikes = 0;
                break;
            case Normal:
                allowedStrikes = 1;
                break;
            case Long:
                allowedStrikes = 2;
                break;
        }
    }
}
