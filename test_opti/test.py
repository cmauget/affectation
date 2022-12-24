import random
import numpy as np
from tqdm import tqdm

#_____________________generation_____________________#


#-----------------gen-----------------#
#génére une matrice aléatoire
def gen(nb_eleve,nb_mat,nb_cours):
    
    matrix = np.zeros(shape = (nb_eleve,nb_mat), dtype = int)
    for i in range(nb_eleve):
        for j in range(nb_mat):
            matrix[i][j] = random.randint(0,1)
   # matrix = [[random.randint(0, 1) for _ in range(nb_mat)] for _ in range(nb_eleve)]
    col_sum = np.zeros(shape=(nb_mat), dtype =int)

    for i in range(nb_eleve):
        row_sum = sum(matrix[i])
        for j in range(nb_mat):
            if row_sum > nb_cours:
                if matrix[i][j]==1:
                    matrix[i][j] = 0
                    row_sum -= 1
            elif row_sum < nb_cours:
                if matrix[i][j]==0:
                    matrix[i][j] = 1
                    row_sum += 1

    for j in range(nb_mat):
        col_sum[j] = sum([matrix[i][j] for i in range(nb_eleve)])

    i=0

    while np.amax(col_sum) > 10 and i < nb_eleve:
        
        ind= np.argmin(col_sum)
        ind2 = np.argmax(col_sum)

        if np.amax(col_sum) > 10 and matrix[i][ind2]==1 and matrix[i][ind]==0:
            matrix[i][ind]=1
            matrix[i][ind2]=0
            col_sum[ind2] -= 1
            col_sum[ind] += 1
        i += 1

    return matrix


#-----------------gencout-----------------#  
#gen une matrice de cout aléatoire
def gencout(nb_eleve, nb_mat, nb_voeux):

   
    #creation d'un tableau de voeux
    voeux = np.zeros(shape = (nb_eleve, nb_mat), dtype = int)

    liste = np.zeros(shape=(nb_mat), dtype = int)
    for j in range(1,nb_mat):
        liste[j-1]=j
    
    for j in range(nb_voeux,nb_mat):
        liste[j]=-1

    for i in range(nb_eleve):
        random.shuffle(liste)
        for j in range(nb_mat):
            cours = liste[j]
            voeux[i][j] = cours

    return voeux



#_____________________cout_____________________#


#-----------------cout-----------------#
#définit le cout
def cout(matrix, matrix_cout, nb_eleve, nb_mat):

    Nc=8
    cout=0
    col_sum = np.zeros(shape = (nb_mat), dtype=int)
    for j in range(nb_mat):
        for i in range(nb_eleve):
            val = matrix[i][j]*matrix_cout[i][j]
            if val == -1: #-1 si voeux non choisi
                cout=cout+10*pow(Nc,2)
            else:
                cout=cout+pow(val,2)

    for j in range(nb_mat):
        col_sum[j] = sum([matrix[i][j] for i in range(nb_eleve)])
        if 0<col_sum[j]<5 or col_sum[j]>10:
            cout=cout+10000 #pénalise le non respect d'une condition
    return cout



#_____________________modification_____________________#

#-----------------mutation-----------------#
#prend une affectation au hasard et l'échange avec une affectation opposé sur la même ligne
def mutation(matrix_global, nb_eleve, nb_mat, nb_cours):


    ind1 = random.randint(0,nb_eleve-1)
    ind2 = random.randint(0,nb_mat-1)
    ind3 = random.randint(0,nb_mat-nb_cours)
    
    sub_list = matrix_global[ind1].copy()

    test = True
    i,j=0,0
    while test :
        v = sub_list[ind2]
        val = abs(v-1)
        if sub_list[i]== val and j==ind3 :
            sub_list[i]=v
            sub_list[ind2]=val
            test=False
        elif sub_list[i]== val and j!=ind3:
            j+=1
        i+=1
        if i>=nb_mat:
            i=0

    matrix_global[ind1]=sub_list
   

    return matrix_global


#-----------------crossover-----------------#    
#change de marnière aléatoire deux lignes de deux matrices différentes

def crossover(matrix_global1, matrix_global2,nb_eleve):

    matrix = matrix_global1
    matrix2 = matrix_global2

    ind1 = random.randint(0,nb_eleve-1)
    ind2 = random.randint(0,nb_eleve-1)

    #matrix_temp = matrix[ind1]
    #print("matrix temp : ", matrix_temp)
    matrix[ind1] = matrix2[ind2]
    #matrix2[ind2]=matrix_temp

    return matrix



#_____________________algo_principal_____________________#

#-----------------genetique-----------------#

def genetique(matrix_cout, nb_eleve, nb_mat, nb_cours, iter=400, disp=True, debug=False, target=550):
  
    i_out = 0
    tab_cout = np.zeros(shape= (100), dtype = int)

    for i in range(100):
        tab_cout[i] = 10001 #initalise un tableau de cout (pour le while)

    while np.amin(tab_cout) > 10000 and i_out <4 :

        if disp:
            print("Début de l'affectation...")

        list_matrix = []
        list_matrix2 = []
        
        for i in range(100): #gen 100 matrices aléatoires
            matrix = gen(nb_eleve,nb_mat, nb_cours)
            list_matrix.append(matrix)
            tab_cout[i]=cout(matrix, matrix_cout, nb_eleve, nb_mat)#calcule le cout initiale
        
        if disp:
            print("cout initial généré : ", tab_cout[1])

        for j in range(iter):
            
            list_matrix2 = []   

            for _ in range(5): #prend les 5 matrices avec le plus faible cout
                ind = np.argmin(tab_cout)
                list_matrix2.append(list_matrix[ind])
                tab_cout[ind]=1000000

            list_matrix=[]
            list_matrix = list_matrix2.copy()

            for _ in range(5):
                list_matrix.append(gen(nb_eleve,nb_mat,nb_cours))#gen 5 nouvelles matrices pour ne pas stériliser l'échantillon

            for i in range(10):#pour chacune des 10 matrices (5 + 5 gens)

                for k in range(6):#Il y a 6 crossover

                    if j>=i:
                        ind= k+1
                    else:
                        ind = k

                    matrix_temp1 = list_matrix[ind].copy()
                    matrix_temp2 = list_matrix[i].copy()
                    matrix_cross = crossover(matrix_temp1,matrix_temp2,nb_eleve)
                    list_matrix.append(matrix_cross)
                
                for _ in range(3):#et 3 mutations
                    matrix_temp = list_matrix[i].copy()
                    matrix_mut = mutation(matrix_temp, nb_eleve, nb_mat, nb_cours)
                    list_matrix.append(matrix_mut)
                

            for i in range(100):#nouveau calcul du cout
                matrix=list_matrix[i].copy()
                tab_cout[i]=cout(matrix, matrix_cout, nb_eleve, nb_mat)

            res_cout = np.amin(tab_cout)

            if debug:
                print(res_cout)
            
            if disp:#affichage
                print("| Cout actuel : ",res_cout, "||| itération : ",j+1,"/",iter, "||| target : ",target," |" ,end='\r')

                if np.amin(tab_cout) < target:
                    print("")
                    print("target atteinte en ",j+1, " iterations")
                    break
            
        if debug:
            print()

        i_out+=1

    res_matrix = list_matrix[np.argmin(tab_cout)]

    if disp:
        print("")
        print("Affectation finale : ")
        print(res_matrix)
        print("Cout final obtenu : ", res_cout)

    return res_matrix, res_cout
    
#_____________________main_____________________#

nb_eleve = 17 #nb d'eleves au total 
nb_mat = 8 #nb de matières au totales
nb_voeux = 6 #nb de voeux fait par l'eleve
nb_cours = 4 #nb de cours à assigner

matrix_cout= gencout(nb_eleve, nb_mat, nb_voeux)
res, cout = genetique(matrix_cout, nb_eleve, nb_mat, nb_cours)