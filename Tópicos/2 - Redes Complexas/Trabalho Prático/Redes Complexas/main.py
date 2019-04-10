import csv                                                             
import networkx as nx
from operator import itemgetter
import community
import matplotlib.pyplot as plt
import numpy as np

# leitura do nodelist file
with open('quakers_nodelist.csv', 'r') as nodecsv:                 
    nodereader = csv.reader(nodecsv)                                       
    nodes = [n for n in nodereader][1:]                                 

# obtém a lista de nomes, primeiro item do registro
node_names = [n[0] for n in nodes]                                       


# leitura do arquivo de arestas
with open('quakers_edgelist.csv', 'r') as edgecsv:                         
    edgereader = csv.reader(edgecsv)                                   
    edges = [tuple(e) for e in edgereader][1:]                         

# imprime a quantidade de cada um
#print(len(node_names))  
#print(len(edges))                                                                               

G = nx.Graph() # inicializando o grafo                                                   
G.add_nodes_from(node_names) # cria os nós                           
G.add_edges_from(edges) # adiciona as arestas no grafo  
#print(nx.info(G)) # imprime informações do grafo (resumo) 

