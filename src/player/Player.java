//package player;
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
    public static void main(String[] args) {
        Scanner cin  = new Scanner(System.in);
		int teamId; // # of busters * player
		int ghostCount; //# of ghosts in map
		int bustersPP; //player 0 or 1
		bustersPP = cin.nextInt();
		ghostCount = cin.nextInt();
		teamId = cin.nextInt();
		System.err.printf("%d %d %d\n",bustersPP,ghostCount,teamId);
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
			System.err.printf("# busters:%d\n# ghosts:%d\n",busters,ghosts);
			/*debug*/
			//operations
			for (int i = 0;i < busters;i++){
				if (buster[i].state==1){
					if (buster[i].entDist(base)<1600){
						System.out.println("RELEASE\n");
					}else{
						System.out.printf("MOVE %d %d\n",base.x,base.y);
					}
				}
				boolean found=false;
		    	for (int j = 0;j < ghosts;j++){
		    		if (ghost[j].value!=0){ continue;}
					int dist = buster[i].entDist(ghost[j]);
					if (dist<1760){
			    		System.out.printf("BUST %d\n",ghost[j].id);
				    	found=true;
				    }else if (dist<2200){
				    	System.out.printf("MOVE %d %d\n",ghost[j].x,ghost[j].y);
				    	System.err.printf("%d %d\n",ghost[j].id,dist);
				    	found=true;
					}
				}
				if (!found){
					System.out.printf("MOVE %d %d\n",dest.x,dest.y);
				}
			}
		}
	}
}
