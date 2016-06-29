#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

//id,x,y,type,state,value
class Entity {
	public:
		int id;
		int x;
		int y;
		int type;
		int state;
		int value;
		int entDist(Entity * b){
			return sqrt((this->x - b->x)*(this->x-b->x)+(this->y-b->y)*(this->y-b->y));
		}
		void print(){
			cerr<<this->id<<" "<<this->x<<" "<<this->y<<" "<<this->type<<" "<<this->state<<" "<<this->value<<endl;
		}
		Entity (int a, int b):x(a),y(b){}
		Entity () {}
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
    int bustersPP,ghostCount,teamId; // # of busters * player ; # of ghosts in map; player 0 or 1;
    cin >> bustersPP >> ghostCount >> teamId; cin.ignore();
    cerr<<bustersPP<<" "<<ghostCount<<" "<<teamId<<endl;
	int baseX,baseY,destX,destY;//assign base and (first try) direction
	Entity base,dest;
	if (teamId==0){
		base.x=base.y=0;
		dest.x=16001;
		dest.y=9001;
	}else{
		base.x=16001;
		base.y=9001;
		dest.x=dest.y=0;
	}
	
    // game loop
    while (1) {
		vector <Entity> buster;
		vector <Entity> ghost;
        Entity * temp = new Entity ();
		int entN; // the number of buster and ghosts visible to you
        cin >> entN; cin.ignore();
        for (int i = 0; i < entN; i++){
            cin>>temp->id>>temp->x>>temp->y>>temp->type>>temp->state>>temp->value;cin.ignore();
			if (temp->type==teamId) buster.push_back(*temp);
			else ghost.push_back(*temp);
        }
		
        for (int i = 0; i < bustersPP; i++) {
			if(buster[i].state==1){
				if(buster[i].entDist(base)<1600)
					cout<<"RELEASE"<<endl;
				else
					cout<<"MOVE "<<base.x<<" "<<base.y<<endl;
			}
			bool found=false;
			for (int j=0;j<ghostCount||found;j++){
				if(ghost[i].value!=0) continue;
				int dist = buster[i].entDist(ghost[j])
				if (dist<1760){
					cout<<"BUST "<<ghost[i].id<<endl;
					found=true;
				}
				else if(dist<2200){
					cout<<"MOVE "<<ghost[i].x<<" "<<ghost[i].y<<endl;
					cerr<<ghost[i]->id<<endl;
					found=true;
				}
			}
			if(!found){//if no ghost near the buster, continue to dest
				cout<<"MOVE "<<dest.x<<" "<<dest.y<<endl;
			}
			
            // MOVE x y | BUST id | RELEASE
        }
    }
}