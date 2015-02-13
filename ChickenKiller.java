package chris_RSBot;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name ="ChickenKiller", description ="Kills Chickens")

public class ChickenKiller extends PollingScript<ClientContext>
{
	private List<Task> taskList = new ArrayList<Task>();
	
	public void start()
	{
		taskList.addAll(Arrays.asList(new Attack(ctx)));
	}
	
	public void poll()
	{
		for(Task task : taskList)
		{
			if(task.activate())
			{
				task.execute();
			}
		}
	}
}
