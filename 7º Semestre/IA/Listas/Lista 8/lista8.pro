nosolo(a).
nosolo(d).
nosolo(f).
nosolo(j).

apoiado(b, c).
apoiado(c, d).
apoiado(e, f).
apoiado(g, h).
apoiado(h, i).
apoiado(i, j).

contiguo(a, d).
contiguo(d, f).
contiguo(f, j).

base(X, X) :- nosolo(X).
base(X, Y) :- apoiado(Y, Z), base(X, Z).

base_a_direita(X, X) :- nosolo(X).
base_a_direita(Y, X) :- contiguo(X, Z), base_a_direita(Y, Z).

% objeto_a_direita(X, X) :- nosolo(X).

% todos_esquerda(X, Esquerda) :- findall(Z, objeto_a_direita(Z, X), Esquerda)
