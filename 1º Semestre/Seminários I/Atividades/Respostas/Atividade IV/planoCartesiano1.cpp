#include <iostream>

using namespace std;

class Ponto{
	public:
		double x;
		double y;
};

Ponto pontoMedio(Ponto &p1, Ponto &p2){
	Ponto pm;
	pm.x = (p1.x + p2.x)/2.0;
	pm.y = (p1.y + p2.y)/2.0;	

	return pm;
}

void saudacao(){
	cout << "\nMédia de um ponto cartesiano:\n";
}

void preenchePonto(Ponto &p){
	cout << "\nPonto \n Digite seu valor X: ";
	cin >> p.x;
	cout << "\n Digite seu valor Y: ";
	cin >> p.y;
}

void resposta(Ponto &pm){
	cout << "\nO ponto médio (x, y) é:\n PM(" << pm.x << ", " << pm.y << ")\n\n";
}

int main(){
	saudacao();

	Ponto p1;
	Ponto p2;

	preenchePonto(p1);
	preenchePonto(p2);
	
	Ponto pm = pontoMedio(p1, p2);

	resposta(pm);

	return 0;
}
