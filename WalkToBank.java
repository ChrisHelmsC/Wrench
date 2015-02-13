package chris_RSBot;

import org.powerbot.script.Tile;
import java.util.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;
import org.powerbot.script.rt4.Movement;

public class WalkToBank extends Task<ClientContext>
{ 	 
	 public Tile walkingPath[] = {new Tile(3284, 3366), new Tile(3286,3371), new Tile(3290, 3375),
			 new Tile(3294, 3380), new Tile(3294, 3388), new Tile(3293, 3393), new Tile(3292, 3399),
			 new Tile(3291, 3405), new Tile(3288, 3411), new Tile(3286, 3417), new Tile(3282, 3423),
			 new Tile(3275, 3428), new Tile(3270, 3428), new Tile(3264, 3428), new Tile(3257, 3428),
			 new Tile(3254, 3424), new Tile(3253, 3421)};
	 public int[] bankBoothId = {11748};
	 private Movement move = new Movement(ctx);
	 private TilePath path = move.newTilePath(walkingPath);
	 
	public WalkToBank(ClientContext ctx)
	{
		super(ctx);
	}
	
	public boolean activate()
	{
		return ctx.inventory.count() == 28
				&& ctx.objects.select().id(bankBoothId).within(5).isEmpty();
	}
	
	
	public void execute()
	{		
		 path.traverse();		
	}
}
