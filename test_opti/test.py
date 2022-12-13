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
                cout=cout+10*Nc
            else:
                cout=cout+pow(val,2)

    for j in range(len(matrix[0])):
        col_sum[j] = sum([matrix[i][j] for i in range(len(matrix))])
        if 0<col_sum[j]<5:
            cout=cout+10000
    #print("col sum : ")
    #print(col_sum)
    return cout

"""
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
"""

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

def test(matrix):

    matrix=np.zeros((6,8))
    return matrix



def genetique(matrix_cout,n,m, iter):
  
    i_out = 0
    tab_cout = np.zeros(100)

    for i in range(100):
        tab_cout[i] = 10001

    while tab_cout[np.argmin(tab_cout)] > 10000 and i_out <4 :

        list_matrix = []
        list_matrix2 = []

        for i in range(100):
            matrix = gen(n,m)
            list_matrix.append(matrix)
            tab_cout[i]=cout(matrix, matrix_cout)
        
        print(tab_cout[1])

        for j in tqdm(range(iter)):

            list_matrix2 = []   

            for _ in range(5):
                ind = np.argmin(tab_cout)
                list_matrix2.append(list_matrix[ind])
                tab_cout[ind]=1000000

            
            #print(list_matrix)
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
                for _ in range(9):
                    if i==4:
                        matrix3 = list_matrix[1].copy()
                    else :
                        matrix3 = list_matrix[i+1].copy()
                    matrix2 = list_matrix[i].copy()
                    matrix, matrix4 = crossover(matrix2,matrix3)
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

        print(tab_cout[np.argmin(tab_cout)])
        i_out+=1

    return list_matrix[np.argmin(tab_cout)], tab_cout[np.argmin(tab_cout)]
    

matrix_cout=[[1,2,3,4,5,6,7,8],[2,4,1,3,8,6,7,5],[1,2,3,4,5,6,7,8],[2,4,1,3,8,6,7,5],[1,2,3,4,5,6,7,8],[7,3,8,2,6,4,5,1],[5,4,7,8,6,2,3,1],[8,7,6,5,4,3,2,1]]


res, cout = genetique(matrix_cout,8,8,400)

for i in range(8):
    print(res[i])

print(cout)
