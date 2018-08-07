clear;
close all;

A = imread('lena.gif');
[M,N] = size(A);

figure;
imshow(A); title('Lena original - 512x512');

B=rand(M,N).*255; % matriz MxN randômica com valores de 0 a 255
Ruido_1=uint8(B);
figure;
imshow(Ruido_1);title('Ruído uniforme de 0 a 255');

B=rand(M,N).*1000; % matriz MxN randômica com valores de  a 1000
figure;
imshow(B); title('Ruído de 0 a 1000, sem informar valores mínimo e máximo');
% O uso de imshow resulta em contraste pobre, pois os dados %excedem a faixa esperada
figure;
imshow(B,[0 1000]); title('Ruído de 0 a 1000, informando valores mínimo e máximo');

Im1= imnoise(A,'gaussian',0,0.1); %adiciona ruído gaussiano de média 0 e variância 0.1.
figure;
imshow(Im1);title('Lena com ruído gaussiano de média 0 e variância 0.1');

Im2= imnoise(A,'gaussian',0,0.05); %adiciona ruído gaussiano de média 0 e variância 0.1.
figure;
imshow(Im2);title('Lena com ruído gaussiano de média 0 e variância 0.05');

Im3= imnoise(A,'gaussian',0,0.001); %adiciona ruído gaussiano de média 0 e variância 0.1.
figure;
imshow(Im3);title('Lena com ruído gaussiano de média 0 e variância 0.05');
