//
// Created by Dave Nash on 20/10/2017.
//

#include "Block.h"
#include "sha256.h"

Block::Block(uint32_t nIndexIn, const string &sDataIn) : _nIndex(nIndexIn), _sData(sDataIn)
{
    _nNonce = 0;
    _tTime = time(nullptr);

    sHash = _CalculateHash();
}

void Block::MineBlock(uint32_t nDifficulty)
{
    char cstr[nDifficulty + 1];
    for (uint32_t i = 0; i < nDifficulty; ++i)
    {
        cstr[i] = '0';
    }
    cstr[nDifficulty] = '\0';

    string str(cstr);

	// Variável auxiliar para transformar a string sHash em um ponteiro,
	// para que possa ser mapeada na GPU.
	char *_sHash = const_cast<char*>(sHash.c_str());

	// Variável auxiliar para transformar a string str em um ponteiro,
	// para que possa ser mapeada na GPU.
	char *_str = const_cast<char*>(str.c_str());

	printf("%s", _str);
	// Variável auxiliar para o contador _nNonce, que é
	// privado na classe Block.
	uint32_t nNonce = _nNonce;

	// Flag sinalizando a saída do loop.
	bool found = false;
	
	// int i;
	// while (sHash.substr(0, nDifficulty) != str)
	// while (i < 5000000)
	while (!found)
	{
		#pragma omp target map(from: _sHash) \
			map(tofrom: found, nNonce) map(to: _str)
		#pragma omp teams distribute parallel for simd
		for (int j = 0; j < 1024; j++)
		{
			nNonce++;

			// string temp = _CalculateHash();
			// string comp(_str);
			
		// 	if (temp.substr(0, nDifficulty) == comp) {
		// 		#pragma omp critical
		// 		{
		// 			_sHash = const_cast<char*>(temp.c_str());
		// 			found = true;
		// 		}
		// 	}
		}

		// found = true
		// sHash.assign(_sHash);
	}

	cout << nDifficulty << endl;
    cout << "Block mined: " << sHash << endl;
}

inline string Block::_CalculateHash() const
{
    stringstream ss;
    ss << _nIndex << sPrevHash << _tTime << _sData << _nNonce;

    return sha256(ss.str());
}
