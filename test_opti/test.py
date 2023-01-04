from Opti import *

   
o = Opti()
nb_eleve = 15 #nb d'eleves au total 
nb_mat = 8 #nb de matières au totales
nb_voeux = 4 #nb de voeux fait par l'eleve
nb_cours = 4 #nb de cours à assigner

matrix_cout= [[1,2,3,4],[1,2,3,4],[3,4,2,1],[2,4,1,3],[2,3,1,4]]#o.gencout(nb_eleve, nb_mat, nb_voeux)
matrix_cout=o.gencout(nb_eleve, nb_mat, nb_voeux)
print(matrix_cout)
res, cout = o.genetique(matrix_cout, nb_eleve, nb_mat, nb_cours, debug=False, iter=400)
    