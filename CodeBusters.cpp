#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

//id,x,y,type,state,value
class Entity {
public:
    static int teamId;
    int id;
	int x;
	int y;
	int type;
	int state;
	int value;
	Entity (int a, int b):x(a),y(b){}
	Entity () {}
    Entity (Entity copied) {
        this.id = copied.id;
		this.x = copied.x;
		this.y = copied.y;
		this.type = copied.type;
		this.state = copied.state;
		this.value = copied.value;
    }
    int entDist(Entity b){
		return sqrt((this->x - b.x)*(this->x-b.x)+(this->y-b.y)*(this->y-b.y));
	}
	void print(){
        if (this->type==teamId){
			cerr<<"My buster is "<<this->id<<" and is in "<<this->x<<" "<<this.y<<",";
			if (this->state==1){
				cerr<<"this buster is carrying ghost #"<<this->value<<endl;
			}else{
				cerr<<"this buster isn't carrying any ghost"<<endl;
			}
		}else{
			cerr<<"This ghost is #"this->id<<" and is in "<<this->x<<" "<<this.y<<",";
			if (this.value!=0){
				cerr<<"This ghost is currently targeted by "<<this->value<<" buster(s)"<<endl;
			}
		}
	}
	void copy (Entity copied) {
		this->id=copied.id;
		this->x=copied.x;
		this->y=copied.y;
		this->type=copied.type;
		this->state=copied.state;
		this->value=copied.value;
        }
};

int main(){
    int bustersPP,ghostCount; // # of busters * player ; # of ghosts in map; player 0 or 1;
    cin >> bustersPP >> ghostCount >> Entity.teamId; cin.ignore();
    cerr<<bustersPP<<" "<<ghostCount<<" "<<Entity.teamId<<endl;
	int baseX,baseY,destX,destY;//assign base and (first try) direction
	Entity base,dest;
	if (Entity.teamId==0){
		base = new Entity (0,0);
		dest = new Entity (16001,9001);
	}else{
		base = new Entity (16001,9001);
		dest = new Entity (0,0);
	}
	
    // game loop
    while (1) {
		Entity buster[bustersPP];
		Entity ghost[ghostCount];
        Entity temp = new Entity ();
        int busters=0,ghosts=0,entN;
        cin >> entN; cin.ignore();
        //input
        for (int i = 0; i < entN; i++){
            cin>>temp.id>>temp.x>>temp.y>>temp.type>>temp.state>>temp.value;cin.ignore();
			if (temp.type==Entity.teamId) buster[busters]=new Entity(temp);
            else if(temp.type==-1) ghost[ghosts]=new Entity(temp);
            
        }
        /*debug*/
		cerr<<"# busters:"<<busters<<endl;
		for (int i = 0;i < busters;i++){
			buster[i].print();
		}
		cerr<<"# ghosts:"ghosts<<endl;
		for (int i = 0;i < ghosts;i++){
			ghost[i].print();
		}
		/*debug*/
        //operations
        // MOVE x y | BUST id | RELEASE
 		for (int i = 0;i < busters;i++){
			if (buster[i].state==1){
				if (buster[i].entDist(base)<1600){
					cerr<<"RELEASE"<<endl;
				}else{
					cerr<<"MOVE "<<base.x<<" "<<base.y<<" home " << buster[i].value<<endl;
				}
			}
			else{
				bool found=false;
				for (int j = 0;j < ghosts;j++){
					if (ghost[j].value!=0){continue;}
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

