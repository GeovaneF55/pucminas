from copy import deepcopy

class eight_puzzle:
    def __init__ (self, inicial, pai):
        self.tabuleiro = inicial
        self.pai = pai
        self.f = 0
        self.g = 0
        self.h = 0

    def manhattan(self):
        inc = 0
        h = 0
        for i in range(3):
            for j in range(3):
                h += abs(inc-self.tabuleiro[i][j])
            inc += 1
        return h


    def objetivo(self):
        inc = 0
        for i in range(3):
            for j in range(3):
                if self.tabuleiro[i][j] != inc:
                    return False
                inc += 1
        return True

    def __eq__(self, outro):
        return self.tabuleiro == outro.tabuleiro

def move_function(curr):
    curr = curr.tabuleiro
    for i in range(3):
        for j in range(3):
            if curr[i][j] == 0:
                x, y = i, j
                break
    q = []
    if x-1 >= 0:
        b = deepcopy(curr)
        b[x][y]=b[x-1][y]
        b[x-1][y]=0
        succ = eight_puzzle(b, curr)
        q.append(succ)
    if x+1 < 3:
        b = deepcopy(curr)
        b[x][y]=b[x+1][y]
        b[x+1][y]=0
        succ = eight_puzzle(b, curr)
        q.append(succ)
    if y-1 >= 0:
        b = deepcopy(curr)
        b[x][y]=b[x][y-1]
        b[x][y-1]=0
        succ = eight_puzzle(b, curr)
        q.append(succ)
    if y+1 < 3:
        b = deepcopy(curr)
        b[x][y]=b[x][y+1]
        b[x][y+1]=0
        succ = eight_puzzle(b, curr)
        q.append(succ)

    return q

def best_fvalue(openList):
    f = openList[0].f
    index = 0
    for i, item in enumerate(openList):
        if i == 0: 
            continue
        if(item.f < f):
            f = item.f
            index  = i

    return openList[index], index

def AStar(start):
    openList = []
    closedList = []
    openList.append(start)

    while openList:
        current, index = best_fvalue(openList)
        if current.objetivo():
            return current
        openList.pop(index)
        closedList.append(current)

        X = move_function(current)
        for move in X:
            ok = False   #checking in closedList
            for i, item in enumerate(closedList):
                if item == move:
                    ok = True
                    break
            if not ok:              #not in closed list
                newG = current.g + 1 
                present = False

                #openList includes move
                for j, item in enumerate(openList):
                    if item == move:
                        present = True
                        if newG < openList[j].g:
                            openList[j].g = newG
                            openList[j].f = openList[j].g + openList[j].h
                            openList[j].pai = current
                if not present:
                    move.g = newG
                    move.h = move.manhattan()
                    move.f = move.g + move.h
                    move.pai = current
                    openList.append(move)

    return None

def print_tabuleiro(tabuleiro):
    for row in tabuleiro:
        print(row)
    print('\n')

if __name__ == "__main__":
    #start = eight_puzzle([[2,3,6],[0,1,8],[4,5,7]], None)
    start = eight_puzzle([[5,2,8],[4,1,7],[0,3,6]], None)
    # start = eight_puzzle([[0,1,2],[3,4,5],[6,7,8]], None)
    #start = eight_puzzle([[1,2,0],[3,4,5],[6,7,8]], None)
    result = AStar(start)
    noofMoves = 0

    if(not result):
        print ("No solution")
    else:
        t=result.pai
        while t:
            noofMoves += 1
            print('passo: ', noofMoves)
            print_tabuleiro(t.tabuleiro)
            t=t.pai
        print('resultado: ')
        print_tabuleiro(result.tabuleiro)
    print ("Length: " + str(noofMoves))