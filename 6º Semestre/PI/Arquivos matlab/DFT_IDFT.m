clear;
close all;


x=[1 1 1 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0]; 
N=length(x);
n=0:N-1;
h=x;

X=fft(x);
H=fft(h); % Transformada de Fourier 

x_estimado=ifft(X); % Transformada de Fourier Inversa 

figure;
subplot(3,1,1); plot(n,x,'.'); ylabel('x(n)');
subplot(3,1,2); plot(n,X,'.'); ylabel('X(mi)=FFT(x(n)');
subplot(3,1,3); plot(n,x_estimado,'.'); ylabel('IFFT(X(mi))');


