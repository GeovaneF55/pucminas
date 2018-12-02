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

	bool found = false;
	// int i = 0;
	// while (sHash.substr(0, nDifficulty) != str)
	#pragma omp parallel
	{
		#pragma omp single nowait
		while (!found)
		// while (i < 5000000)
		{
			#pragma omp task
			{
				#pragma critical
				_nNonce++;
				string temp = _CalculateHash();

				if (temp.substr(0, nDifficulty) == str) {
					#pragma critical
					sHash = temp;

					found = true;
				}
			}

			// i++;
		}
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
