//package player;
import java.util.*;

class Entity {
	public static int teamId; // # of busters * player
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
		this.id=0;
		this.x=x;
		this.y=y;
		this.type = 0;
		this.state = 0;
		this.value = 0;
	}
	public int  entDist (Entity b){
		return (int) Math.sqrt((this.x - b.x)*(this.x-b.x)+(this.y-b.y)*(this.y-b.y));
	}
	public void print(){
		if (this.type==teamId){
			System.err.printf("My buster is #d and is in %d %d,",type,this.id,this.x,this.y);
			if (this.state==1){
				System.err.printf("this buster is carrying ghost #%d\n",this.value);
			}else{
				System.err.printf("this buster isn't carrying any ghost\n");
			}
		}else{
			System.err.printf("This ghost is # %d and is in %d %d\n",type,this.id,this.x,this.y);
			if (this.value!=0){
				System.err.printf("This ghost is currenty targeted by %d buster(s)\n",this.value);
			}
		}
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
		int ghostCount; //# of ghosts in map
		int bustersPP; //player 0 or 1
		bustersPP = cin.nextInt();
		ghostCount = cin.nextInt();
		Entity.teamId = cin.nextInt();
		System.err.printf("%d %d %d\n",bustersPP,ghostCount,Entity.teamId);
		Entity base,dest;
		if (Entity.teamId==0){
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
				if (temp.type==Entity.teamId){
					buster[busters] = new Entity (temp);
					busters++;
				}else if (temp.type==-1){
					ghost[ghosts] = new Entity (temp);
					ghosts++;
				}
			}
			/*debug*/
			System.err.printf("# busters:%d\n",busters);
			for (int i = 0;i < busters;i++){
				buster[i].print();
			}
			System.err.printf("# ghosts:%d\n",ghosts);
			for (int i = 0;i < ghosts;i++){
				ghost[i].print();
			}
			/*debug*/
			//operations
			for (int i = 0;i < busters;i++){
				if (buster[i].state==1){
					if (buster[i].entDist(base)<1600){
						System.out.println("RELEASE\n");
					}else{
						System.out.printf("MOVE %d %d home %d\n",base.x,base.y,buster[i].value);
					}
				}
				else{
					boolean found=false;
					for (int j = 0;j < ghosts;j++){
						if (ghost[j].value!=0){ continue;}
						int dist = buster[i].entDist(ghost[j]);
						if (dist<1760){
							System.out.printf("BUST %d\n",ghost[j].id);
					    	found=true;
					    }else if (dist<2200){
					    	System.out.printf("MOVE %d %d, %d found\n",ghost[j].x,ghost[j].y,ghost[j].value);
					    	found=true;
						}
					}
					if (!found){
						System.out.printf("MOVE %d %d nobody\n",dest.x,dest.y);
					}
				}
			}
		}
	}
}
