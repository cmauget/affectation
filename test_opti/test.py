import random
import numpy as np

#-----------------gen-----------------#

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


#-----------------cout-----------------#

def cout(matrix, matrix_cout):

    Nc=8
    cout=0
    nb_lignes = len(matrix)
    nb_colones = len(matrix[0])
    col_sum = np.zeros(nb_colones)
    for j in range(nb_colones):
        for i in range(nb_lignes):
            val = matrix[i][j]*matrix_cout[i][j]
            if val == -1:
                cout=cout+10*pow(Nc,2)
            else:
                cout=cout+pow(val,2)

    for j in range(nb_colones):
        col_sum[j] = sum([matrix[i][j] for i in range(nb_lignes)])
        if 0<col_sum[j]<5 or col_sum[j]>10:
            cout=cout+10000
    return cout


#-----------------mutation-----------------#

def mutation(matrix_global):

    nb_lignes = len(matrix_global)

    ind1 = random.randint(0,nb_lignes-1)
    ind2 = random.randint(0,len(matrix_global[0])-1)
  #  ind3 = random.randint(0,len(matrix_global)-1)
    
    sub_list = matrix_global[ind1].copy()

    test = True
    i=0
    while test :
        v = sub_list[ind2]
        val = abs(v-1)
        if sub_list[i]== val:
            sub_list[i]=v
            sub_list[ind2]=val
            test=False
        i+=1

    matrix_global[ind1]=sub_list
   

    return matrix_global


#-----------------crossover-----------------#    

def crossover(matrix_global1, matrix_global2):

    matrix = matrix_global1
    matrix2 = matrix_global2
    #matrix_temp=np.zeros(len(matrix[0]))

    ind1 = random.randint(0,len(matrix)-1)
    ind2 = random.randint(0,len(matrix)-1)

    #matrix_temp = matrix[ind1]
    #print("matrix temp : ", matrix_temp)
    matrix[ind1] = matrix2[ind2]
    #matrix2[ind2]=matrix_temp

    return matrix


#-----------------gencout-----------------#  

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


#-----------------genetique-----------------#

def genetique(matrix_cout,n,m, iter=100, disp=True, debug=False, target=550):
  
    i_out = 0
    tab_cout = np.zeros(100)

    for i in range(100):
        tab_cout[i] = 10001

    while np.amin(tab_cout) > 10000 and i_out <4 :

        if disp:
            print("Début de l'affectation")

        list_matrix = []
        list_matrix2 = []
        
        for i in range(100):
            matrix = gen(n,m)
            list_matrix.append(matrix)
            tab_cout[i]=cout(matrix, matrix_cout)
        
        if disp:
            print("cout initial généré : ", tab_cout[1])

        for j in range(iter):
            
            list_matrix2 = []   

            for _ in range(5):
                ind = np.argmin(tab_cout)
                list_matrix2.append(list_matrix[ind])
                tab_cout[ind]=1000000

            list_matrix=[]
            list_matrix = list_matrix2.copy()

            for _ in range(5):
                list_matrix.append(gen(n,m))

            for i in range(10):

                for k in range(6):

                    if j>=i:
                        ind= k+1
                    else:
                        ind = k

                    matrix3 = list_matrix[ind].copy()
                    matrix2 = list_matrix[i].copy()
                    matrix_cross = crossover(matrix2,matrix3)
                    list_matrix.append(matrix_cross)
                
                for _ in range(3):
                    matrix4 = list_matrix[i].copy()
                    matrix_mut = mutation(matrix4)
                    list_matrix.append(matrix_mut)
                

            for i in range(100):
                matrix=list_matrix[i].copy()
                tab_cout[i]=cout(matrix, matrix_cout)

            if debug:
                print(np.amin(tab_cout))
            
            print("Cout actuel : ",np.amin(tab_cout), "||| itération : ",j+1,"/",iter, "||| target : ",target, end='\r')
            if np.amin(tab_cout) < target:
                break
            
        if debug:
            print()

        i_out+=1

    res_matrix = list_matrix[np.argmin(tab_cout)]
    res_cout = np.amin(tab_cout)

    if disp:
        print("")
        print("Affectation finale : ")
        for i in range(n):
            print(res_matrix[i])
        print("Cout final obtenu : ", res_cout)

    return res_matrix, res_cout
    
#_____________________main_____________________#

nbr_eleve = 17
nbr_mat = 8

matrix_cout=gencout(nbr_eleve)
res, cout = genetique(matrix_cout,nbr_eleve,nbr_mat, iter=100000)