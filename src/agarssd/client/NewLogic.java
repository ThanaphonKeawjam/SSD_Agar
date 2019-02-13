package agarssd.client;

import agarssd.model.Item;
import agarssd.model.MoveCommand;
import agarssd.model.Player;
import agarssd.model.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewLogic implements  LogicStrategy{

    private Random random = new Random();
    private long lastCommand;

    @Override
    public MoveCommand getNextMoveCommand(World world, Player myPlayer) {
        if(world == null) {
            return null;
        }

        long diff = System.currentTimeMillis() - lastCommand;
        if(diff < 5000) {
            return null;
        }
        lastCommand = System.currentTimeMillis();
        MoveCommand command = new MoveCommand();

        float aX1 = myPlayer.positionX + 100;
        float aX2 = myPlayer.positionX - 100;
        float aY1 = myPlayer.positionY + 100;
        float aY2 = myPlayer.positionY - 100;
        List<Item> near = new ArrayList<Item>();
        float min = 10000;
        float x = 0, y = 0;

        for(Item i : world.items){
            float iX = i.positionX;
            float iY = i.positionY;

            if( (iX <= aX1 && iX >= aX2) && (iY <= aY1 && iY >= aY2)) near.add(i);
        }

        for(Item i : near){
            float dis = i.distance(myPlayer.positionX, myPlayer.positionY);
            if(dis < min){
                min = dis;
                x = i.positionX;
                y = i.positionY;
            }
        }

        command.toX = x;
        command.toY = y;

//      command.toX = random.nextInt(world.size);
//      command.toY = random.nextInt(world.size);

        return command;
    }
}
