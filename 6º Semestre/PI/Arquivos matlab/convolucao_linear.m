clear;
close all;

% C = conv(A, B, SHAPE) returns a subsection of the convolution with size
%     specified by SHAPE:
%       'full'  - (default) returns the full convolution,
%       'same'  - returns the central part of the convolution
%                 that is the same size as A.

x=[1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0];  
h=x;

M= size(x);

y1=conv(x,h,'full');

figure; title('Convolu��o de dois pulsos iguais');
subplot(3,1,1);plot(x,'.'); ylabel('x');
subplot(3,1,2);plot(h,'.'); ylabel('h');
subplot(3,1,3);plot(y1,'*'); ylabel('conv(x,h)');

% Se x e h s�o sinais peri�dicos (com zero padding):

x=[1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]; 
h=x;

x=[x x x x];
h=[h h h h];

M= size(x);


y2=conv(x,h,'full');

figure; 
subplot(3,1,1);plot(x,'.'); ylabel('x');
subplot(3,1,2);plot(h,'.'); ylabel('h');
subplot(3,1,3);plot(y2,'*'); ylabel('conv'); title('Convolu��o de dois pulsos peri�dicos, c/ zero padding, c/ shape=full');

% y3=conv(x,h,'same');
% 
% figure; title('Convolu��o de dois pulsos peri�dicos, com zero padding, c/ shape=same');
% subplot(3,1,1);plot(x,'.'); ylabel('x');
% subplot(3,1,2);plot(h,'.'); ylabel('h');
% subplot(3,1,3);plot(y3,'*'); ylabel('conv');


% Se x e h s�o sinais peri�dicos (sem zero padding):

x=[1 1 1 1 1 1 1 0 0 0 0 0]; 
h=x;

x=[x x x x];
h=[h h h h];

M= size(x);


y2=conv(x,h,'full');

figure; title('Convolu��o');
subplot(3,1,1);plot(x,'.'); ylabel('x');
subplot(3,1,2);plot(h,'.'); ylabel('h');
subplot(3,1,3);plot(y2,'*'); ylabel('conv'); title('Convolu��o de dois pulsos peri�dicos, s/ zero padding, c/ shape=full');

% y3=conv(x,h,'same');
% 
% figure; title('Convolu��o');
% subplot(3,1,1);plot(x,'.'); ylabel('x');
% subplot(3,1,2);plot(h,'.'); ylabel('h');
% subplot(3,1,3);plot(y3,'*'); ylabel('conv');