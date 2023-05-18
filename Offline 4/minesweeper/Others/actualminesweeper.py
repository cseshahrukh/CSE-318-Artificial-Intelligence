import itertools
import random


class Minesweeper():
    """
    Minesweeper game representation
    """

    def __init__(self, height=8, width=8, mines=8):

        # Set initial width, height, and number of mines
        self.height = height
        self.width = width

        #self.mines ke array hisebe declare korlam, pair er list, pore pair add korbo 
        self.mines = set()

        # Initialize an empty field with no mines
        self.board = []
        for i in range(self.height):
            row = []
            for j in range(self.width):
                row.append(False)
                #false means no mine
            self.board.append(row)

        # Add mines randomly
        while len(self.mines) != mines:
            i = random.randrange(height)
            j = random.randrange(width)

            #jodi mine na thake tahole mine add korchi 
            if not self.board[i][j]:
                self.mines.add((i, j))
                self.board[i][j] = True

        #uporer jinis gula to player janbe na 
        # At first, player has found no mines
        self.mines_found = set()

    def print(self):
        """
        Prints a text-based representation
        of where mines are located.
        """
        for i in range(self.height):
            print("--" * self.width + "-")
            for j in range(self.width):
                if self.board[i][j]:
                    print("|X", end="")
                else:
                    print("| ", end="")
            print("|")
        print("--" * self.width + "-")

    #cell hocche most probably ekta pair e 
    #eita true naki false eita return korbo, true mane mine ahce 
    def is_mine(self, cell):
        i, j = cell
        return self.board[i][j]

    def nearby_mines(self, cell):
        """
        Returns the number of mines that are
        within one row and column of a given cell,
        not including the cell itself.
        """

        # Keep count of nearby mines
        count = 0

        #cell hocche ekta pair, (row, col) mane 0th element e row ache ar 1st element e col ache 
        #range(1,5) mane 1, 2, 3, 4 may be
        # Loop over all cells within one row and column

        # Eikhane count ta change kora lagbe 
        for i in range(cell[0] - 1, cell[0] + 2):
            for j in range(cell[1] - 1, cell[1] + 2):

                # Ignore the cell itself
                if (i, j) == cell:
                    continue

                #change by Shahrukh
                #if i!=cell[0] and j!=cell[1]:
                    #continue
                #change done by Shahrukh

                # Update count if cell in bounds and is mine
                if 0 <= i < self.height and 0 <= j < self.width:
                    if self.board[i][j]:
                        count += 1

        return count

    def won(self):
        """
        Checks if all mines have been flagged.
        """
        return self.mines_found == self.mines


class Sentence():
    """
    Logical statement about a Minesweeper game
    A sentence consists of a set of board cells,
    and a count of the number of those cells which are mines.
    """

    def __init__(self, cells, count):
        self.cells = set(cells)
        self.count = count

    def __eq__(self, other):
        return self.cells == other.cells and self.count == other.count

    def __str__(self):
        return f"{self.cells} = {self.count}"

    def known_mines(self):
        """
        Returns the set of all cells in self.cells known to be mines.
        """
        #change by Shahrukh
        #amar kache dhorlam 4 ta cell ache
        #and amar minecount o jodi 4 hoy
        #tahole ami sure je ei cell gulay sob mine ache 
        
        #if len(self.cells) == self.count and self.count !=0:
        if len(self.cells) == self.count:
            return self.cells
        
        #na hole emni ekta empty set return korchi 
        #return set()
        return None
        #change done by Shahrukh
        

    def known_safes(self):
        """
        Returns the set of all cells in self.cells known to be safe.
        """
        #mine count minus korte korte sob bujhe gechi
        #tokhon amar kache joto gula unknown cell ache ami bolbo sob gulai safe 
        if self.count == 0:
            return self.cells
        #return set()
        return None

    def mark_mine(self, cell):
        """
        Updates internal knowledge representation given the fact that
        a cell is known to be a mine.
        """
        #cell e initially sobai chilo
        #ekhon ami jene gechi je parameter cell e mine ache
        #tai list theke remove kore dicchi 
        if cell in self.cells:
            self.cells.remove(cell)
            
            #unkonwn mine er count 1 komlo 
            self.count=self.count-1
        

    def mark_safe(self, cell):
        """
        Updates internal knowledge representation given the fact that
        a cell is known to be safe.
        """
        #ami jenechi eita safe tai delete kore dilam
        if cell in self.cells:
            self.cells.remove(cell)
       
        


#eita ektu hard ache 
class MinesweeperAI():
    """
    Minesweeper game player
    """

    def __init__(self, height=8, width=8):

        # Set initial height and width
        self.height = height
        self.width = width

        # Keep track of which cells have been clicked on
        self.moves_made = set()

        # Keep track of cells known to be safe or mines
        self.mines = set()
        self.safes = set()

        # List of sentences about the game known to be true
        self.knowledge = []

    def mark_mine(self, cell):
        """
        Marks a cell as a mine, and updates all knowledge
        to mark that cell as a mine as well.
        """
        self.mines.add(cell)
        for sentence in self.knowledge:
            sentence.mark_mine(cell)

    def mark_safe(self, cell):
        """
        Marks a cell as safe, and updates all knowledge
        to mark that cell as safe as well.
        """
        self.safes.add(cell)
        for sentence in self.knowledge:
            sentence.mark_safe(cell)


    #amake nicher 3 ta function implement kora lagbe 
    def add_knowledge(self, cell, count):
        """
        Called when the Minesweeper board tells us, for a given
        safe cell, how many neighboring cells have mines in them.

        This function should:
            1) mark the cell as a move that has been made
            2) mark the cell as safe
            3) add a new sentence to the AI's knowledge base
               based on the value of `cell` and `count`
            4) mark any additional cells as safe or as mines
               if it can be concluded based on the AI's knowledge base
            5) add any new sentences to the AI's knowledge base
               if they can be inferred from existing knowledge
        """
        #change by Shahrukh
        self.moves_made.add(cell)
        self.mark_safe(cell)

        underterminedCells = []
        countMines=0
        #ager row oi row and nicher row
        for i in range(cell[0]-1, cell[0]+2):
            for j in range(cell[1]-1, cell[1]+2):
                if (i,j) in self.mines:
                    countMines=countMines+1
                #first 2 ta condition hocche valid row and col kina oita check
                if 0<=i < self.height and 0<=j<self.width and (i,j) not in self.safes and (i,j) not in self.mines:
                    underterminedCells.append((i,j))

        newSentence = Sentence(underterminedCells, count-countMines)

        self.knowledge.append(newSentence)

        #now doing the task 5 of this function
        for sentence in self.knowledge:
            if sentence.known_mines():
                for cell in sentence.known_mines().copy():
                    self.mark_mine(cell)

            if sentence.known_safes():
                for cell in sentence.known_safes().copy():
                    self.mark_safe(cell)

        for sentene in self.knowledge:
            #check if subset or not 
            if newSentence.cells.issubset(sentence.cells) and sentence.count > 0 and newSentence.count > 0 and newSentence != sentence:
                newSubset = sentence.cells.difference(newSentence.cells)
                newSentenceSubset = Sentence(list(newSubset), sentence.count-newSentence.count)
                self.knowledge.append(newSentenceSubset)

    def make_safe_move(self):
        """
        Returns a safe cell to choose on the Minesweeper board.
        The move must be known to be safe, and not already a move
        that has been made.

        This function may use the knowledge in self.mines, self.safes
        and self.moves_made, but should not modify any of those values.
        """
        #change by shahrukh
        #self.safes er moddhe safe move gula ache
        #ekhon oi safe move already dewa hole oitake self.moves_made theke remove kora hoy
        #so check kore nicchi je already self.moves_made e ache kina 
        for nowCell in self.safes:
            #changing to not 
            if nowCell not in self.moves_made:
                return nowCell
        return None

    def make_random_move(self):
        """
        Returns a move to make on the Minesweeper board.
        Should choose randomly among cells that:
            1) have not already been chosen, and
            2) are not known to be mines
        """
        #change by shahrukh
        mypossibleMoves = []
        for i in range(self.height):
            for j in range(self.width):
                if (i,j) not in self.moves_made and (i,j) not in self.mines:
                    mypossibleMoves.append((i,j))
        
        #mypossibleMoves er moddhe jekono ekta randomly choose korbo
        if len(mypossibleMoves) !=0:
            return random.choice(mypossibleMoves)
        else:
            return None
