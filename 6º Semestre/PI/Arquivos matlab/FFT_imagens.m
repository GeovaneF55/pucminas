clear;
close all


A = imread('cameraman.tif'); %L� imagem 

%Calcula TF e obt�m vers�o centrada:
FT = fft2(A); 
FT_centred = fftshift(FT);  

subplot(2,3,1); imshow( A); title('Original'); %Exibe a imagem 
subplot(2,3,2); imshow( log( 1 + abs(FT)),[]); title('M�dulo da FT (escala log)'); %Exibe o m�dulo da TF (escala log) 
subplot(2,3,3); imshow( log( 1 + abs(FT_centred)),[]); title('M�dulo da FT centrada (escala log)'); %Exibe o m�dulo da TF centrada (escala log) 

%Calcula FFT inversa e exibe resultado:
Im1 = abs(ifft2(FT)); subplot(2,3,5); imshow(Im1,[]);  title('IFT (a partir de FT n�o centrada)');
Im2 = abs(ifft2(FT_centred)); subplot(2,3,6); imshow(Im2,[]); title('IFT (a partir de FT centrada)'); 


%Constr�i filtro no dom�nio da frequ�ncia :
[xd, yd] = size( A); 
x =-xd./ 2: xd./2 - 1; 
y = -yd./ 2: yd./2 - 1; 
[X, Y] = meshgrid(x, y); 
%  meshgrid   Cartesian grid in 2-D/3-D space
%  [X,Y] = meshgrid(xgv,ygv) replicates the grid vectors xgv and ygv to 
%  produce the coordinates of a rectangular grid (X, Y). 
sigma = 32; 
arg =(X.^2 + Y.^ 2)./ sigma.^ 2; 
frqfilt = exp(-arg); 


imfilt1 = abs(ifft2(frqfilt.* FT)); %Filtro centrado em espectro n�o centrado 
imfilt2 = abs( ifft2(frqfilt.* FT_centred)); %filtro centrado em espectro centrado 
figure;
subplot(2,2,1), imshow(frqfilt,[]); title ('FT Filtro centrado'); %Exibe resultados 
subplot(2,2,2), imshow( imfilt1,[]); title('c/ Filtro centrado em espectro n�o centrado'); 
subplot(2,2,4), imshow( imfilt2,[]); title('c/ Filtro centrado em espectro centrado'); 

% Coment�rios Fun��es de Matlab: fft2, ifft2, fftshift Este exemplo ilustra o papel de fftshift, que centra a transformada de Fourier, 
% de modo que a dupla de frequ�ncias espaciais nulas ocorra no centro da matriz.


