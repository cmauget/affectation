import random
import numpy as np
from tqdm import tqdm

def gen(m,n):
    
    matrix = [[random.randint(0, 1) for _ in range(n)] for _ in range(m)]
    col_sum = np.zeros(8)

    for i in range(m):
        row_sum = sum(matrix[i])
        for j in range(n):
            if row_sum > 4:
                if matrix[i][j]==1:
                    matrix[i][j] = 0
                    row_sum -= 1
            elif row_sum < 4:
                if matrix[i][j]==0:
                    matrix[i][j] = 1
                    row_sum += 1

    for j in range(n):
        col_sum[j] = sum([matrix[i][j] for i in range(m)])

    i=0

    while np.amax(col_sum) > 10 and i < m:
        
        ind= np.argmin(col_sum)
        ind2 = np.argmax(col_sum)

        if np.amax(col_sum) > 10 and matrix[i][ind2]==1 and matrix[i][ind]==0:
            matrix[i][ind]=1
            matrix[i][ind2]=0
            col_sum[ind2] -= 1
            col_sum[ind] += 1

        i += 1

    return matrix

def cout(matrix, matrix_cout):

    Nc=8
    cout=0
    col_sum = np.zeros(len(matrix[0]))
    for j in range(len(matrix[0])):
        for i in range(len(matrix)):
            val = matrix[i][j]*matrix_cout[i][j]
            if val == -1:
                cout=cout+10*pow(Nc,2)
            else:
                cout=cout+pow(val,2)

    for j in range(len(matrix[0])):
        col_sum[j] = sum([matrix[i][j] for i in range(len(matrix))])
        if 0<col_sum[j]<5:
            cout=cout+10000
    #print("col sum : ")
    #print(col_sum)
    return cout


def mutation(matrix_global):

    matrix_prout = matrix_global.copy()

    ind1 = random.randint(0,len(matrix_prout)-1)
    ind2 = random.randint(0,len(matrix_prout[0])-1)

    ind1=0
    ind2=4

    i=0
    test=True
    if matrix_prout[ind1][ind2]==0:
        while test:
            if matrix_prout[ind1][i]==1:
                matrix_prout[ind1][ind2]=1
                matrix_prout[ind1][i]=0
                test=False
            i+=1

    else:
        while test:
            if matrix_prout[ind1][i]==0:
                matrix_prout[ind1][ind2]=0
                matrix_prout[ind1][i]=1
                test=False
            i+=1

    return matrix_prout

def crossover(matrix_global1, matrix_global2):

    matrix = matrix_global1
    matrix2 = matrix_global2
    matrix_temp=np.zeros(len(matrix[0]))

    ind1 = random.randint(0,len(matrix)-1)
    ind2 = random.randint(0,len(matrix)-1)

    matrix_temp = matrix[ind1]
    #print("matrix temp : ", matrix_temp)
    matrix[ind1] = matrix2[ind2]
    matrix2[ind2]=matrix_temp

    return matrix, matrix2

def gencout(n):

    nb_cours= 8
    nb_voeux=6
    nb_cours_affectes = 4
    #creation d'un tableau de voeux
    voeux = np.empty(shape = (n, nb_cours), dtype = int)

    liste = np.zeros(shape=(nb_cours))
    for j in range(1,nb_cours):
        liste[j-1]=j
    
    for j in range(nb_voeux,nb_cours):
        liste[j]=-1

    for i in range(n):
        random.shuffle(liste)
        for j in range(nb_cours):
            cours = liste[j]
            voeux[i][j] = cours

    #print(voeux)

    return voeux


def genetique(matrix_cout,n,m, iter=100, disp=True):
  
    i_out = 0
    tab_cout = np.zeros(100)
    tab_sol = np.zeros(5)
    list_sol = []

    for i in range(5):
        tab_sol[i] = 10001

    while np.amin(tab_sol) > 10000 and i_out <4 :

        if disp:
            print("Début de l'affectation")

        for p in range(5):

            if disp:
                print("#-#-#-#-#-#-#-",p+1,"/ 5 -#-#-#-#-#-#-#-#")

            list_matrix = []
            list_matrix2 = []
            
            for i in range(100):
                matrix = gen(n,m)
                list_matrix.append(matrix)
                tab_cout[i]=cout(matrix, matrix_cout)
            
            if disp:
                print("cout initial généré : ", tab_cout[1])

            for j in tqdm(range(iter)):

                list_matrix2 = []   

                for _ in range(5):
                    ind = np.argmin(tab_cout)
                    list_matrix2.append(list_matrix[ind])
                    tab_cout[ind]=1000000

                list_matrix=[]
                list_matrix = list_matrix2.copy()

                for _ in range(5):
                    list_matrix.append(gen(n,m))
                
                """
                for j in range(5):
                    for i in range(6):
                        matrix_proutcaca = list_matrix[j].copy()
                        print(matrix_proutcaca[i])
                    print("")
                """

                for i in range(10):
                    for _ in range(6):
                        if i==4:
                            matrix3 = list_matrix[1].copy()
                        else :
                            matrix3 = list_matrix[i+1].copy()
                        matrix2 = list_matrix[i].copy()
                        matrix, matrix4 = crossover(matrix2,matrix3)
                        list_matrix.append(matrix)
                    for _ in range(3):
                        matrix2 = list_matrix[i].copy()
                        matrix = mutation(matrix2)
                        list_matrix.append(matrix)
                
                """
                print("--------------")
                for j in range(5):
                    for i in range(6):
                        matrix_proutcaca = list_matrix[j].copy()
                        print(matrix_proutcaca[i])
                    print("")
                """

                for i in range(100):
                    matrix=list_matrix[i].copy()
                    tab_cout[i]=cout(matrix, matrix_cout)

                """
                for i in range(5):
                    print(tab_cout[i])  
                print("-----")
                """
                

            tab_sol[p]=np.amin(tab_cout)
            list_sol.append(list_matrix[np.argmin(tab_cout)].copy())
            if disp:
                print("cout final obtenu : " ,tab_sol[p])
                

        i_out+=1
    #print(len(list_sol))
    #print(np.argmin(tab_sol))
    res_matrix = list_sol[np.argmin(tab_sol)]
    res_cout = np.amin(tab_sol)

    if disp:
        print("Affectation finale : ")
        for i in range(n):
            print(res_matrix[i])
        print("Cout final obtenu : ", res_cout)

    return res_matrix, res_cout
    
#_____________________main_____________________#

nbr_eleve = 20
nbr_mat = 8

matrix_cout=gencout(nbr_eleve)
res, cout = genetique(matrix_cout,nbr_eleve,nbr_mat)

