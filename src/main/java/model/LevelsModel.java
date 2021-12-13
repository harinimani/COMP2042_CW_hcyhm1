package model;

import controller.BrickController;
import controller.WallController;

import java.awt.*;

/**
 * LevelsModel Class handles all the implementation based on the different levels in the game.
 *
 * @author Harini Manikandan
 */
public class LevelsModel {

    private static final int LEVELS_COUNT = 7;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int BONUS = 4;
    private static final int TIMESAVER = 5;
    private static final int SLOW = 6;

    private BrickController[][] levels;
    private int level;

    private WallController wall;

    public LevelsModel(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, WallController wall) {
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        this.wall = wall;
    }

    /**
     * makeSingleTypeLevel is a Private Method implements a wall with only 1 type of brick.
     * @param drawArea          rectangle game area
     * @param brickCnt          number of bricks
     * @param lineCnt           number of rows on the wall
     * @param brickSizeRatio    brick size scale
     * @param type              type of brick
     * @return          returns a single brick type wall.
     */
    private BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrickModel(p,brickSize);
        }
        return tmp;

    }

    /**
     * makeChessBoard level is a Private Method implements a wall with multiple types of brick.
     * Creates a Chessboard like wall.
     * @param drawArea          rectangle game area
     * @param brickCnt          number of bricks
     * @param lineCnt           number of rows on the wall
     * @param brickSizeRatio    brick size scale
     * @param typeA             type of first brick
     * @param typeB             type of second brick
     * @return          returns a Chessboard wall.
     */
    private BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * makeLevels is a Private Method that help set the different levels in the game.
     * @param drawArea              rectangle game area
     * @param brickCount            number of bricks
     * @param lineCount             number of rows of brick on wall
     * @param brickDimensionRatio   brick size scale
     * @return                      returns levels
     */
    private BrickController[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        BrickController[][] tmp = new BrickController[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,BONUS);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,TIMESAVER);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,SLOW);
        return tmp;
    }

    /**
     * makeBrick is a Private Method that creates the respective brick based on the brick type.
     * @param point     position of brick
     * @param size      size of brick
     * @param type      type of brick
     * @return          returns a brick
     */
    private BrickController makeBrick(Point point, Dimension size, int type){
        BrickController out;
        switch(type){
            case CLAY:
                out = new ClayBrickModel(point,size);
                break;
            case STEEL:
                out = new SteelBrickModel(point,size);
                break;
            case CEMENT:
                out = new CementBrickModel(point, size);
                break;
            case BONUS:
                out = new BonusBrickModel(point, size);
                break;
            case TIMESAVER:
                out = new TimesaverBrickModel(point, size);
                break;
            case SLOW:
                out = new SlowBrickModel(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

    /**
     * If there exits another level, nextLevel() method initializes the next level.
     */
    public void nextLevel(){
        if(hasLevel()){
            wall.setBricks(levels[level++]);
            wall.setBrickCount(wall.getBricks().length);;
        }
    }

    /**
     * hasLevel() checks if there is a next level.
     * @return      returns a boolean value denoting if another level exists or not
     */
    public boolean hasLevel(){
        return level < levels.length;
    }


    /**
     * getLevel() Method is a Getter method that gets the current level. Encapsulation
     * @return      returns the game level
     */
    public int getLevel() {
        return level;
    }

}
