import random
import numpy as np
from time import sleep
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

def mutation(matrix):

    ind1 = random.randint(0,len(matrix)-1)
    ind2 = random.randint(0,len(matrix[0])-1)

    #print(ind1)
    #print(ind2)

    i=0
    test=True
    if matrix[ind1][ind2]==0:
        while i < len(matrix) and test:
            if matrix[ind1][i]==1:
                matrix[ind1][ind2]=1
                matrix[ind1][i]=0
                test=False
            i+=1

    if matrix[ind1][ind2]==1:
        while i < len(matrix) and test:
            if matrix[ind1][i]==0:
                matrix[ind1][ind2]=0
                matrix[ind1][i]=1
                test=False
            i+=1

    return matrix


def genetique(matrix_cout,n,m, iter):

    tab_cout = np.zeros(100)
    list_matrix = []
    list_matrix2 = []

    for i in range(100):
        matrix = gen(n,m)
        list_matrix.append(matrix)
        tab_cout[i]=cout(matrix, matrix_cout)

    print(tab_cout[1])

    for j in tqdm(range(iter)):

        list_matrix2 = [] 
        #print("##########################")      
        for _ in range(5):
            ind = np.argmin(tab_cout)
            """
            print("------------------")
            print(ind)
            print(tab_cout[ind])
            print("------------------")
            """
            list_matrix2.append(list_matrix[ind])
            tab_cout[ind]=1000000

        list_matrix = []
        #print(list_matrix)
        list_matrix=list_matrix2

        #for i in range(5):
            #print(list_matrix[i])

        for i in range(5):
            for _ in range(19):
                #matrix = mutation(list_matrix[i])
                matrix=gen(n,m)
                list_matrix.append(matrix)

        for i in range(100):
            matrix=list_matrix[i]
            tab_cout[i]=cout(matrix, matrix_cout)

        #print(list_matrix[1])
        #print(list_matrix[2])
        #for i in range(5):
            #print(tab_cout[i])

        
    """
    for i in range(100):
        tab_cout[i]=cout(list_matrix[i], matrix_cout)
    """

    return list_matrix[np.argmin(tab_cout)], tab_cout[np.argmin(tab_cout)]
    

matrix_cout=[[1,2,3,4,5,6,7,8],[2,4,1,3,8,6,7,5],[1,2,3,4,5,6,7,8],[2,4,1,3,8,6,7,5],[1,2,3,4,5,6,7,8]]

res, cout = genetique(matrix_cout,5,8,2000)

for i in range(5):
    print(res[i])

print(cout)