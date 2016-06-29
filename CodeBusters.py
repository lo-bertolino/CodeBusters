import sys
import math
import numpy
# Send your busters out into the fog to trap ghosts and bring them home!
def dist (a,b):
    return int(math.sqrt((a[1]-b[1])**2+(a[2]-b[2])**2))
busters_per_player = int(input())  # the amount of busters you control
ghost_count = int(input())  # the amount of ghosts on the map
my_team_id = int(input())  # if this is 0, your base is on the top left of the map, if it is one, on the bottom right
my_buster=[]
ghost=[]
if my_team_id==0:
    home_x=home_y=0
    p_x=16001
    p_y=9001
else:
    home_x=16001
    home_y=9001
    p_x=p_y=0
entity_id=0# 0 entity_id: buster id or ghost id
pos=(1,2)# 1,2 x,y: position of this buster / ghost
e_type=3# 3 entity_type: the team id if it is a buster, -1 if it is a ghost.
state=4# 4 state: For busters: 0=idle, 1=carrying a ghost.
value=5# 5 value: For busters: Ghost id being carried. For ghosts:  of busters attempting to trap

# game loop
while True:
    entities_n = int(input())  # the number of busters and ghosts visible to you
    entity=[]
    for i in range(entities_n):
        element=[int(j) for j in input().split()]
        if element[e_type]==my_team_id:
            my_buster.append(element)
        if element[e_type]==-1:
            ghost.append(element)
    #print("busters:",my_buster,file=sys.stderr)
    #print("ghosts:",ghost,file=sys.stderr)
    for buster in my_buster:
        if buster[state]==1:
            if dist(buster,[0,home_x,home_y])<1600:
                print("RELEASE")
            else:
                print("MOVE",home_x,home_y)
        elif len(ghost)!=0:
            for coso in ghost:
                if dist(buster,coso)<1760 and coso[value]==0:
                    print("BUST",coso[entity_id])
                else:
                    print("MOVE",coso[1],coso[2])
                    print(coso[0],file=sys.stderr)
        else:#elif buster[state]==0:
            #angle=90/len(my_buster)
            print("MOVE",p_x,p_y)
        #print(,file=sys.stderr)

        # MOVE x y | BUST id | RELEASE
