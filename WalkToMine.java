package chris_RSBot;

import java.util.Random;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;
import org.powerbot.script.rt4.Movement;

public class WalkToMine extends Task<ClientContext>
{
	public Tile walkingPath[] = {new Tile(3284, 3366), new Tile(3286,3371), new Tile(3290, 3375),		//Create bots approx walking path from mine to bank
			 new Tile(3294, 3380), new Tile(3294, 3388), new Tile(3293, 3393), new Tile(3292, 3399),
			 new Tile(3291, 3405), new Tile(3288, 3411), new Tile(3286, 3417), new Tile(3282, 3423),
			 new Tile(3275, 3428), new Tile(3270, 3428), new Tile(3264, 3428), new Tile(3257, 3428),
			 new Tile(3254, 3424), new Tile(3253, 3421)};
	
	public final int ironDepositId[] = {13446, 13444, 13445};											//Ids for iron deposits
	public final int usedIronDepositId[] = {13459, 13461, 13460};										//Ids for used iron deposits
	private Movement move = new Movement(ctx);
	 private TilePath path = move.newTilePath(walkingPath).reverse();									//Create the walking path in reverse, for walking to the mine
	 
	public WalkToMine(ClientContext ctx)
	{
		super(ctx);
	}
	
	public boolean activate()																			//Activate if inventory has count 1 or less, player is not animated and player is at the bank/
	{
		return 	ctx.inventory.count() <= 1
				&& ctx.players.local().animation() == -1
				&& ctx.objects.select().id(usedIronDepositId).within(5).isEmpty();
	}
	
	public void execute()
	{
		walkToMine();																					////Call Walk to Mine			
	}
	
	public void walkToMine()																			//Walks to the mine
	{		 
		 path.traverse();		
	}
}