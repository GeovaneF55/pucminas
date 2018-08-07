clear;
close all;

RGB = imread('gantrycrane.png');
figure;
imshow(RGB);
I=rgb2gray(RGB);
figure
imshow(I);

% BW = edge(I,'sobel',THRESH,DIRECTION) specifies directionality for the
%     Sobel method. DIRECTION is a string specifying whether to look for
%     'horizontal' or 'vertical' edges, or 'both' (the default).
    
[I_gx_sobel,thx_sobel] = edge(I,'sobel','vertical'); %Retorna o valor de threshold calculado pelo Matlab - oberve como é baixo
[I_gy_sobel,thy_sobel] = edge(I,'sobel','horizontal');
I_sobel= I_gx_sobel | I_gy_sobel;
thx_sobel
thy_sobel

figure
subplot(2,2,1)
imshow(I);title('Original');
subplot(2,2,2)
imshow(I_gx_sobel);title('Gx');
subplot(2,2,3)
imshow(I_gx_sobel);title('Gy');
subplot(2,2,4);
imshow(I_sobel);title('Sobel');

[I_sobel_both,th_sobel] = edge(I,'sobel');
th_sobel
figure
subplot(1,2,1)
imshow(I_sobel);title('Sobel a partir de Gx e Gy');
subplot(1,2,2)
imshow(I_sobel_both);title('Sobel');

[I_canny,th_canny] = edge(I,'canny');
th_canny % retorna 1 vetor, com os 2 thresholds do método canny
figure
imshow(I_canny);title('Canny');

[I_log,th_log] = edge(I,'log');
th_log % retorna 1 vetor, com os 2 thresholds do método canny
figure
imshow(I_log);title('Laplaciano do Gaussiano');

[I_zerocross,th_zerocross] = edge(I,'zerocross');
th_zerocross % retorna 1 vetor, com os 2 thresholds do método canny
figure
imshow(I_zerocross);title('Cruzamento por zero');


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%Geração de imagem sintética com borda


A=uint8(zeros(500,500));
A(:,80:400)=128;
A(:,400:500)=255;
for i=1:500
    for j=50:80
    A(i,j)=A(i,j-1)+10;
    end    
    for j=150:200
    A(i,j)=A(i,j-1)-10;
    end
    for j=430:500
    A(i,j)=A(i,j-1)+30;
    end
end
imhist(A)
figure
imshow(A);title('Imagem sintética com 3 bordas - primeira suave, segunda abrupta e terceira ');

[A_canny,th_ac] = edge(A,'canny');
th_ac % retorna 1 vetor, com os 2 thresholds do método canny
figure
imshow(A_canny);title('Canny');

[A_sobel,th_as] = edge(A,'sobel');
th_as % retorna 1 vetor, com os 2 thresholds do método canny
figure
imshow(A_sobel);title('Sobel');

figure
imshowpair(A,A_canny,'montage'),title('Imagem original (à esquerda) e Detecção de Bordas com Canny (à direita)') 

figure
imshowpair(A,A_sobel,'montage'),title('Imagem original (à esquerda) e Detecção de Bordas com Sobel (à direita)') 