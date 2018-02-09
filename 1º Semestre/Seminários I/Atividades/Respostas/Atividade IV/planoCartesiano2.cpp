#include <iostream>

using namespace std;

class Ponto{
	public:
		double x;
		double y;

		Ponto pontoMedio(Ponto &p){
			Ponto pm;
			cout << "x1: " << this->x << " -- x2: " << p.x;
			pm.x = (this->x + p.x)/2.0;
			pm.y = (this->y + p.y)/2.0; 
			return pm;
		}
		
};

void saudacao(){
	cout << "\nMédia de um ponto cartesiano:\n";
}

void preenchePonto(Ponto &p){
	cout << "\nPonto \n Digite seu valor X: ";
	cin >> p.x;
	cout << "\n Digite e seu valor Y: ";
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
	
	Ponto pm = p1.pontoMedio(p2);

	resposta(pm);

	return 0;
}
