package chris_RSBot;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Movement;

public class RunFromRat extends Task<ClientContext>
{ 	 
	
	private Tile[] dangerTile = {new Tile(3283, 3367), new Tile(3283, 3368), new Tile(3283, 3369), new Tile(3283, 3370),		//Tiles that are in reach of rat
			new Tile(3282, 3367), new Tile(3289, 3369), new Tile(3288, 3368)};
	private Tile safeSpot = new Tile(3287, 3363);																				//Approx tile to run to if rat attacks
	private final int numTiles = 7;																								//Number of danger tiles
	
	public RunFromRat(ClientContext ctx)
	{
		super(ctx);
	}
	
	public boolean activate()																									//activate if rat is attacking and player is on a danger tile
	{
		return ctx.players.local().inCombat()
		&& onDangerTile(ctx);
	}
	
	
	public void execute()																										//step to safe spot if activated
	{		
		Movement move = new Movement(ctx);
		move.step(safeSpot);
	}
	
	private boolean onDangerTile(ClientContext ctx)																				//returns true if player is on a dangerTile
	{
		boolean onDangerTile = false;					//Create DangerTile boolean
		Tile playerTile = ctx.players.local().tile();	//Get current player tile
		for(int i = 0; i<numTiles; i++)					//Iterate through dangerTiles
		{
			if(dangerTile[i].compareTo(playerTile)==0)	//if playerTile matches dangerTile, set boolean to true
			{
				onDangerTile = true;			
			}
		}
		return onDangerTile;							//return boolean
	}
}