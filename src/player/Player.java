package player;
import java.util.*;

class Entity {
	public int id;
	public int x;
	public int y;
	public int type;
	public int state;
	public int value;
	public Entity (){
	}
	public Entity (Entity copied){
		this.id = copied.id;
		this.x = copied.x;
		this.y = copied.y;
		this.type = copied.type;
		this.state = copied.state;
		this.value = copied.value;
	}
	public Entity (int x,int y){
		this.x=x;
		this.y=y;
	}
	public Entity (int id, int x, int y, int type, int state, int value){
		this.id = id;
		this.x = x;
		this.y = y;
		this.type = type;
		this.state = state;
		this.value = value;
	}
	public int  entDist (Entity b){
		return (int) Math.sqrt((this.x - b.x)*(this.x-b.x)+(this.y-b.y)*(this.y-b.y));
	}
	public void print(){
		System.err.printf("%d %d %d %d %d %d ",this.id,this.x,this.y,this.type,this.state,this.value);
	}
	public void copy(Entity copied){
		this.id = copied.id;
		this.x = copied.x;
		this.y = copied.y;
		this.type = copied.type;
		this.state = copied.state;
		this.value = copied.value;
	}
}

class Player {
    public static void main(String args[]) {
        Scanner cin  = new Scanner(System.in);
		int teamId; // # of busters * player
		int ghostCount; //# of ghosts in map
		int bustersPP; //player 0 or 1
		bustersPP = cin.nextInt();
		ghostCount = cin.nextInt();
		teamId = cin.nextInt();
		System.err.printf("%d %d %d ",bustersPP,ghostCount,teamId);
		Entity base,dest;
		if (teamId==0){
			base = new Entity(0,0);
			dest = new Entity(16001,9001);
		}else{
			base = new Entity(16001,9001);
			dest = new Entity(0,0);
		}
		
		//game loop
		while (true){			
			Entity[] buster = new Entity[bustersPP];
			int busters=0;
			Entity[] ghost = new Entity[ghostCount];
			int ghosts=0;
			Entity temp = new Entity ();
			int entN;
			entN = cin.nextInt();
			//input
			for (int i = 0;i < entN;i++){
				temp.id=cin.nextInt();
				temp.x=cin.nextInt();
				temp.y=cin.nextInt();
				temp.type=cin.nextInt();
				temp.state=cin.nextInt();
				temp.value=cin.nextInt();
				if (temp.type==teamId){
					buster[busters] = new Entity (temp);
					busters++;
				}else if (temp.type==-1){
					ghost[ghosts] = new Entity (temp);
					ghosts++;
				}
			}
			/*debug*/
			System.err.printf("# busters:%d\n#ghosts:%d",busters,ghosts);
			/*debug*/
			//operations
			for (Entity element:buster){
				if (element.state==1){
					if (element.entDist(base)<1600){
						System.out.println("RELEASE");
					}else{
						System.out.printf("MOVE %d %d",base.x,base.y);
					}
				}
				boolean found=false;
		    	for (Entity coso:ghost){
		    		if (coso.value!=0){ continue;}
					int dist = element.entDist(coso);
					if (dist<1760){
			    		System.out.printf("BUST %d",coso.id);
				    	found=true;
				    }else if (dist<2200){
				    	System.out.printf("MOVE %d %d",coso.x,coso.y);
				    	System.err.printf("%d %d",coso.id,dist);
				    	found=true;
					}
				}
				if (!found){
					System.out.printf("MOVE %d %d",dest.x,dest.y);
				}
			}
		}
	}
}
