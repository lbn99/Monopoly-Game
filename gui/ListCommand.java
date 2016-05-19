public class ListCommand() extends NetworkListenerAdapter
{
	public static String COMMAND = "LIST";
	
	public void process(String message, IClient client)
	{
		if(isCommand(message, COMMAND))
		{
			String[] str = message.split(" ", 4);
			String list = getName(str[3]);
			j.replaceRange(list, 0, j.getText().length());
		}
	}
	
	public String getName(String str)
	{
		String name = "";
		if(str.length() > 0)
		{
			String[] s = str.split(" ", 2);
			String[] prop = s[1].substring(0, Integer.parseInt(s[0])).split("_");
			int x = prop.length();
			while(x > 0)
			{
				
				x--;
			}
			s[1] = s[1].substring(Integer.parseInt(s[0]));
			name += getName(s[1]);
			return name;
		}
		return "";
	}
}